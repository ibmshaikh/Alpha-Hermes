package c.alpha_hermes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * Created by zeeshan on 19-03-2017.
 */




public class CreateGroup extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1 ;
    private EditText mGroupName ;
    private EditText mUserName  ;
    private Button mCreateGroupButton;
    private FirebaseListAdapter<User> mFirebaseListAdapter;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAdminReference ;
    private ListView mListView;
    private FirebaseDatabase firebaseDatabase ;



    private FirebaseAuth mAuth ;

    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt);

        final String mGroupuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

         mGroupName = (EditText)findViewById(R.id.GroupName);
        mUserName = (EditText)findViewById(R.id.UserName);
        mCreateGroupButton = (Button)findViewById(R.id.CreateGroupButton);


        mCreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Test Code




                // Base code
                  Intent i = new Intent(CreateGroup.this,MainActivity.class);

                    i.putExtra("USERNAME",mUserName.getText().toString());
                    i.putExtra("GROUPNAME",mGroupName.getText().toString());

              //  Group group = new Group();
               // group.setmGroupname(mGroupName.getText().toString());


                User user = new User();
                user.setmName(mGroupName.getText().toString());



                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("USERS").child(mGroupuser);


                // Small Plan
               HashMap<String,String> map = new HashMap<String, String>();
                map.put("admin",mGroupuser.toString());
                mAdminReference = FirebaseDatabase.getInstance().getReference() ;
               mAdminReference.child("UTest").child(mGroupName.getText().toString()).setValue(map);



                mDatabaseReference.push().setValue(user);

             Toast.makeText(getApplicationContext(),"Group Created ",Toast.LENGTH_SHORT).show();

                startActivity(i);

            }
        });



    }


    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(getApplicationContext(),ChatList.class);
        startActivity(i);

    }


}
