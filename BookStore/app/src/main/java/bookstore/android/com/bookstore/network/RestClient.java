package bookstore.android.com.bookstore.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 19/12/2016.
 */

public class RestClient {
    public static String url = "http://android-samsung.herokuapp.com/api/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
    public static Retrofit getClient(String url){
        if (url != null){
            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}