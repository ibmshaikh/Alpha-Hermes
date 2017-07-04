package c.alpha_hermes.dummy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import c.alpha_hermes.ChatList;
import c.alpha_hermes.CreateGroup;
import c.alpha_hermes.MainActivity;
import c.alpha_hermes.R;

import static android.Manifest.permission.READ_CONTACTS;



/**
 * A login screen that offers login via email/password.
 */


public class LoginActivity extends AppCompatActivity  {


    private EditText mPassword ;
    private EditText mEmail ;
    private ProgressDialog mProgressDialog ;
    private Button mloginButton ;
    private  FirebaseAuth mAuth ;
    private FirebaseAuth.AuthStateListener mAuthListener ;
    private static final int RC_SIGN_IN = 1;


    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         mAuth = FirebaseAuth.getInstance();


        mPassword = (EditText)findViewById(R.id.password);
        mEmail = (EditText)findViewById(R.id.email);
        mloginButton = (Button)findViewById(R.id.email_sign_in_button);



         mloginButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View view) {



            // mProgressDialog.setMessage("Signing UP ...Please Wait");
                 //   mProgressDialog.show();



                  String pass = mPassword.getText().toString();
                  String email = mEmail.getText().toString();




                 mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                //         mProgressDialog.dismiss();
                         if(task.isSuccessful())
                         {

                             Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_SHORT).show();


                             startActivity(new Intent(getApplicationContext(),CreateGroup.class));

               //     startActivity(new Intent(getApplicationContext(),CreateGroup.class));


                         }
                         else
                         {
                             Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                         }



                     }
                 });

                 // TODO check
                 //   startActivity(new Intent(getApplicationContext(),CreateGroup.class));

             }
         });



    }



}

