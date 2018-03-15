package kidsentertainment.com.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by QuocVietDang1 on 3/14/2018.
 */

public class TitleResponse {
    @SerializedName("status")
    boolean status;
    @SerializedName("msg")
    String msg;
    @SerializedName("data")
    ArrayList<Data> data= new ArrayList<>();
    public class Data{
        @SerializedName("_id")
        String _id;
        @SerializedName("menu")
        String menu;
        @SerializedName("name")
        String name;
        @SerializedName("image")
        String image;
        @SerializedName("description")
        String description;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "_id='" + _id + '\'' +
                    ", menu='" + menu + '\'' +
                    ", name='" + name + '\'' +
                    ", image='" + image + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TitleResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
