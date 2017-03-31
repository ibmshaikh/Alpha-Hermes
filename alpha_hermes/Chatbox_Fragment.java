package c.alpha_hermes;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



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




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Context mContext ;


    //VIEW CONTENTS


    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;

    private ChildEventListener mChildEventListener;
    private FirebaseListAdapter<Messages> adapter;



    private ListView listView;
    private EditText mEditText;
    private Button mbutton;



  private   String group ,human;





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
        if (getArguments() != null) {
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chatbox_, container, false);


        mbutton = (Button)v.findViewById(R.id.messageSendButton);
        mEditText = (EditText)v.findViewById(R.id.EditMessage) ;
        listView = (ListView)v.findViewById(R.id.listView);
        mFirebaseDatabase = FirebaseDatabase.getInstance();


      // final String name = getIntent().getStringExtra("USERNAME");

        String group = getArguments().getString("G3");

      //  Log.e("The","The name of the Group is"+group);


        try {


            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(group);


        }catch (NullPointerException e )
        {


        //    mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Messages");


        }



        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabaseReference.push()
                        .setValue(new Messages(mEditText.getText().toString(),
                                "Zeeshan"));


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
