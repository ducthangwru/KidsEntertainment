package kidsentertainment.com.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuocVietDang1 on 3/25/2018.
 */

public class RegisModel {
    @SerializedName("username")
    public String username;

    @SerializedName("password")
    String password;
    @SerializedName("email")
    public String email;

    @SerializedName("avatar")
    String avatar;
    @SerializedName("tokenfirebase")
    public String tokenfirebase;

    @SerializedName("fullname")
    String fullname;
    @SerializedName("dateofbirth")
    String dateofbirth;

    public RegisModel(String username, String password, String email,  String fullname, String dateofbirth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = "http://gamep.blobla.com/ynghiaava/imgs/intro.jpg";
        this.tokenfirebase = "";
        this.fullname = fullname;
        this.dateofbirth = dateofbirth;
    }
}
