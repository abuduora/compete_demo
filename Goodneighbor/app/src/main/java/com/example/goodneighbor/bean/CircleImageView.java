package com.example.goodneighbor.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends androidx.appcompat.widget.AppCompatImageView {

    private Paint paint; // 画笔

    private int radius; // 半径

    private float scale; // 缩放比例

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 宽高保持一致
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        radius = size / 2;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint = new Paint();
        Bitmap bitmap = drawableToBitmap(getDrawable());

        // 初始化BitmapShader
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // 缩放比例
        scale = (radius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);


        paint.setShader(bitmapShader);

        // 圆形操作 定中心点坐标、半径、画笔
        canvas.drawCircle(radius, radius, radius, paint);
    }

    /**
     * Drawable转 BitMap
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

}