package c.alpha_hermes;

/**
 * Created by zeeshan on 21-06-2017.
 */

public class Test {

    private String mUser;
    private String UUID ;


    public Test(String mUser, String UUID) {
        this.mUser = mUser;
        this.UUID = UUID;
    }

    public String getUUID() {

        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }


    public Test()
    {

    }


    public  Test(String mName)
    {
        this.mUser = mName;

    }


}
