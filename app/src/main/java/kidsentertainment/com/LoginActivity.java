package kidsentertainment.com;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import kidsentertainment.com.database.DbContext;
import kidsentertainment.com.network.NetContext;
import kidsentertainment.com.network.Service;
import kidsentertainment.com.network.models.LoginModel;
import kidsentertainment.com.network.models.LoginResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity implements View.OnClickListener {

    private String TAG="LoginActivity";
    private String DBPREFS="Database";
    EditText etUserName;
    EditText etPassWord;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: "+ FirebaseInstanceId.getInstance().getToken());
//

        initView();


    }

    private void initView() {
        loginBtn = this.findViewById(R.id.btnLogin);
        etUserName = this.findViewById(R.id.username);
        etPassWord = this.findViewById(R.id.password);
        loginBtn.setOnClickListener(this);
        etUserName.setText(getSharedPreferences(DBPREFS,MODE_PRIVATE).getString("username",""));
        etPassWord.setText(getSharedPreferences(DBPREFS,MODE_PRIVATE).getString("password",""));
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnLogin){
            Log.d(TAG, "onClick: ");
            loginAct();
        }
    }

    private void loginAct() {
        getSharedPreferences(DBPREFS,MODE_PRIVATE).edit().putString("username",etUserName.getText().toString()).commit();
        getSharedPreferences(DBPREFS,MODE_PRIVATE).edit().putString("password",etPassWord.getText().toString()).commit();
        LoginModel loginModel = new LoginModel(etUserName.getText().toString(),etPassWord.getText().toString());
        Gson gson = new Gson();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,gson.toJson(loginModel));
        Service loginService = NetContext.getInstance().create(Service.class);
        loginService.login(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, "onResponse: "+response.body());
                LoginResponse loginResponse = response.body();

                if (loginResponse.isStatus()) {
                    DbContext.getInstance().setLoginResponse(loginResponse);
                    startActivity(new Intent(LoginActivity.this,KidsEntertainmentActivity.class));

                }else {
                    Toast.makeText(LoginActivity.this,loginResponse.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Không có kết nối mạng , vui lòng bật 3g hoặc wifi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
