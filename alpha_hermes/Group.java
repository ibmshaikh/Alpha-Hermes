package c.alpha_hermes;

/**
 * Created by zeeshan on 18-06-2017.
 */


public class Group extends Object
{

    private String mGroupname ;

    public String getmGroupname()
    {
        return mGroupname;
    }

    public void setmGroupname(String mGroupname)
    {
        this.mGroupname = mGroupname;
    }

    public Group(String nGroupname)
    {
        this.mGroupname = nGroupname ;
    }

    public Group()
    {
        // Empty Constructor required by Firebase
    }

}