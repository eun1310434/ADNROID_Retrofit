package retrofit.eun1310434.com.retrofit;

/**
 * Created by ddil on 25-Sep-18.
 */

public class Item {
    String login;
    String html_url;
    int contributions;

    @Override
    public String toString(){
        return login + "(" + contributions +")";
    }

}
