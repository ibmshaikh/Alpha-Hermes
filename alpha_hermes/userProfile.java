package c.alpha_hermes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class userProfile extends AppCompatActivity {

    private TextView mUserName  ;
    private Button mChangeName  ;
    private Button mChangeImage  ;
    private CircleImageView mProfilePicture ;
    private StorageReference mStorageReference ;
    private DatabaseReference mDatabaseReference ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mUserName = (TextView)findViewById(R.id.userNaam);
        mChangeImage = (Button)findViewById(R.id.changeDp);
        mChangeName = (Button)findViewById(R.id.changeNaam);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();








    }
}
