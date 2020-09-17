package com.glriverside.xgqin.logindemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Boolean bPwdSwitch = false;

    private EditText etPwd;
    private EditText etAccount;
    private CheckBox cbRememberPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPwd = findViewById(R.id.et_password);
        etAccount = findViewById(R.id.et_account);
        cbRememberPwd = findViewById(R.id.cb_remember_pwd);

        SharedPreferences spFile = getSharedPreferences(getResources().getString(R.string.shared_preferences_file_name), MODE_PRIVATE);
        String account = spFile.getString(getResources().getString(R.string.login_account_name), null);
        String password = spFile.getString(getResources().getString(R.string.login_password), null);
        Boolean rememberPassword = spFile.getBoolean(getResources().getString(R.string.login_remember_password), false);

        if (account != null && !TextUtils.isEmpty(account)) {
            etAccount.setText(account);
        }

        if (password != null && !TextUtils.isEmpty(password)) {
            etPwd.setText(password);
        }

        cbRememberPwd.setChecked(rememberPassword);

        Button btLogin = findViewById(R.id.bt_login);
        if (btLogin != null) {
            btLogin.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_login:
                SharedPreferences spFile = getSharedPreferences(getResources().getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spFile.edit();

                if (cbRememberPwd.isChecked()) {
                    String password = etPwd.getText().toString();
                    String account = etAccount.getText().toString();

                    editor.putString(getResources().getString(R.string.login_account_name), account);
                    editor.putString(getResources().getString(R.string.login_password), password);
                    editor.putBoolean(getResources().getString(R.string.login_remember_password), true);
                    editor.apply();
                } else {
                    editor.remove(getResources().getString(R.string.login_account_name));
                    editor.remove(getResources().getString(R.string.login_password));
                    editor.remove(getResources().getString(R.string.login_remember_password));
                    editor.apply();
                }
                break;
            default:
                break;
        }
    }
}

