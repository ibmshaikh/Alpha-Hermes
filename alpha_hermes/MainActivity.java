package c.alpha_hermes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.firebase.auth.FirebaseUser;
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

import c.alpha_hermes.dummy.LoginActivity;

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


      String group ,human,group2 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("ChatRoom");


           //  ActionBar actionBar = getActionBar();
          //   actionBar.setDisplayHomeAsUpEnabled(true);


        // Code Sorta Cryptic Here
        if(getIntent().getStringExtra("HOTSTUFF")!=null) {
            group =  getIntent().getStringExtra("HOTSTUFF");

        }
        else if((getIntent().getStringExtra("GROUPNAME"))==null)
        {
            group = getIntent().getStringExtra("messy");  ;
        }
        else
        {
            group = getIntent().getStringExtra("GROUPNAME");
        }



                  // group =  getIntent().getStringExtra("GROUPNAME");


              human = getIntent().getStringExtra("USERNAME");


        String kkk = getIntent().getStringExtra("RICK");


        super.onCreate(savedInstanceState);


               setContentView(R.layout.activity_main);


             FragmentManager fragmentManager = getSupportFragmentManager();

          // Fragment fragment = fragmentManager.findFragmentById(R.id.FragmetContainer);

             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             Fragment fragment   =   Chatbox_Fragment.newInstance();


        Bundle bundle = new Bundle();
        bundle.putString("G3",group);
        bundle.putString("H3",human);
        bundle.putString("R3",kkk);
        fragment.setArguments(bundle);

        fragmentTransaction.add(R.id.FragmetContainer,fragment).commit();

     //   displayMessages();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.activity_chat_users_drawer,menu);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case (R.id.GroupMenuBar) :
                 Intent i = new Intent(MainActivity.this,ChatUser.class);
                i.putExtra("GROUP",group);
                startActivity(i);

                Toast.makeText(this,"I'm Magic Tadaaaa",Toast.LENGTH_SHORT).show();
                return true;

            case(R.id.logout) :

                // Snackbar Testcode
                CoordinatorLayout odinator = (CoordinatorLayout)findViewById(R.id.coordinator_layout);
                FirebaseAuth.getInstance().signOut();

                // SnackBar
              Snackbar snack =  Snackbar.make(odinator,"Logged Out",Snackbar.LENGTH_SHORT);
                snack.setActionTextColor(Color.CYAN);
                View snackbarView = snack.getView();
                snackbarView.setBackgroundColor(Color.BLACK);
                snack.show();

               startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }



   /* private void displayMessages()
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

*/

    @Override
    public void onBackPressed()
    {

        int i = 0 ;

        ++i;

        if(i==1)
        {

            Intent intent = new Intent(getApplicationContext(),ChatList.class);


            intent.putExtra("RUN",group2);

            startActivity(intent);



        }

        Toast.makeText(getApplicationContext(),"This app is gonna kick ass",Toast.LENGTH_SHORT).show();

    }


}
