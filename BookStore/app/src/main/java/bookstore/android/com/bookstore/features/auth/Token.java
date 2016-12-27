package bookstore.android.com.bookstore.features.auth;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tuannt on 27/12/2016.
 */

public class Token {

    private static final String PREFS_NAME = "APP_PREFS";

    private static final String PREFS_KEY_TOKEN = "USER_TOKEN";

    private static final String PREFS_KEY_ID = "USER_ID";

    public static void store(Context context, String token, int id) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit();
        editor.putString(PREFS_KEY_TOKEN, token);
        editor.putInt(PREFS_KEY_ID, id);
        editor.apply();
    }

    public static String retrieveToken(Context context) {
        SharedPreferences settings;
        String token;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        token = settings.getString(PREFS_KEY_TOKEN, null);
        return token;
    }

    public static int retrieveId(Context context) {
        SharedPreferences settings;
        int id;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id = settings.getInt(PREFS_KEY_ID, 0);
        return id;
    }

}
