package c.alpha_hermes;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by zeeshan on 17-03-2017.
 */

public class InstanceIDService extends FirebaseInstanceIdService {



    public static final String TAG = "MyFirebaseIdService";

    @Override
    public void onTokenRefresh()
    {


        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"  "+token);



    }



}
