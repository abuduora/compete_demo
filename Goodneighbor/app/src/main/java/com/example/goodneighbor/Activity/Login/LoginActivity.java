package com.example.goodneighbor.Activity.Login;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.example.goodneighbor.database.UserDBHelper;

import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class LoginActivity extends AppCompatActivity
{
    private EditText et_Email, et_VerifyCode;
    private UserDBHelper mHelper;
    private String mVerifyCode;
    private Button btn_VerifyCode;
    private Button btn_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login);
        //初始化
        et_Email = findViewById(R.id.et_Email);
        et_VerifyCode = findViewById(R.id.et_VerifyCode);
        btn_VerifyCode=findViewById(R.id.btn_VerifyCode);
        btn_Login=findViewById(R.id.btn_Login);

        mHelper = UserDBHelper.getInstance(this, 1); // 获得用户数据库帮助器的实例
        mHelper.openWriteLink(); //打开数据库连接

        //验证码按钮设置监听器
        btn_VerifyCode.setOnClickListener((v)->{
            String Email = et_Email.getText().toString();
            final String senderEmail = "csy157486@163.com"; // 发件人邮箱
            final String senderPassword = "Qscvbylkj157486"; // 发件人邮箱密码

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.163.com"); // 使用163 SMTP服务器地址
            props.put("mail.smtp.port", "465");//服务器端口

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(Email));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email));
                message.setSubject("验证码");
                mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                message.setText("您的验证码是: " + mVerifyCode);
                Transport.send(message);
                System.out.println("邮件已发送成功！");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
    });

        //登录按钮设置监听器
        btn_Login.setOnClickListener((v)->{
                {
                    if (!et_VerifyCode.getText().toString().equals(mVerifyCode)) {
                        Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    } else { // 验证码校验通过
                        loginSuccess(); // 提示用户登录成功
                    }
                }
        });

        /*//最短账号
        //hasFocus表示当前控件是否获得焦点
        et_VerifyCode.setOnFocusChangeListener((v, hasFocus) -> {
            if(v.getId()==R.id.et_VerifyCode&&hasFocus)
            {
                String Username=et_Email.getText().toString();
                if(TextUtils.isEmpty(Username)||Username.length()<11)
                {
                    et_Email.requestFocus();
                    Toast.makeText(LoginActivity.this,"请输入11位手机号码",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }


    //登录的点击事件
    /*public void onClick(View v) {
        System.out.println("第一个按钮");
        String phone = et_Username.getText().toString();
        if (v.getId() == R.id.btn_VerifyCode) { // 点击了“获取验证码”按钮
            if (phone.length() != 11) { // 手机号码不足11位
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            else
             {
                // 生成六位随机数字的验证码
                mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                // 以下弹出提醒对话框，提示用户记住六位验证码数字
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号" + phone + "，本次验证码是" + mVerifyCode + "，请输入验证码");
                builder.setPositiveButton("好的", null);
                AlertDialog alert = builder.create();
                alert.show(); // 显示提醒对话框
            }
        }
        else if (v.getId() == R.id.btn_Login) { // 点击了“登录”按钮
            if (phone.length() != 11) { // 手机号码不足11位
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            else{ // 验证码
                if (!et_VerifyCode.getText().toString().equals(mVerifyCode)) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                } else { // 验证码校验通过
                    loginSuccess(); // 提示用户登录成功
                }
            }
        }
    }*/


//发邮件
    //我直接写成监听器onclick事件 不写静态了
        /*class EmailSender {
            public static void sendVerificationCode(String recipientEmail, String verificationCode) {
                final String senderEmail = "csy157486@163.com"; // 发件人邮箱
                final String senderPassword = "Qscvbylkj157486"; // 发件人邮箱密码
                String mVerifyCode;
                EditText et_Username = findViewById(R.id.et_Username);

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com"); // 使用Gmail SMTP服务器
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(et_Username.getText().toString()));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                    message.setSubject("验证码");
                    mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                    message.setText("您的验证码是: " + mVerifyCode);
                    Transport.send(message);
                    System.out.println("邮件已发送成功！");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }*/


    @Override
    protected void onRestart() {
        super.onRestart();
        et_VerifyCode.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHelper = UserDBHelper.getInstance(this, 1); // 获得用户数据库帮助器的实例
        mHelper.openWriteLink(); // 恢复页面，则打开数据库连接
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHelper.closeLink(); // 暂停页面，则关闭数据库连接
    }

    //登录成功
    private void loginSuccess()
    {
        String desc = String.format("您的电子邮箱是%s，恭喜你通过登录验证，点击“确定”按钮返回上个页面",
               et_Email.getText().toString());
        // 以下弹出提醒对话框，提示用户登录成功
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定返回", (dialog, which) -> {
            mHelper.closeLink();
            finish(); // 结束当前的活动页面
        });
        builder.setNegativeButton("我再看看", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

}
