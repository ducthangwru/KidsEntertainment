package kidsentertainment.com.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by QuocVietDang1 on 3/5/2018.
 */

public class LoginResponse {
    @SerializedName("status")
    boolean status;
    @SerializedName("msg")
    String msg;
    @SerializedName("data")
    Data data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public class Data{
        @SerializedName("_id")
        String _id;
        @SerializedName("username")
        String username;
        @SerializedName("password")
        String password;
        @SerializedName("email")
        String email;
        @SerializedName("avatar")
        String avatar;
        @SerializedName("tokenfirebase")
        String tokenfirebase;
        @SerializedName("fullname")
        String fullname;
        @SerializedName("dateofbirth")
        String dateofbirth;
        @SerializedName("status")
        boolean statusDAta;
        @SerializedName("createdAt")
        String createdAt;
        @SerializedName("updatedAt")
        String updatedAt;
        @SerializedName("__v")
        int __v;
        public class Group{
            @SerializedName("_id")
            String _id;
            @SerializedName("groupname")
            String groupname;
            @SerializedName("isadmin")
            String isadmin;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
            }

            public String getIsadmin() {
                return isadmin;
            }

            public void setIsadmin(String isadmin) {
                this.isadmin = isadmin;
            }
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getTokenfirebase() {
            return tokenfirebase;
        }

        public void setTokenfirebase(String tokenfirebase) {
            this.tokenfirebase = tokenfirebase;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getDateofbirth() {
            return dateofbirth;
        }

        public void setDateofbirth(String dateofbirth) {
            this.dateofbirth = dateofbirth;
        }

        public boolean isStatusDAta() {
            return statusDAta;
        }

        public void setStatusDAta(boolean statusDAta) {
            this.statusDAta = statusDAta;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "_id='" + _id + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", tokenfirebase='" + tokenfirebase + '\'' +
                    ", fullname='" + fullname + '\'' +
                    ", dateofbirth='" + dateofbirth + '\'' +
                    ", statusDAta='" + statusDAta + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", __v=" + __v +
                    '}';
        }
    }
    @SerializedName("token")
    String token;
    public class Menu {
        @SerializedName("_id")
        String _id;
        @SerializedName("index")
        int index;
        @SerializedName("mcode")
        String mcode;
        @SerializedName("mname")
        String mname;
        @SerializedName("screen")
        String screen;
        @SerializedName("icon")
        String icon;
        @SerializedName("color")
        String color;
        @SerializedName("visiable")
        String visiable;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getMcode() {
            return mcode;
        }

        public void setMcode(String mcode) {
            this.mcode = mcode;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getScreen() {
            return screen;
        }

        public void setScreen(String screen) {
            this.screen = screen;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getVisiable() {
            return visiable;
        }

        public void setVisiable(String visiable) {
            this.visiable = visiable;
        }

        @Override
        public String toString() {
            return "Menu{" +
                    "_id='" + _id + '\'' +
                    ", index=" + index +
                    ", mcode='" + mcode + '\'' +
                    ", mname='" + mname + '\'' +
                    ", screen='" + screen + '\'' +
                    ", icon='" + icon + '\'' +
                    ", color='" + color + '\'' +
                    ", visiable='" + visiable + '\'' +
                    '}';
        }
    }
    @SerializedName("menus")
    ArrayList<Menu> menus;



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                ", menus=" + menus +
                '}';
    }
}
