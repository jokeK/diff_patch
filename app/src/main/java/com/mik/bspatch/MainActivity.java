package com.mik.bspatch;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String SD_CARD = Environment.getExternalStorageDirectory().getPath();
    private static final String OLD_PATH = SD_CARD+ File.separatorChar+"old.apk";
    private static final String NEW_PATH = SD_CARD+File.separatorChar+"new.apk";
    private static final String NEW_PATCH_PATH = SD_CARD+File.separatorChar+"new_patch.apk";
    private static final String PATCHPATH = SD_CARD+File.separatorChar+"apk.patch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //6.0以上动态设置权限或者手机设置上修改
        //首先要请求网络根据版本号判断是否需要更新
        //开子线程假装在下载
        File patchFile = DownLoadUtils.download("url");
        //下载完成拿到本地旧APK
        String scorceApk = ApkUtils.getSourceApkPath(this,getPackageName());
        //假装合并
        //MikPatchAndDiff.patch(scorceApk,"新APK路径",patchFile.getAbsolutePath());
        //安装APK
        ApkUtils.installApk(this,"新APK的路径");
    }

    public void diff(View view) {
        //差分的方法，本该在服务器端的
        MikPatchAndDiff.diff(OLD_PATH,NEW_PATH,PATCHPATH);
    }

    public void patch(View view) {
        MikPatchAndDiff.patch(OLD_PATH,NEW_PATCH_PATH,PATCHPATH);
    }


}
