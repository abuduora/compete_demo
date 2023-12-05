package com.example.goodneighbor.Activity.Share;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.goodneighbor.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SharePublish extends Activity {
    private View takephotos;
    private ArrayList<View> viewArrayList = new ArrayList<>();
    private TextView txtDate;
    private TextView txtTime;
    private static String str_time="1";

    DateFormat format= DateFormat.getDateTimeInstance();
    Calendar calendar= Calendar.getInstance(Locale.CHINA);
    private PopupWindow popupWindow;
    private View view_time;
    TextView time_finish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_publish);
        view_time=LayoutInflater.from(this).inflate(R.layout.share_time_takephotos,null);

        TextView share_location=findViewById(R.id.share_location);
        TextView time_new=findViewById(R.id.publish_timeNew);
        time_new.setText(getNowTime());



        txtDate= (TextView) findViewById(R.id.txtDate);
        time_finish=findViewById(R.id.publish_timeFinish);
        time_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.publish_timeFinish){
                    showDatePickerDialog(SharePublish.this,  3, txtDate, calendar);;
                    time_finish.setText(str_time);
                }
//                initPopupWindow();
//                showPopWindow();
            }
        });
        share_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SharePublish.this,BaiduMapView.class);
                startActivity(i);
            }
        });
    }

    public static String getNowTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM月dd号");
        return simpleDateFormat.format(new Date());
    }
    private void initPopupWindow() {
        //要在布局中显示的布局
        takephotos = LayoutInflater.from(this).inflate(R.layout.share_time_takephotos, null, false);
        //实例化PopupWindow并设置宽高
        popupWindow = new PopupWindow(takephotos, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失，这里因为PopupWindow填充了整个窗口，所以这句代码就没用了
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.ActionSheetDialogAnimation);
    }

    private void showPopWindow() {
        View rootview = LayoutInflater.from(SharePublish.this).inflate(R.layout.share_time_takephotos, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            private String str,str1,str2,str3;
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText("您选择了：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");

                str1=String.valueOf(year)+"年";
                str2=String.valueOf((monthOfYear + 1))+"月";
                str3=String.valueOf(dayOfMonth)+"日";
                str_time=str1+str2+str3;
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
