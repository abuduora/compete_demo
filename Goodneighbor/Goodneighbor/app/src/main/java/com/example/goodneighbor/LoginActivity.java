package com.example.goodneighbor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener
{

    private EditText et_Username, et_Password;
    private Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_Username = findViewById(R.id.et_Username);
        et_Password = findViewById(R.id.et_Password);
        btn_Login = findViewById(R.id.btn_Login);
        CheckBox cb_remember=findViewById(R.id.cb_remember);
cb_remember.setOnClickListener();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入的用户名和密码
                String username = et_Username.getText().toString();
                String password = et_Password.getText().toString();

                // 在这里添加登录逻辑和验证代码
                // 例如，可以将用户名和密码发送到服务器进行验证

                // 这里只是简单地打印用户名和密码到控制台
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }
        });

        et_Password.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(v.getId()==R.id.et_Password&&hasFocus)
                {
                    String Username=et_Username.getText().toString();
                    if(TextUtils.isEmpty(Username)||Username.length()<11)
                    {
                        et_Username.requestFocus();
                        Toast.makeText(LoginActivity.this,"请输入11为手机号码",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {

    }
}
