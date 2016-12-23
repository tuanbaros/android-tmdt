package bookstore.android.com.bookstore.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;

import bookstore.android.com.bookstore.R;

public class Login extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProfilePictureView profilePictureView;
    private LinearLayout infoLayout;
    private TextView name;

    private ProfileTracker mProfileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        name=(TextView)findViewById(R.id.name);
        infoLayout = (LinearLayout) findViewById(R.id.layout_info);
        profilePictureView = (ProfilePictureView) findViewById(R.id.image);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    String token=AccessToken.getCurrentAccessToken().toString();
                    Log.v("main",token);
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    Intent i=new Intent(Login.this,MainActivity.class);
                                    i.putExtra("jsondata",object.toString());
                                    startActivity(i);

                                    finish();
//                                    setProfileToView(object);
                                }
                            }
                    );
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,gender");
                    request.setParameters(parameters);
                    request.executeAsync();
                    Toast.makeText(Login.this, "Success", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Login attempt canceled.", Toast.LENGTH_LONG).show();
                LoginManager.getInstance().logOut();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Login.this, "error", Toast.LENGTH_LONG).show();
            }
        });

//        mProfileTracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//                if (currentProfile != null) {
//                    name.setText(currentProfile.getName());
//                    profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
//                    profilePictureView.setProfileId(currentProfile.getId());
//                }
//            }
//        };
//        mProfileTracker.startTracking();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mProfileTracker.stopTracking();
    }

    private void setProfileToView(JSONObject object) {
        try {
            name.setText(object.getString("name"));
            profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
            profilePictureView.setProfileId(object.getString("id"));
            infoLayout.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
