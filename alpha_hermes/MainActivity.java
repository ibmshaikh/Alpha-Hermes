package c.alpha_hermes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  "djkdvjksdj" ;


    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;

   private ChildEventListener mChildEventListener;
    private FirebaseListAdapter<Messages> adapter;



    private ListView listView;
    private  EditText mEditText;
    private Button mbutton;

    private MessageInput messageInput;


    String group ,human ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("ChatRoom");


        group = getIntent().getStringExtra("GROUPNAME");


              human = getIntent().getStringExtra("USERNAME");


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();

        // Fragment fragment = fragmentManager.findFragmentById(R.id.FragmetContainer);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

             Fragment fragment   =   Chatbox_Fragment.newInstance();


        Bundle bundle = new Bundle();
        bundle.putString("G3",group);
        bundle.putString("H3",human);
        fragment.setArguments(bundle);

        fragmentTransaction.add(R.id.FragmetContainer,fragment).commit();

     //   displayMessages();




    }


    private void displayMessages()
    {

        listView = (ListView)findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<Messages>(this,Messages.class,R.layout.messages,mDatabaseReference) {
            @Override
            protected void populateView(View v, Messages model, int position) {



                TextView messageText = (TextView)v.findViewById(R.id.Name);
                TextView messsageName = (TextView)v.findViewById(R.id.Maaaasage) ;

                messageText.setText(model.getmText());

              messsageName.setText(model.getmName());


            }
        };




        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }


    @Override
    public void onBackPressed()
    {

        int i = 0 ;

        ++i;

        if(i==1)
        {

            System.exit(0);

        }

        Toast.makeText(getApplicationContext(),"Don't press back ",Toast.LENGTH_SHORT).show();

    }


}
