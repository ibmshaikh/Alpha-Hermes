package c.alpha_hermes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;






// It's Just Sloppy CraftmanShip

public class ChatList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private final int RC_SIGN_IN = 1;
    private List<User> mList = new ArrayList<>();
    // private ChatListAdapter adapter;
    private FloatingActionButton mActionButton;
    private FirebaseListAdapter<User> mFirebaseListAdapter ;
    private ArrayAdapter<String> mAdapter ;
    private ArrayList<String> mArrayList;
    private DatabaseReference mDatabaseReference ;
    private FirebaseDatabase mDatabase;
    private ListView mListView;
    private FirebaseAuth mAuth ;
    private  FirebaseUser mFireUser ;
    private FirebaseAuth.AuthStateListener mAuthListener;

    final Context context = this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);


     //   FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mListView = (ListView) findViewById(R.id.ChatDialogListView);
        mActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        mListView = (ListView)findViewById(R.id.ChatDialogListView);



        //  String mGroupUser = FirebaseAuth.getInstance().getCurrentUser().getUid() ;

           mAuth = FirebaseAuth.getInstance();


        final String mUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mArrayList = new ArrayList<String>();

        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mArrayList);


        mListView.setAdapter(mAdapter);


        final  String name = getIntent().getStringExtra("GROUPNAME");








        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("USERS").child(mUser);

        mDatabaseReference.keepSynced(true);

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())

                {



                       Log.e("LostInPa",dataSnapshot1.toString());


                  String GroupName = (String)dataSnapshot1.getValue().toString();



                       mArrayList.add(GroupName);




                    mAdapter.notifyDataSetChanged();

                     mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                         @Override
                         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            // Test code

                             String userName = mArrayList.get(i);
                             Intent test = new Intent(getApplicationContext(),ChatUser.class);
                             test.putExtra("Trip",userName);







                             // Base code
                             String message = mArrayList.get(i);
                             Intent inti = new Intent(getApplicationContext(), MainActivity.class);
                             inti.putExtra("messy", message);
                            // Log.d("Message", message);
                             startActivity(inti);

                         }
                     });


                 }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null)
                {

                    Toast.makeText(getApplicationContext(),"YOLO",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false).setProviders(AuthUI.EMAIL_PROVIDER,AuthUI.GOOGLE_PROVIDER).build(),RC_SIGN_IN);
                }


            }
        };




        mDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mDatabase.getReference().child(mUser);

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(ChatList.this,CreateGroup.class);

             //   mDatabaseReference.push().setValue(new User(name));

              //    populate();

               startActivity(i);

            }
        });

        // populate();

    }

    public void populate()
    {

        mFirebaseListAdapter = new FirebaseListAdapter<User>(this,User.class,R.layout.chatlist,mDatabaseReference) {
            @Override
            protected void populateView(View v, User model, int position) {

                TextView mGroupName = (TextView)v.findViewById(R.id.ZeeshanChatRoom) ;

               mGroupName.setText(model.getmName());


            }
        };

        mListView.setAdapter(mFirebaseListAdapter);
    }


    @Override
    protected  void onPause()
    {
        super.onPause();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);

    }

}
