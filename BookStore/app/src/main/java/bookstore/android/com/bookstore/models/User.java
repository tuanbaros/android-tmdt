package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class User {
    @SerializedName("user_id")
    private int userId;
    private String fbId;
    private String name;
    private String avatar;
    private String accesstoken;
    @SerializedName("user_token")
    private String userToken;

    public User(int userId){
        this.userId = userId;
    }
    public User(){
    }
    public User(int userId, String userToken){
        this.userId = userId;
        this.userToken = userToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public class CallBackUser{
        @SerializedName("user_id")
        private int userId;
        @SerializedName("user_token")
        private String userToken;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }
    }
}
