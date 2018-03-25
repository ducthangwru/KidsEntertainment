package kidsentertainment.com;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import kidsentertainment.com.network.NetContext;
import kidsentertainment.com.network.Service;
import kidsentertainment.com.network.models.LoginModel;
import kidsentertainment.com.network.models.RegisModel;
import kidsentertainment.com.network.models.RegisResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText etUserName, etPassWord, etVerifyPassWord, etEmail, etFullName;
    TextView tvBirthDate;
    Button btnRegis;
    private ProgressDialog registserDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etUserName = findViewById(R.id.regisUsername);
        etPassWord = findViewById(R.id.regisPassword);
        etVerifyPassWord = findViewById(R.id.regisVerifyPass);
        etEmail = findViewById(R.id.regisEmail);
        etFullName = findViewById(R.id.regisFullname);
        tvBirthDate = findViewById(R.id.datebirth);
        btnRegis = findViewById(R.id.btnRegis);
        tvBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regisAct();
            }
        });
    }

    private void regisAct() {

        if (etEmail.getText().toString().equals("") ||
                etFullName.getText().toString().equals("") ||
                etPassWord.getText().toString().equals("") ||
                etVerifyPassWord.getText().toString().equals("") ||
                etUserName.getText().toString().equals("") ||
                tvBirthDate.getText().toString().equals("")) {
            Toast.makeText(this, "Thông tin đăng ký không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            if (!etVerifyPassWord.getText().toString().equals(etPassWord.getText().toString())) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            } else {
                registserDialog = ProgressDialog.show(RegisterActivity.this, "", "Đang đăng ký...", true, false);
                RegisModel regisModel = new RegisModel(etUserName.getText().toString(),
                        etPassWord.getText().toString(),
                        etEmail.getText().toString(),
                        etFullName.getText().toString(),
                        tvBirthDate.getText().toString());
                Gson gson = new Gson();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody requestBody = RequestBody.create(mediaType, gson.toJson(regisModel));
                Service regisService = NetContext.getInstance().create(Service.class);
                regisService.register(requestBody). enqueue(new Callback<RegisResponse>() {
                    @Override
                    public void onResponse(Call<RegisResponse> call, Response<RegisResponse> response) {
                        try {
                            registserDialog.cancel();
                        } catch (Exception e) {

                        }
                        if (response!=null){
                            RegisResponse regisResponse = response.body();
                            if (regisResponse.isStatus()){
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }else {
                                Toast.makeText(RegisterActivity.this, regisResponse.getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisResponse> call, Throwable t) {
                        try {
                            registserDialog.cancel();
                        } catch (Exception e) {

                        }
                        Toast.makeText(RegisterActivity.this, "Không có kết nối mạng , vui lòng bật 3g hoặc wifi", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    }

    public void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                RegisterActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        tvBirthDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
    }
}
