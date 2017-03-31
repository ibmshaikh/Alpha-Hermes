package c.alpha_hermes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by zeeshan on 19-03-2017.
 */




public class CreateGroup extends AppCompatActivity {


    private EditText mGroupName ;
    private EditText mUserName  ;
    private Button mCreateGroupButton;
    private FirebaseListAdapter<User> mFirebaseListAdapter;
    private DatabaseReference mDatabaseReference;
    private ListView mListView;



    @Override
    public void onCreate(Bundle savedInstanceState)

    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt);

    mGroupName = (EditText)findViewById(R.id.GroupName);
        mUserName = (EditText)findViewById(R.id.UserName);
        mCreateGroupButton = (Button)findViewById(R.id.CreateGroupButton);




        mCreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  Intent i = new Intent(CreateGroup.this,MainActivity.class);

               i.putExtra("USERNAME",mUserName.getText().toString());
                 i.putExtra("GROUPNAME",mGroupName.getText().toString());




                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(mGroupName.getText().toString());



                mDatabaseReference.push().setValue(new User(mGroupName.getText().toString()));

                 startActivity(i);




            }
        });



    }



}
