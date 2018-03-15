package kidsentertainment.com.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by QuocVietDang1 on 3/14/2018.
 */

public class ListVideoResponse {
    @SerializedName("status")
    public boolean status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public ArrayList<Data> data;
    public class Data{
        @SerializedName("_id")
        public String _id;
        @SerializedName("category")
        public String category;
        @SerializedName("description")
        public String description;
        @SerializedName("link")
        public String link;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "_id='" + _id + '\'' +
                    ", category='" + category + '\'' +
                    ", description='" + description + '\'' +
                    ", link='" + link + '\'' +
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
        return "ListVideoResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
