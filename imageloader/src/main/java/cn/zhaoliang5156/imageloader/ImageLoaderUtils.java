package cn.zhaoliang5156.imageloader;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * ImageLoader 工具类
 */
public class ImageLoaderUtils {

    public static ImageLoaderConfiguration getImageConfig(Context context) {
        // DON'T COPY THIS CODE TO YOUR PROJECT! This is just example of ALL options using.
        // See the sample project how to use ImageLoader correctly.
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/img"); // 配置缓存目录
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // 线程池的数量
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .defaultDisplayImageOptions(getImageOption(new SimpleBitmapDisplayer())) // default
                .writeDebugLogs()
                .build();
        return config;
    }

    public static DisplayImageOptions getImageOption(BitmapDisplayer displayer) {
        // DON'T COPY THIS CODE TO YOUR PROJECT! This is just example of ALL options using.
        // See the sample project how to use ImageLoader correctly.
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
                //.showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
                // .showImageOnFail(R.drawable.ic_error) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // 缓存到内容
                .cacheOnDisk(true) // 缓存到SD卡
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // 配置色彩模式
                .displayer(displayer) // default
                .build();
        return options;
    }
}
