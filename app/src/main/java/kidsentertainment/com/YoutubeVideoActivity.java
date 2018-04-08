package kidsentertainment.com;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.youtubeplayer.ui.PlayerUIController;

import kidsentertainment.com.database.DbContext;
import kidsentertainment.com.network.NetContext;
import kidsentertainment.com.network.Service;
import kidsentertainment.com.network.models.ListVideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YoutubeVideoActivity extends AppCompatActivity   {
    private ListView listVideo;
    private FullScreenManager fullScreenManager = new FullScreenManager(this);
    private EasyVideoPlayer player;
    private static final String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private String TAG = "VideoActivity";
    YouTubePlayer initializedYouTubePlayer;
    YouTubePlayerView youTubePlayerView;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_youtube_video);
        mInflater = getLayoutInflater();
        youTubePlayerView= (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        listVideo = findViewById(R.id.list_youtube);
        listVideo.setAdapter(new ListVideoAdapter());

        this.getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.initialize(initializedYouTubePlayer -> {
            initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady() {
                    YoutubeVideoActivity.this.initializedYouTubePlayer = initializedYouTubePlayer;
                    try {
                        if (DbContext.getInstance().getListVideoResponse().getData().size() != 0) {
                            String videoId = DbContext.getInstance().getListVideoResponse().getData().get(0).getLink();
                            initializedYouTubePlayer.loadVideo(videoId, 0);
                        }
                    }catch (Exception e){
                        Toast.makeText(YoutubeVideoActivity.this,"Có lỗi khi tải video hoặc danh sách video rỗng !!!",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            addFullScreenListenerToPlayer(initializedYouTubePlayer);
        }, true);
//        youTubePlayerView.getPlayerUIController().showMenuButton(true);
//        youTubePlayerView.initialize(new YouTubePlayerInitListener() {
//            @Override
//            public void onInitSuccess(final YouTubePlayer initializedYouTubePlayer) {
//                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
//                    @Override
//                    public void onReady() {
//                        String videoId = "Kak3Py4dggU";
//                        initializedYouTubePlayer.loadVideo(videoId, 0);
//                    }
//                });
//            }
//        }, true);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        // It's not necessary to call release if you register youTubePlayerView as a lifecycle observer of this Activity.
        youTubePlayerView.release();
    }
    @Override
    public void onPause() {
        super.onPause();
        if (initializedYouTubePlayer != null)
            initializedYouTubePlayer.pause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfiguration) {
        super.onConfigurationChanged(newConfiguration);
        youTubePlayerView.getPlayerUIController().getMenu().dismiss();
    }

    @Override
    public void onBackPressed() {
        if (youTubePlayerView.isFullScreen())
            youTubePlayerView.exitFullScreen();
        else
            super.onBackPressed();
    }

    private void addFullScreenListenerToPlayer(final YouTubePlayer youTubePlayer) {
        youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                fullScreenManager.enterFullScreen();

                addCustomActionToPlayer(youTubePlayer);
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fullScreenManager.exitFullScreen();

                removeCustomActionFromPlayer();
            }
        });
    }

    private void addCustomActionToPlayer(YouTubePlayer youTubePlayer) {
        Drawable icon = ContextCompat.getDrawable(YoutubeVideoActivity.this, R.drawable.ic_pause_36dp);

        youTubePlayerView.getPlayerUIController().setCustomAction1(icon, view -> {
            if(youTubePlayer != null) youTubePlayer.pause();
        });
    }

    private void removeCustomActionFromPlayer() {
        youTubePlayerView.getPlayerUIController().showCustomAction1(false);
    }

//    private void setNextVideoButtonClickListener(final YouTubePlayer youTubePlayer) {
//        nextVideoButton.setOnClickListener(view -> {
//            String videoId = videoIds[new Random().nextInt(videoIds.length)];
//
//            youTubePlayer.loadVideo(videoId, 0);
//            setVideoTitle(youTubePlayerView.getPlayerUIController(), videoId);
//        });
//    }

    /**
     * This method is called every time a new video is being loaded/cued.
     * It uses the YouTube Data APIs to fetch the video title from the video ID.
     * The YouTube Data APIs are nothing more then a wrapper over the YouTube REST API.
     * You can learn more at the following urls:
     * https://developers.google.com/youtube/v3/docs/videos/list
     * https://developers.google.com/apis-explorer/#p/youtube/v3/youtube.videos.list?part=snippet&id=6JYIGclVQdw&fields=items(snippet(title))&_h=9&
     *
     * This method does network operations, therefore it cannot be executed on the main thread.
     * For simplicity I have used RxJava to implement the asynchronous logic. You can use whatever you want: Threads, AsyncTask ecc.
     */
    public class ListVideoAdapter extends BaseAdapter {
        private Context context = null;
        public int positionChoose=0;
        private class ViewHolder {
            public TextView tvNameVideo;

            //public ImageView friendStatus;

            public ViewHolder(View view) {
                tvNameVideo = view.findViewById(R.id.tv_name_video);

                //friendStatus = (ImageView) view.findViewById(R.id.friendStatus);
            }
        }


        public int getCount() {
            return DbContext.getInstance().getListVideoResponse().getData().size();

        }

        @Override
        public Object getItem(int position) {
            if (position >= getCount()) return null;
            try {
                return DbContext.getInstance().getListVideoResponse().getData().get(position);
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
                view = mInflater.inflate(R.layout.list_video_cell, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.tvNameVideo.setText(DbContext.getInstance().getListVideoResponse().getData().get(position).getDescription());
           if (position==positionChoose) holder.tvNameVideo.setTextColor(Color.RED);
            else holder.tvNameVideo.setTextColor(Color.BLACK);
            ViewHolder finalHolder = holder;
            holder.tvNameVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   positionChoose=position;
                    if(initializedYouTubePlayer!=null) {
                        initializedYouTubePlayer.loadVideo(DbContext.getInstance().getListVideoResponse().getData().get(position).getLink(), 0);
                        ListVideoAdapter.this.notifyDataSetChanged();
                    }
                }
            });


            return view;
        }


    }


}

