package kidsentertainment.com.network;




import kidsentertainment.com.network.models.ListVideoResponse;
import kidsentertainment.com.network.models.LoginResponse;
import kidsentertainment.com.network.models.TitleResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by hanhi on 7/14/2017.
 */

public interface Service {

    @POST("user/login")
    Call<LoginResponse> login(@Body RequestBody body);
    @GET
    Call<TitleResponse> getHangMuc(@Url String url);
    @GET
    Call<ListVideoResponse> getListVideo(@Url String url);


}
