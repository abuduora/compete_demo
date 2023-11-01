/*
package com.example.goodneighbor.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class saveBitmap {
    public static String saveBitmapByMediaStore(Bitmap bitmap, String name) {
        ContentResolver contentResolver = APP.getAppContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.ImageColumns.RELATIVE_PATH, "Pictures/whooo/");
        contentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.Images.ImageColumns.WIDTH, bitmap.getWidth());
        contentValues.put(MediaStore.Images.ImageColumns.HEIGHT, bitmap.getHeight());
        //会向Pictures/whooo/目录创建name.jpg的文件
        Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (uri == null) {
            return "";
        }
        //写入图片
        OutputStream out = null;
        try {
            out = contentResolver.openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory() + "/Pictures/whooo/" + name + ".jpg";
    }
}
*/
