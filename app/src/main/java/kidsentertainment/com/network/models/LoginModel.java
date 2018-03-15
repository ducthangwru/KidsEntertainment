package kidsentertainment.com.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuocVietDang1 on 3/5/2018.
 */

public class LoginModel {
    @SerializedName("username")
    public String username;

    @SerializedName("password")
    String password;

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
