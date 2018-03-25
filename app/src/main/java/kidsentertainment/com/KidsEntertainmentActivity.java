package kidsentertainment.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import kidsentertainment.com.database.DbContext;
import kidsentertainment.com.network.NetContext;
import kidsentertainment.com.network.Service;
import kidsentertainment.com.network.models.TitleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KidsEntertainmentActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout ll1, ll2, ll3, ll4;
    private String TAG="KidsEntertainment";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_kids_entertainment);
        getListHangMuc();
        initView();
    }

    private void getListHangMuc() {

    }

    private void initView() {
        ll1 = this.findViewById(R.id.bangchucai);
        ll2 = this.findViewById(R.id.giaitrichotre);
        ll3 = this.findViewById(R.id.bechuphinh);
        ll4 = this.findViewById(R.id.doimatkhau);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bangchucai) {
            startActivity(new Intent(KidsEntertainmentActivity.this,BangChuCaiActivity.class));
        } else if (view.getId() == R.id.doimatkhau) {
        } else if (view.getId() == R.id.giaitrichotre) {
            dialog = ProgressDialog.show(KidsEntertainmentActivity.this, "", "Đang tải..", true, false);

            Service service = NetContext.getInstance().create(Service.class);
            String hangMucUrl="category?idlogin="+DbContext.getInstance().getLoginResponse().getData().get_id()
                    +"&idmenu="+DbContext.getInstance().getLoginResponse().getMenus().get(2).get_id();
            Log.d(TAG, "onClick: "+hangMucUrl);
                    service.getHangMuc(hangMucUrl).enqueue(new Callback<TitleResponse>() {
                        @Override
                        public void onResponse(Call<TitleResponse> call, Response<TitleResponse> response) {
                            Log.d(TAG, "onResponse: "+response.body());
                            try{
                                dialog.cancel();
                            }catch (Exception e){

                            }
                            TitleResponse titleResponse = response.body();
                            if (titleResponse.isStatus() ){
                                DbContext.getInstance().setTitleResponse(titleResponse);
                                startActivity(new Intent(KidsEntertainmentActivity.this,HangMucActivity.class));
                            }else {
                                Toast.makeText(KidsEntertainmentActivity.this,titleResponse.getMsg(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<TitleResponse> call, Throwable t) {
                            try{
                                dialog.cancel();
                            }catch (Exception e){

                            }
                            Toast.makeText(KidsEntertainmentActivity.this,"Không có kết nối mạng , vui lòng bật 3g hoặc wifi",Toast.LENGTH_SHORT).show();

                        }
                    });
        } else if (view.getId() == R.id.hoctienganh) {
            startActivity(new Intent(KidsEntertainmentActivity.this,QRCodeActivity.class));
        } else if (view.getId() == R.id.doimatkhau) {

        }
    }
}
