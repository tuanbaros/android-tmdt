package bookstore.android.com.bookstore.features.auth;

import android.app.Activity;
import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import bookstore.android.com.bookstore.models.User;
import bookstore.android.com.bookstore.utils.DataController;

/**
 * Created by tuannt on 26/12/2016.
 */

public class AuthPresenter implements FacebookCallback<LoginResult> {

    private AuthView mAuthView;

    private CallbackManager mCallbackManager;

    public AuthPresenter(AuthView aAuthView) {
        mCallbackManager = CallbackManager.Factory.create();
        this.mAuthView = aAuthView;
    }

    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }

    public void login(Activity activity) {
        LoginManager.getInstance().registerCallback(mCallbackManager, this);
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
    }

    public void logout() {

    }

    public boolean checkAuth(Context context) {
        Profile profile = Profile.getCurrentProfile();
        return profile != null && Token.retrieveToken(context) != null;
    }

    public void retrieveCurrentUser(Context context) {
        Profile profile = Profile.getCurrentProfile();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        DataController.user = new User();
        DataController.user.setFbId(profile.getId());
        DataController.user.setName(profile.getName());
        DataController.user.setAccesstoken(accessToken.getToken());
        DataController.user.setAvatar(profile.getProfilePictureUri(100, 100).toString());
        DataController.user.setUserToken(Token.retrieveToken(context));
        DataController.user.setUserId(Token.retrieveId(context));
        mAuthView.onRetrieveCurrentUserSuccess();

    }

    private void getToken() {
        Profile profile = Profile.getCurrentProfile();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        AndroidNetworking.post("http://android-samsung.herokuapp.com/api/login")
                .addBodyParameter("facebookId", profile.getId())
                .addBodyParameter("name", profile.getName())
                .addBodyParameter("avatar", profile.getProfilePictureUri(200, 200).toString())
                .addBodyParameter("fbToken", accessToken.getToken())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .doNotCacheResponse()
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mAuthView.onLoginSuccess(response.getString("user_token"), response.getInt("user_id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mAuthView.onLoginError();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        mAuthView.onLoginError();
                    }
                });
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        getToken();
    }

    @Override
    public void onCancel() {
        mAuthView.onLoginError();
    }

    @Override
    public void onError(FacebookException error) {
        mAuthView.onLoginError();
    }
}
