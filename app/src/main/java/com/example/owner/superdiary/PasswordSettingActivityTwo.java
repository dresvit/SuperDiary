package com.example.owner.superdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordSettingActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_setting_two);

        Button okBtn = (Button) findViewById(R.id.okBtn2);
        Button returnBtn = (Button) findViewById(R.id.returnBtn2);
        final EditText oldPwd = (EditText) findViewById(R.id.oldPwd);
        final SharedPreferences sharedPreferences = getSharedPreferences("DiaryPwd", MODE_PRIVATE);
        final String today = getIntent().getExtras().getString("today");
        final String validPwd = sharedPreferences.getString(today, null);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPwdStr = oldPwd.getText().toString();
                if (MD5Utils.encode(oldPwdStr).equals(validPwd)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(today);
                    editor.commit();
                    Toast.makeText(PasswordSettingActivityTwo.this, "密码取消成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PasswordSettingActivityTwo.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
