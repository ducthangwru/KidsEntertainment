package kidsentertainment.com;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import kidsentertainment.com.database.DbContext;
import kidsentertainment.com.network.NetContext;
import kidsentertainment.com.network.Service;
import kidsentertainment.com.network.models.ListVideoResponse;
import kidsentertainment.com.network.models.TitleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HangMucActivity extends AppCompatActivity {

    private LayoutInflater mInflater;
    private ListView listHangMuc;
    private TextView tvTitle;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_hang_muc);
        mInflater = getLayoutInflater();
        initView();

    }

    private void initView() {
        listHangMuc = this.findViewById(R.id.hangmuclist);
        tvTitle = this.findViewById(R.id.title);
        backBtn = this.findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        listHangMuc.setAdapter(new HangMucAdapter());
    }

    public class HangMucAdapter extends BaseAdapter {
        private static final String TAG = "HangMucAdapter";
        private Context context = null;

        private class ViewHolder {
            public LinearLayout ll;
            public ImageView img_hangmuc;
            public TextView tv_hangmuc;
            public TextView des_hangmuc;

            //public ImageView friendStatus;

            public ViewHolder(View view) {
                ll = view.findViewById(R.id.ll_hangmuc);
                img_hangmuc = view.findViewById(R.id.img_hangmuc);
                tv_hangmuc = view.findViewById(R.id.tv_name);
                des_hangmuc = view.findViewById(R.id.tv_descrip);
                //friendStatus = (ImageView) view.findViewById(R.id.friendStatus);
            }
        }


        public int getCount() {
            Log.d("HangMucAdapter", "getCount: " + DbContext.getInstance().getTitleResponse().getData().size());
            return DbContext.getInstance().getTitleResponse().getData().size();

        }

        @Override
        public Object getItem(int position) {
            if (position >= getCount()) return null;
            try {
                return DbContext.getInstance().getTitleResponse().getData().get(position);
            } catch (Exception e) {
                return null;
            }
        }


        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = null;

//            if (contact == null) return null;
            ViewHolder holder = null;
            if (convertView != null) {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            } else {
                view = mInflater.inflate(R.layout.hangmuc_cell, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }

            Picasso.get().load(DbContext.getInstance().getTitleResponse().getData().get(position).getImage()).into(holder.img_hangmuc);

            holder.tv_hangmuc.setText(DbContext.getInstance().getTitleResponse().getData().get(position).getName());
            holder.des_hangmuc.setText(DbContext.getInstance().getTitleResponse().getData().get(position).getName());
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Service service = NetContext.getInstance().create(Service.class);
                    String listVideoUrl = "categoryDetail?idlogin=" + DbContext.getInstance().getLoginResponse().getData().get_id()
                            + "&idcategory=" + DbContext.getInstance().getTitleResponse().getData().get(position).get_id();
                    service.getListVideo(listVideoUrl).enqueue(new Callback<ListVideoResponse>() {
                        @Override
                        public void onResponse(Call<ListVideoResponse> call, Response<ListVideoResponse> response) {
                            ListVideoResponse listVideoResponse = response.body();
                            if (listVideoResponse.isStatus()) {
                                DbContext.getInstance().setListVideoResponse(listVideoResponse);
                                startActivity(new Intent(HangMucActivity.this, YoutubeVideoActivity.class));
                            } else {
                                Toast.makeText(HangMucActivity.this, listVideoResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ListVideoResponse> call, Throwable t) {

                            Toast.makeText(HangMucActivity.this, "Không có kết nối mạng , vui lòng bật 3g hoặc wifi", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            this.notifyDataSetChanged();
            return view;
        }


    }

}
