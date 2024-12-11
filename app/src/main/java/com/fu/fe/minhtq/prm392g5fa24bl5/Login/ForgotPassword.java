package com.fu.fe.minhtq.prm392g5fa24bl5.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AccountDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Account;

public class ForgotPassword extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtRepassword;
    private Button btnForgotPassword, btnBack;
    private TextView tvError;
    private AccountDAO accountDAO;

    private void bindingView() {
        edtEmail = findViewById(R.id.edtForgotPasswordEmail);
        edtPassword = findViewById(R.id.edtForgotPasswordPassword);
        edtRepassword = findViewById(R.id.edtForgotPasswordRepassword);

        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        btnBack = findViewById(R.id.btnForgotPasswordBack);

        tvError = findViewById(R.id.tvForgotPasswordError);

        accountDAO = AppDatabase.getInstance(this).accountDAO();
    }

    private void bindingAction() {
        btnForgotPassword.setOnClickListener(this::onBtnForgotPasswordClick);
        btnBack.setOnClickListener(this::onBtnBackClick);
    }


    private void onBtnForgotPasswordClick(View view) {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String repassword = edtRepassword.getText().toString().trim();

        if (validateForgotPassword(email, password, repassword)) {
            Account account = accountDAO.getAccountByEmail(email);
            if (account == null) {
                tvError.setText("Địa chỉ Email chưa tồn tại!");
                tvError.setVisibility(View.VISIBLE);
            } else {
                accountDAO.updatePassword(email, password);
                Toast.makeText(this, "Thay đổi mật khẩu thành công! Hãy đăng nhập lại.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }
    }

    private boolean validateForgotPassword(String email, String password, String repassword) {
        tvError.setVisibility(View.GONE);

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

        if (TextUtils.isEmpty(repassword)) {
            edtRepassword.setError("Vui lòng nhập lại mật khẩu!");
            edtRepassword.requestFocus();
            return false;
        } else if (!password.equals(repassword)) {
            edtRepassword.setError("Mật khẩu không khớp!");
            edtRepassword.requestFocus();
            return false;
        }
        return true;
    }

    private void onBtnBackClick(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();
    }
}