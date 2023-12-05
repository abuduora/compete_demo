package com.example.goodneighbor.Activity.Share;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.example.goodneighbor.util.BitmapUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

public class ShareQrView extends AppCompatActivity {
    private ImageView main_posting_qr_1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_posting_qr);

        qr_view();

    }

    private void qr_view(){

        // 调用生成二维码的方法
        String qrCodeData = "幸福小区 2\0"; // 二维码的内容

        //Bitmap yourLogoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.title);
        ErrorCorrectionLevel errorRate = ErrorCorrectionLevel.H;

        Bitmap qrCodeBitmap = createQrcodeBitmap(qrCodeData, errorRate);

        // 将生成的二维码显示在ImageView中
        ImageView imageView = findViewById(R.id.main_posting_qr_1);
        imageView.setImageBitmap(qrCodeBitmap);
    }

    private Bitmap createQrcodeBitmap(String content, ErrorCorrectionLevel errorRate) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        int width = content.length()*6; // 二维码图片的宽度
        int height = width; // 二维码图片的高度
        int margin = width / 20; // 二维码图片的空白边距
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, margin); // 设置空白边距
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, errorRate); // 设置容错率
        try {
            // 根据配置参数生成位矩阵对象
            BitMatrix bitMatrix = new QRCodeWriter().encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);
            // 创建像素数组，并根据位矩阵对象为数组元素赋色值
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) { // 返回true表示黑色色块
                        pixels[y * width + x] = Color.BLACK;
                    } else { // 返回false表示白色色块
                        pixels[y * width + x] = Color.WHITE;
                    }
                }
            }
            // 创建位图对象，并根据像素数组设置每个像素的色值
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            if (bitmap.getWidth() < 300) { // 图片太小的话，要放大图片
                bitmap = BitmapUtil.getScaleBitmap(bitmap, 300.0/bitmap.getWidth());
            }

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}