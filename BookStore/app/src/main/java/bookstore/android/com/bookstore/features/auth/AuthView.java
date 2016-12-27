package bookstore.android.com.bookstore.features.auth;

/**
 * Created by tuannt on 26/12/2016.
 */

public interface AuthView {
    void onRetrieveCurrentUserSuccess();
    void onLoginSuccess(String token, int id);
    void onLoginError();
}
