package kidsentertainment.com;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import kidsentertainment.com.objects.ResultQRCodeObject;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BangChuCaiActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler,
        TextToSpeech.OnInitListener{

    private ZXingScannerView zXingScannerView;
    private Gson gson = new Gson();
    private TextToSpeech tts;
    private CustomDialogClass cdd;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_chu_cai);
        zXingScannerView = findViewById(R.id.qrView_ChuCai);
        imageView = findViewById(R.id.image_QR);
        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.ENGLISH);
        tts.setSpeechRate((float) 0.9);
        scan();
    }

    public void scan(){
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        try
        {
            ResultQRCodeObject obj = gson.fromJson(result.getText(), ResultQRCodeObject.class);
//            cdd = CustomDialogClass.getInstance(QRCodeActivity.this, obj.url);
//            cdd.show();
            if(obj.text != null)
            {
                Picasso.get().load(obj.url).into(imageView);
                Toast.makeText(getApplicationContext(),obj.text, Toast.LENGTH_SHORT).show();
                tts.speak(obj.text, TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Không đúng định dạng QRCode",Toast.LENGTH_SHORT).show();
            }

            Thread.sleep(1000);
            zXingScannerView.resumeCameraPreview(this);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Không đúng định dạng QRCode",Toast.LENGTH_SHORT).show();
            zXingScannerView.resumeCameraPreview(this);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
