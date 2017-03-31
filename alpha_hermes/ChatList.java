package c.alpha_hermes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;






public class ChatList extends AppCompatActivity {

    //private RecyclerView mRecyclerView;


    private List<User> mList = new ArrayList<>();


   // private ChatListAdapter adapter;


    private FloatingActionButton mActionButton;


     private FirebaseListAdapter<User> mFirebaseListAdapter ;


    private DatabaseReference mDatabaseReference ;


     private FirebaseDatabase mDatabase;


    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        mListView = (ListView) findViewById(R.id.ChatDialogListView);
        mActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        mListView = (ListView)findViewById(R.id.ChatDialogListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);




            }
        });


        mDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mDatabase.getReference().child("User");


      final  String name = getIntent().getStringExtra("GROUPNAME");


        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(ChatList.this,CreateGroup.class);

                mDatabaseReference.push().setValue(new User(name));

               startActivity(i);

            }
        });

         populate();

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

}
