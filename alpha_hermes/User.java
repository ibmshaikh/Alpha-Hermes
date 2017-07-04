package c.alpha_hermes;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by zeeshan on 17-03-2017.
 */

public class User extends  Object {



    private String mName;
    private String uId;


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }


    public User(String mName,String UID)
    {
        this.mName = mName;
        this.uId = UID;

    }


    public void setmName(String mName)
    {
        this.mName = mName;
    }

    public  User()
    {

    }


    public User(String mName) {

        this.mName = mName;
    }

    public String getmName() {

        return mName;
    }


}
