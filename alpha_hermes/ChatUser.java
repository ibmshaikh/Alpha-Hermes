package c.alpha_hermes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StreamDownloadTask;

import java.util.ArrayList;

public class ChatUser extends AppCompatActivity {



       private ArrayList<String> mArrayList ;
       private ArrayAdapter<String> mArrayAdapter;
       private ListView mListView ;
       private DatabaseReference mDatabaseReference ;
       private FirebaseAuth mAuth;
       private DatabaseReference mDeleteRefernce ;
       private FirebaseListAdapter<Test> mFirebaseListAdapter;
       private ArrayList<String>   mUserList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_user);

        final String NAAM = getIntent().getStringExtra("LALABHAI");


        final String group = getIntent().getStringExtra("GROUP");

        String human = getIntent().getStringExtra("RICKALL");

        mListView = (ListView)findViewById(R.id.userslist);

         mArrayList = new ArrayList<>();
         mUserList =  new ArrayList<>();


        // Database References
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("UTest").child(group);
        mDeleteRefernce = FirebaseDatabase.getInstance().getReference();






        mFirebaseListAdapter = new FirebaseListAdapter<Test>(this,Test.class,R.layout.firebaseusers,mDatabaseReference) {
            @Override
            protected void populateView(View v, Test model, int position) {


                TextView Name = (TextView)v.findViewById(R.id.NAAM);


                Name.setText(model.getmUser());




            }
        } ;


        mListView.setAdapter(mFirebaseListAdapter);
        mFirebaseListAdapter.notifyDataSetChanged();




         final  String ID = FirebaseAuth.getInstance().getCurrentUser().getUid();
       mDatabaseReference.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                   String UserId = dataSnapshot.child("uuid").getValue().toString();
                   String UserName = dataSnapshot.child("mUser").getValue().toString();


                   mArrayList.add(UserId);
                   mUserList.add(UserName);

                   mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                           String user = mArrayList.get(i);
                           String naam = mUserList.get(i);

                           Toast.makeText(getApplicationContext(), naam.toString(), Toast.LENGTH_SHORT).show();

                           Query delete = mDeleteRefernce.child("USERS").child(user).orderByChild("mName").equalTo(group);

                           delete.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {


                                   for (DataSnapshot data : dataSnapshot.getChildren()) {




                                       data.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               Toast.makeText(ChatUser.this, "Removed From the Group", Toast.LENGTH_SHORT).show();
                                           }
                                       });


                                   }


                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });

                           Query deleteName = mDeleteRefernce.child("UTest").child(group).orderByChild("mUser").equalTo(naam);

                           deleteName.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {

                                   for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                       dataSnapshot1.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {

                                               Toast.makeText(ChatUser.this, "LAAAAALA", Toast.LENGTH_SHORT).show();

                                           }
                                       });
                                   }


                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });


                           //     mFirebaseListAdapter.notifyDataSetChanged();


                       }
                   });



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







                     // TODO wORKING CODE """Please don't Alter this""""


        /*
       // mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mArrayList);

       // mListView.setAdapter(mArrayAdapter);





        mDeleteRefernce = FirebaseDatabase.getInstance().getReference();

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {


                  String Username =  dataSnapshot1.getValue().toString();

                    mArrayList.add(Username);


                    mArrayAdapter.notifyDataSetChanged();
                      // TODO FIX THE CONFUSION

                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                             String user = mArrayList.get(i);


                       //   mDeleteRefernce.child("USERS").child(user);

                            Query query = mDeleteRefernce.child("USERS").child(user).orderByChild("mName").equalTo(group);

                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for(DataSnapshot dataSnapshot2 : dataSnapshot.getChildren())
                                    {
                                        dataSnapshot2.getRef().removeValue();
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    Toast.makeText(ChatUser.this,"Naa Nahh Naaah",Toast.LENGTH_SHORT).show();

                                }
                            });

                            // Intent intent = new Intent(ChatUser.this,MainActivity.class);
                            // intent.putExtra("HOTSTUFF",user);
                            // startActivity(intent);
                        }
                    });





                }

             //  Log.d("Letshere",dataSnapshot.toString());


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



*/

    }
}
