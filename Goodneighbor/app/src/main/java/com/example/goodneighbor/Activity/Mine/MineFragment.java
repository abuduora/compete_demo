package com.example.goodneighbor.Activity.Mine;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.goodneighbor.Activity.Login.LoginActivity;
import com.example.goodneighbor.R;
import com.example.goodneighbor.database.PrefManager;
import com.example.goodneighbor.util.OkHttp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView btn_Nickname,btn_About,btn_exit,btn_message;
    private  Button btn_share_wite;
    private ImageView iv_imageAvatar;
    private TextView tv_integral;
    private ActivityResultLauncher<Intent> mRresultLauncher;
    private Uri picUri;
    private TextView tv_share_things;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_mine, null);
        SharedPreferences shared= getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
        tv_share_things = view.findViewById(R.id.tv_share_things);
        tv_share_things.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MineGoods1.class);
                startActivity(i);
            }
        });
        //获取头像
        iv_imageAvatar = view.findViewById(R.id.ib_imageAvatar);
        tv_integral=view.findViewById(R.id.tv_integral);
        iv_imageAvatar.setOnClickListener(this);
        mRresultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode()==RESULT_OK){
             /*  Bundle bundle = result.getData().getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                iv_imageAvatar.setImageBitmap(bitmap);*/
                Intent intent=result.getData();
               picUri=intent.getData();
                if(picUri !=null){
                    System.out.println(picUri);
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(picUri));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // 可以根据需要调整压缩格式和质量
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                    RequestBody requestbody = new FormBody.Builder()
                            .add("email",shared.getString("email",""))
                            .add("base64",base64Image)
                            .build();
                    OkHttp.Post("http://[fe80::1614:4bff:fe7f:6e7c]:9776/img/getImg", requestbody, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Toast.makeText(getActivity(), "头像设置失败", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            SharedPreferences.Editor editor=shared.edit();
                            editor.putString("avatar",response.body().string());
                            editor.apply();
                            iv_imageAvatar.setScaleType(ImageView.ScaleType.FIT_XY);
                            Glide.with(getActivity()).load(response.body().string()).into(iv_imageAvatar);
                                  }
                            });
                        }
                    }).start();
                    //String path = RealPathFromUriUtils.getRealPathFromUri(getActivity(),picUri);
                  //  File file = new File(path);
                   /* new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url="http://[240e:404:b830:a118:61ce:6331:f25f:c199]:9776/user/avatar";
                            RequestBody requestBody=new FormBody.Builder()
                                    .add("email",shared.getString("email",""))
                                    .build();
                            OkHttp.Post(url, requestBody, new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    Toast.makeText(EditMessageActivity.this, "修改个人信息失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    Toast.makeText(EditMessageActivity.this, "修改个人信息成功", Toast.LENGTH_SHORT).show();
                                }
                            });*/
                   Log.d("IMAGE","设置图片成功");
                }
            }

        });

        //获取积分
        tv_integral.setText(shared.getInt("integral",0)+"积分");

        //获取昵称
        btn_Nickname = view.findViewById(R.id.btn_Nickname);
        btn_Nickname.setText(shared.getString("nickname",""));

        btn_message = view.findViewById(R.id.btn_message);
        btn_About = view.findViewById(R.id.btn_About);
        btn_exit = view.findViewById(R.id.btn_exit);
        btn_Nickname.setText(shared.getString("email",""));
        iv_imageAvatar.setOnClickListener(this);
        btn_Nickname.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_message.setOnClickListener(this);
        btn_About.setOnClickListener(this);
       // btn_share_wite.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        PrefManager prefManager = new PrefManager(getActivity());
            if (v.getId() == R.id.ib_imageAvatar){
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                mRresultLauncher.launch(intent);}
        else if (v.getId() == R.id.btn_Nickname) {
            startActivity(new Intent(getActivity(), EditMessageActivity.class));
        } else if (v.getId() == R.id.btn_About) {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        } else if (v.getId() == R.id.btn_exit) {
            prefManager=new PrefManager(getActivity());
            prefManager.setFirstTimeLaunch(true);
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else if (v.getId() == R.id.btn_message) {
            startActivity(new Intent(getActivity(), EditMessageActivity.class));
        }//else if(v.getId()==R.id.btn_share_wite){
            //startActivity(new Intent(getActivity() , ReadyShare.class));
        //}
    }
}



    //底部四个按钮
    //private Button btn_circle,btn_main,btn_share,btn_mine;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mine_mine);
//        btn_Nickname=findViewById(R.id.btn_Nickname);
//        btn_Nickname.setOnClickListener(this);
//        btn_About=findViewById(R.id.btn_About);
//        btn_About.setOnClickListener(this);
//        btn_exit=findViewById(R.id.btn_exit);
//        btn_exit.setOnClickListener(this);
//        ib_imageAvatar=findViewById(R.id.ib_imageAvatar);
//        ib_imageAvatar.setOnClickListener(this);
//        //btn_Nickname.setText(userInfo.email);
//        btn_message=findViewById(R.id.btn_message);
//        btn_message.setOnClickListener(this);
//        mHelper = UserDBHelper.getInstance(this, 1);
        /*//底部四个选择按钮的监听器
        btn_main=findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);
        btn_share=findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);
        btn_circle=findViewById(R.id.btn_circle);
        btn_circle.setOnClickListener(this);
        btn_mine=findViewById(R.id.btn_mine);
        btn_mine.setOnClickListener(this);*/

    // 设置登录按钮的点击事件
        /*btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理登录逻辑
                // 这里简单地假设登录成功，并更新欢迎文本和积分
                //textWelcome.setText("欢迎，用户！");
                points = 100; // 假设用户登录后获得100积分
                updatePointsText();
            }
        });
*/
//}
///*    private void updatePointsText() {
//        textPoints.setText("积分：" + points);
//    }*/

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.btn_Nickname) {
//            startActivity(new Intent(this,EditMessageActivity.class));
//        } else if (v.getId()==R.id.btn_About) {
//            startActivity(new Intent(this,AboutActivity.class));
//        } else if (v.getId()==R.id.btn_exit) {
//            startActivity(new Intent(this,LoginActivity.class));
//        }else if(v.getId()==R.id.btn_message){
//            startActivity(new Intent(this,EditMessageActivity.class));
//        }else if(v.getId()==R.id.ib_imageAvatar){
//            startActivity(new Intent(this,EditMessageActivity.class));
//        }
//  /*      else if(v.getId()==R.id.btn_About){startActivity(new Intent(this,AboutActivity.class));}
//        else if(v.getId()==R.id.btn_main){startActivity(new Intent(this,MainActivity.class));}
//        else if(v.getId()==R.id.btn_share){startActivity(new Intent(this,ShareActivity.class));}
//        else if(v.getId()==R.id.btn_circle){startActivity(new Intent(this,CircleActivity.class));}
//        else if(v.getId()==R.id.btn_mine){startActivity(new Intent(this,MineActivity.class));}*/
//    }

