package c.alpha_hermes;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by zeeshan on 28-03-2017.
 */

public class UserLab {




    private ArrayList<User> mUsers;
    private static UserLab suserLab;
    private Context mAppContext;




    private UserLab(Context c)
    {

        mAppContext= c;

    }



    private static UserLab get(Context c)
    {
        if(suserLab==null)
        {
            suserLab = new UserLab(c.getApplicationContext());
        }

        return suserLab;
    }



    public ArrayList<User> getmUsers()
    {
        return mUsers ;
    }







}
