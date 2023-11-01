package com.example.goodneighbor.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class Base64 {
    public static String base64(Context context, Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // 可以根据需要调整压缩格式和质量
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String base64Image = android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);
        return base64Image;
    }
}
