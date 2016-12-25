package bookstore.android.com.bookstore.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.models.User;
import bookstore.android.com.bookstore.utils.DataController;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProfileTracker mProfileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
//                if (AccessToken.getCurrentAccessToken() != null) {
//                    String token=AccessToken.getCurrentAccessToken().toString();
//                    Log.v("main",token);
//                    GraphRequest request = GraphRequest.newMeRequest(
//                            loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                                @Override
//                                public void onCompleted(JSONObject object, GraphResponse response) {
//                                    try {
//                                        String token=loginResult.getAccessToken().getToken();
//                                        int id=object.getInt("id");
//                                        String name=object.getString("name");
//                                        String avatarId=object.getString("");
//                                        Intent i=new Intent(LoginActivity.this,MainActivity.class);
//                                        i.putExtra("jsondata",object.toString());
//                                        startActivity(i);
//
//                                        finish();
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }
//
//                                }
//                            }
//                    );
//                    Bundle parameters = new Bundle();
//                    parameters.putString("fields", "id,name,gender");
//                    request.setParameters(parameters);
//                    request.executeAsync();
//                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
//                }
                //n ko vao ham nay ak co vao.t vua test r.

                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        if (currentProfile != null) {
                            String token=loginResult.getAccessToken().getToken();
                            DataController.user=new User();
                            DataController.user.setAvatar(currentProfile.getProfilePictureUri(30,30)+"");
                            DataController.user.setFbId(currentProfile.getId());
                            DataController.user.setName(currentProfile.getName());
                            //chac sai cai nay r
                            Call<User.CallBackUser> callpostUser = DataController.apiBookStore.updateUser(currentProfile.getId(),currentProfile.getName(),
                                    ""+currentProfile.getProfilePictureUri(30,30),token);
                            callpostUser.enqueue(new Callback<User.CallBackUser>() {
                                @Override
                                public void onResponse(Response<User.CallBackUser> response, Retrofit retrofit) {
                                    if(response.isSuccess()){
                                        Log.e("sss","response.getUserToken = "+response.body().getUserToken());
                                        DataController.user.setUserId(response.body().getUserId());
                                        DataController.user.setUserToken(response.body().getUserToken());
                                        Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_LONG).show();
//no chay dc bao day chua chua. no chay dc vao cho nao r og no startactivity nhung no eo show toast.ok
                                        //cais cho login vao dc va lay thong tin ve dau tien la ow cho nao?
// cais eo j day nhi? og thu push len de t xem nao

        //thu chay di og ok og dang nhap thu cos hinen j ko?dn roi day
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Log.e("sss","err = "+t);
                                    Toast.makeText(LoginActivity.this,"failure"+t,Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                };
                mProfileTracker.startTracking();
//                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "LoginActivity attempt canceled.", Toast.LENGTH_LONG).show();
                LoginManager.getInstance().logOut();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProfileTracker.stopTracking();
    }

//    private void setProfileToView(JSONObject object) {
//        try {
//            name.setText(object.getString("name"));
//            profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
//            profilePictureView.setProfileId(object.getString("id"));
//            infoLayout.setVisibility(View.VISIBLE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
