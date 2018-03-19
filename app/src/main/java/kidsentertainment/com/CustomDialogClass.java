package kidsentertainment.com;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ducth on 3/19/2018.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Button yes, no;
    public ImageView img_QR;
    public String urlImage;

    private static CustomDialogClass instance;

    public static CustomDialogClass getInstance(Activity a, String urlImage) {
        instance = new CustomDialogClass(a, urlImage);
        return instance;
    }

    private CustomDialogClass(Activity a, String urlUmage) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.urlImage = urlUmage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = findViewById(R.id.btn_yes);
        img_QR = findViewById(R.id.img_qr);
        Picasso.get().load(urlImage).into(img_QR);
        yes.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}