package kidsentertainment.com.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuocVietDang1 on 3/25/2018.
 */

public class RegisResponse {
    @SerializedName("status")
    public boolean status;

    @SerializedName("msg")
    String msg;

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

    @Override
    public String toString() {
        return "RegisResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
