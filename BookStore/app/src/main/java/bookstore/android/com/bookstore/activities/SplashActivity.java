package bookstore.android.com.bookstore.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.squareup.picasso.Picasso;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.databinding.ActivitySplashBinding;
import bookstore.android.com.bookstore.features.auth.AuthPresenter;
import bookstore.android.com.bookstore.features.auth.AuthView;
import bookstore.android.com.bookstore.features.auth.Token;
import bookstore.android.com.bookstore.models.User;
import bookstore.android.com.bookstore.utils.DataController;

public class SplashActivity extends AppCompatActivity implements AuthView, View.OnClickListener {

    private ActivitySplashBinding mSplashBinding;

    private AuthPresenter mAuthPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mSplashBinding.loginButton.setTag(R.string.login_button);
        mSplashBinding.loginButton.setOnClickListener(this);
        mAuthPresenter = new AuthPresenter(this);
        mAuthPresenter.getProfileTracker().startTracking();
        if (mAuthPresenter.checkAuth(this))
            mAuthPresenter.retrieveCurrentUser(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAuthPresenter.getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        switch (tag) {
            case R.string.login_button:
                mSplashBinding.loginProgressBar.setVisibility(View.VISIBLE);
                mSplashBinding.loginButton.setEnabled(false);
                mAuthPresenter.login(this);
                break;
            case R.string.continue_button:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRetrieveCurrentUserSuccess() {
        Picasso.with(this).load(DataController.user.getAvatar()).into(mSplashBinding.avatarImageView);
        mSplashBinding.nameTextView.setText("Welcome, " + DataController.user.getName());
        mSplashBinding.loginButton.setText(R.string.continue_button);
        mSplashBinding.loginButton.setTag(R.string.continue_button);
        mSplashBinding.loginButton.setEnabled(true);
    }

    @Override
    public void onLoginSuccess(String token, int id) {
        mSplashBinding.loginProgressBar.setVisibility(View.GONE);
        Token.store(this, token, id);
        mAuthPresenter.retrieveCurrentUser(this);
    }

    @Override
    public void onLoginError() {
        mAuthPresenter.logout();
        mSplashBinding.loginButton.setEnabled(true);
        Toast.makeText(this, "Đã xảy ra lỗi! Vui lòng thử lại sau", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAuthPresenter.getProfileTracker().stopTracking();
    }
}
