package com.fu.fe.minhtq.prm392g5fa24bl5.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnForgotPassword, btnRegister, btnBack;

    private void bindingView() {
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword = findViewById(R.id.edtLoginPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnLoginBack);
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(this::onBtnLoginClick);
        btnForgotPassword.setOnClickListener(this::onBtnForgotPasswordClick);
        btnRegister.setOnClickListener(this::onBtnRegisterClick);
        btnBack.setOnClickListener(this::onBtnBackClick);
    }

    private void onBtnLoginClick(View view) {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (validateLogin(email, password)) {
            //Kiểm tra tài khoản đăng nhập

            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validateLogin(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Vui lòng nhập email!");
            edtEmail.requestFocus();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Email không hợp lệ!");
            edtEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Vui lòng nhập mật khẩu!");
            edtPassword.requestFocus();
            return false;
        } else if (password.length() < 6) {
            edtPassword.setError("Mật khẩu phải có ít nhất 6 ký tự!");
            edtPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void onBtnForgotPasswordClick(View view) {

    }

    private void onBtnRegisterClick(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
        finish();
    }

    private void onBtnBackClick(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();
    }
}