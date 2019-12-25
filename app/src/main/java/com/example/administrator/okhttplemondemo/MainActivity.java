package com.example.administrator.okhttplemondemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lemonlibrary.db.util.PermissionUtils;
import com.example.lemonokhttp.enums.DownloadStatus;
import com.example.lemonokhttp.http.OkHttpLemon;
import com.example.lemonokhttp.interfaces.IDataListener;
import com.example.lemonokhttp.interfaces.IDownloadCallback;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public String tabaoUrl = "http://download.apk8.com/soft/2015/%E6%B7%98%E5%AE%9D.apk";
    public String wpsUrl = "http://gdown.baidu.com/data/wisegame/8be18d2c0dc8a9c9/WPSOffice_177.apk";
    /**
     * WPS下载
     */
    private Button mDownApk1;
    private ProgressBar mDownProgress1;
    private TextView mDownSpeed1;
    private ImageView mPlayOrPause1;
    private ImageView mCancel1;
    private FrameLayout mControlBoard1;
    /**
     * 淘宝下载
     */
    private Button mDownApk2;
    private ProgressBar mDownProgress2;
    private TextView mDownSpeed2;
    private ImageView mPlayOrPause2;
    private ImageView mCancel2;
    private FrameLayout mControlBoard2;
    /**
     * get获取json数据
     */
    private Button mButtonGet;
    private TextView mTvShowGet;
    /**
     * post获取json数据
     */
    private Button mButtonPost;
    private TextView mTvShowPost;

    boolean playOrPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtils.getInstance().requestPermission(this);
        initView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.getInstance().onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    private void initView() {
        mDownApk1 = (Button) findViewById(R.id.down_apk_1);
        mDownApk1.setOnClickListener(this);
        mDownProgress1 = (ProgressBar) findViewById(R.id.down_progress_1);
        mDownSpeed1 = (TextView) findViewById(R.id.down_speed_1);
        mPlayOrPause1 = (ImageView) findViewById(R.id.play_or_pause_1);
        mPlayOrPause1.setOnClickListener(this);
        mCancel1 = (ImageView) findViewById(R.id.cancel_1);
        mCancel1.setOnClickListener(this);
        mControlBoard1 = (FrameLayout) findViewById(R.id.control_board_1);
        mDownApk2 = (Button) findViewById(R.id.down_apk_2);
        mDownApk2.setOnClickListener(this);
        mDownProgress2 = (ProgressBar) findViewById(R.id.down_progress_2);
        mDownSpeed2 = (TextView) findViewById(R.id.down_speed_2);
        mPlayOrPause2 = (ImageView) findViewById(R.id.play_or_pause_2);
        mPlayOrPause2.setOnClickListener(this);
        mCancel2 = (ImageView) findViewById(R.id.cancel_2);
        mCancel2.setOnClickListener(this);
        mControlBoard2 = (FrameLayout) findViewById(R.id.control_board_2);
        mButtonGet = (Button) findViewById(R.id.button_get);
        mButtonGet.setOnClickListener(this);
        mTvShowGet = (TextView) findViewById(R.id.tv_show_get);
        mButtonPost = (Button) findViewById(R.id.button_post);
        mButtonPost.setOnClickListener(this);
        mTvShowPost = (TextView) findViewById(R.id.tv_show_post);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.down_apk_1:
                playOrPause=true;
                mPlayOrPause1.setImageResource(R.drawable.ic_media_pause);
                mDownProgress1.setVisibility(View.VISIBLE);
                mControlBoard1.setVisibility(View.VISIBLE);
                OkHttpLemon.init()
                        .url(wpsUrl)
                        .downFilePath(Environment.getExternalStorageDirectory() + "/wps.apk")
                        .executeDown(new IDownloadCallback() {
                            @Override
                            public void onDownTotalLength(long totalLen) {

                            }

                            @Override
                            public void onDownCurrentLenChange(long alreadyDownLen, double downPercent, long speed) {
                                Log.i("tag00","-----已下载  "+ alreadyDownLen/1024/1024+"M  下载长度  "+downPercent*100 +"%   "+"下载速度："+ speed/1000 +"k/s");
                                mDownProgress1.setProgress((int)(downPercent*100));
                                mDownSpeed1.setText(speed/1000 +"k/s");
                            }

                            @Override
                            public void onFinish(long alreadyDownLen, long totalLen, String stratTime, String finishTime) {
                                Log.i("tag00","下载成功。" + stratTime + "  "+finishTime);
                                mDownProgress1.setVisibility(View.GONE);
                                mControlBoard1.setVisibility(View.GONE);
                                mDownSpeed1.setText("下载成功");
                            }


                            @Override
                            public void onEorror(int errorCode, String ts) {
                                Log.i("tag00","下载异常："+"  errorCode：  " + errorCode +" errorMsg " +ts);

                            }

                            @Override
                            public void onDownStatusChange(DownloadStatus downStatus) {
                                Log.i("tag00","状态变更："+downStatus);
                            }
                        });
                break;
            case R.id.play_or_pause_1:
                if (playOrPause){
                    mPlayOrPause1.setImageResource(R.drawable.ic_media_play);
                    OkHttpLemon.init().pause(wpsUrl);
                    playOrPause=false;
                }else {
                    mPlayOrPause1.setImageResource(R.drawable.ic_media_pause);
                    OkHttpLemon.init().start(wpsUrl);
                    playOrPause=true;
                }
                break;
            case R.id.cancel_1:
                break;
            case R.id.down_apk_2:
                playOrPause=true;
                mPlayOrPause2.setImageResource(R.drawable.ic_media_pause);
                mDownProgress2.setVisibility(View.VISIBLE);
                mControlBoard2.setVisibility(View.VISIBLE);
                OkHttpLemon.init()
                        .url(tabaoUrl)
                        .downFilePath(Environment.getExternalStorageDirectory() + "/tabao.apk")
                        .executeDown(new IDownloadCallback() {
                            @Override
                            public void onDownTotalLength(long totalLen) {

                            }

                            @Override
                            public void onDownCurrentLenChange(long alreadyDownLen, double downPercent, long speed) {
                                Log.i("tag00","-----已下载  "+ alreadyDownLen/1024/1024+"M  下载长度  "+downPercent*100 +"%   "+"下载速度："+ speed/1000 +"k/s");
                                mDownProgress2.setProgress((int)(downPercent*100));
                                mDownSpeed2.setText(speed/1000 +"k/s");
                            }

                            @Override
                            public void onFinish(long alreadyDownLen, long totalLen, String stratTime, String finishTime) {
                                Log.i("tag00","下载成功。" + stratTime + "  "+finishTime);
                                mDownProgress2.setVisibility(View.GONE);
                                mControlBoard2.setVisibility(View.GONE);
                                mDownSpeed2.setText("下载成功");
                            }


                            @Override
                            public void onEorror(int errorCode, String ts) {
                                Log.i("tag00","下载异常："+"  errorCode：  " + errorCode +" errorMsg " +ts);

                            }

                            @Override
                            public void onDownStatusChange(DownloadStatus downStatus) {
                                Log.i("tag00","状态变更："+downStatus);
                            }
                        });
                break;
            case R.id.play_or_pause_2:
                if (playOrPause){
                    mPlayOrPause2.setImageResource(R.drawable.ic_media_play);
                    OkHttpLemon.init().pause(wpsUrl);
                    playOrPause=false;
                }else {
                    mPlayOrPause2.setImageResource(R.drawable.ic_media_pause);
                    OkHttpLemon.init().start(wpsUrl);
                    playOrPause=true;
                }
                break;
            case R.id.cancel_2:
                break;
            case R.id.button_get:
                OkHttpLemon.init().url("http://v3.wufazhuce.com:8000/api/hp/idlist/0")
                        .get(MainPageBean.class)
                        .execute(new IDataListener<MainPageBean>() {
                            @Override
                            public void onSuccess(MainPageBean s) {
                                Log.i("tag00",s.getRes()+"");
                                StringBuffer sb = new StringBuffer();
                                for (String s1:s.getData()) {
                                    Log.i("tag00",s1);
                                    sb.append(s1+"\n");
                                }
                                mTvShowGet.setText(sb.toString());
                            }

                            @Override
                            public void onError(int code,String ts) {

                            }
                        });

                /*OkHttpLemon.init().url("http://192.168.1.106:8088/transportservice/action/SetCarMove.do")
                        .get(GetBean.class)
                        .execute(new IDataListener<GetBean>() {
                            @Override
                            public void onSuccess(GetBean getBean) {
                                mTvShowGet.setText(getBean.toString());
                            }

                            @Override
                            public void onError(int i, String s) {
                                Log.d("500","提示"+s);
                            }
                        });*/

                break;
            case R.id.button_post:
                OkHttpLemon.init().url("http://v3.wufazhuce.com:8000/api/praise/add")
                        .postString("itemid","1644")
                        .postString("type","hpcontent")
                        .postString("deviceid","ffffffff-b821-e83f-45c3-423b5c7ea996")
                        .postString("version","3.5.0")
                        .postString("devicetype","android")
                        .postString("platform","android")
                        .executes(new IDataListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Log.i("tag00",s);
                                mTvShowPost.setText(s);
                            }

                            @Override
                            public void onError(int code, String ts) {
                                Log.i("tag00","code:"+code + "提示："+ts);
                            }
                        });

                /*OkHttpLemon.init().url("http://192.168.1.106:8088/transportservice/action/SetCarMove.do")
                        .postString("CarId","1")
                        .postString("CarAction","Stop")
                        .postString("UserName","user1")
                        .execute(new IDataListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                mTvShowPost.setText(s);
                            }

                            @Override
                            public void onError(int i, String s) {
                                Log.i("tag00","code:"+i + "提示："+s);
                            }
                        });*/

                break;
        }
    }
}
