package com.greentree.application;

import android.app.Application;
import android.os.Environment;
import android.view.Display;

import com.greentree.bean.SearchHotelCache;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by yangyongxin on 15/9/8.
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static MyApplication application;
    private BitmapUtils bitmapUtils;
    private HttpUtils httpUtils;
    private DbUtils dbUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initxUtils();
        initImageLoader();
    }

    public static MyApplication getApplication() {
        return application;
    }

    private void initxUtils() {
        httpUtils = new HttpUtils("utf-8");
        httpUtils.configRequestThreadPoolSize(5);
        httpUtils.configRequestRetryCount(3);
        httpUtils.configSoTimeout(3000);
        httpUtils.configResponseTextCharset("utf-8");

        String cachePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cachePath = Environment.getExternalStorageDirectory() + "/" + this.getPackageName() + "/xutils_cache/image";
        } else {
            cachePath = this.getCacheDir() + "/xutils_cache/image";
        }
        int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        bitmapUtils = new BitmapUtils(this, cachePath, cacheSize);
        bitmapUtils.configThreadPoolSize(5);
        bitmapUtils.configDefaultCacheExpiry(1000L * 60 * 60 * 24 * 7);
        bitmapUtils.configDefaultLoadingImage(R.drawable.list_bg_200);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.list_bg_200);

        DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(this);
        daoConfig.setDbName("greentree.db");
        daoConfig.setDbVersion(1);
        dbUtils = DbUtils.create(daoConfig);
        try {
            dbUtils.createTableIfNotExist(SearchHotelCache.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


    private  void initImageLoader(){
        ImageLoader imageLoader = ImageLoader.getInstance();

        String cachePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cachePath = Environment.getExternalStorageDirectory() + "/" + this.getPackageName() + "/xutils_cache/image";
        } else {
            cachePath = this.getCacheDir() + "/xutils_cache/image";
        }
        int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 8);

        File cacheFile = new File(cachePath);
        cacheFile.mkdirs();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(5)
                .memoryCacheSize(cacheSize)
                .diskCacheFileCount(500)
                .diskCache(new UnlimitedDiskCache(cacheFile))
                .build();

        imageLoader.init(configuration);

        new DisplayImageOptions.Builder()
                .showImageOnFail(R.d);


    }
    public BitmapUtils getBitmapUtils() {
        return bitmapUtils;
    }

    public HttpUtils getHttpUtils() {
        return httpUtils;
    }

    public DbUtils getDbUtils() {
        return dbUtils;
    }

}
