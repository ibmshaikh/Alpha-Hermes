package c.alpha_hermes;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Chatbox_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Chatbox_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */



public class Chatbox_Fragment extends Fragment

{




  // UserLists
    DatabaseReference mUsersReference ;
    FirebaseListAdapter<Test> mUsserListAdapter;
    FirebaseDatabase mUserDatabase ;


    private static final int RC_SIGN_IN  =  1 ;



// TODO APP DRAWER VARIABLES
    private Context mContext ;
    private DrawerLayout mDrawerLayout ;
    private View mDrawerView ;
    private ListView userListView ;
    private ArrayList<String> mUserArrayList ;
    private ArrayAdapter<String> mUserArrayAdapter ;



    //VIEW CONTENTS
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;
    private ChildEventListener mChildEventListener;
    private FirebaseListAdapter<Messages> adapter;



    private FirebaseAuth mAuth ;
    private FirebaseAuth.AuthStateListener mAuthListener;



    private ListView listView;
    private EditText mEditText;
    private Button mbutton;



    private String group ,human;
    private OnFragmentInteractionListener mListener;





    public Chatbox_Fragment() {

        // Required empty public constructor
    }

   // // TODO: Rename and change types and number of parameters




    public static Chatbox_Fragment newInstance() {
        Chatbox_Fragment fragment = new Chatbox_Fragment();
        Bundle args = new Bundle();


        args.putString("GROUPY","Yadav");

        args.putString("PEOPPY","Hai");

        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View v = inflater.inflate(R.layout.fragment_chatbox_, container, false);


        final String group = getArguments().getString("G3");

        final  String human = getArguments().getString("H3");

        final String Rick = getArguments().getString("R3");




        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View promptsView = layoutInflater.inflate(R.layout.user_name,null);
        //userListView = (ListView)v.findViewById(R.id.drawerlist);

      //  mDrawerLayout = (DrawerLayout)v.findViewById(R.id.drawer_layout);

        /*    ALERT DIALOG CODE    */

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(getContext());

        alertdialog.setView(promptsView);

        final EditText getname = (EditText)promptsView.findViewById(R.id.userNameEdit);

        alertdialog.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

                // Test code

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                mUsersReference = FirebaseDatabase.getInstance().getReference().child("UTest").child(group);

                // mUsersReference.push().setValue(new Test(userId));

                 mUsersReference.push().setValue(new Test(getname.getText().toString(),userId));


                Intent intent = new Intent(getContext(),ChatUser.class);
                intent.putExtra("LALABHAI",userId);



                //Working Code
                // Intent intent = new Intent(getActivity(),ChatUser.class);
                //  String UserName = getname.getText().toString();
                // mUsersReference = FirebaseDatabase.getInstance().getReference().child("UTest").child(group);







                 //   mUsersReference.push().setValue(new Test(UserName));


                //  mUsserListAdapter.notifyDataSetChanged();

                // intent.putExtra("RICKALL",getname.getText().toString());


               // startActivity(intent);


            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        AlertDialog alertDialoga = alertdialog.create();

        alertDialoga.show();





        User user = new User();

        String mUser = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // TODO Create a list of uers engaged in a group

        mbutton = (Button)v.findViewById(R.id.messageSendButton);
        mEditText = (EditText)v.findViewById(R.id.EditMessage) ;
        listView = (ListView)v.findViewById(R.id.listView);
        mFirebaseDatabase = FirebaseDatabase.getInstance();



        // final String name = getIntent().getStringExtra("USERNAME");









      //  Log.e("The","The name of the Group is"+group);

        try {


            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("GROUPPY").child(group);


        }
        catch (NullPointerException e )
        {


        //    mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Messages");


        }



        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(human==null)
                {


                    mDatabaseReference.push().setValue(new Messages(mEditText.getText().toString(), getname.getText().toString()));


                }
                else{

                    mDatabaseReference.push().setValue(new Messages(mEditText.getText().toString(),human.toString()));

                    }
              //  mDatabaseReference.push().setValue(new Messages(mEditText.getText().toString()),"IBM");

                mEditText.setText("");


            }
        });














        listView = (ListView)v.findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<Messages>(getActivity(),Messages.class,R.layout.messages,mDatabaseReference) {

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


        return  v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {

        // TODO: Update argument type and name


        void onFragmentInteraction(Uri uri);
    }






}
