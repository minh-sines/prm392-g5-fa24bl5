package com.fu.fe.minhtq.prm392g5fa24bl5.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUserame, edtEmail, edtPassword, edtRepassword;
    private Button btnSignup, btnBack;
    private CheckBox cbAccept;
    private TextView tvError;

    private void bindingView() {
        edtUserame = findViewById(R.id.edtSignupUsername);
        edtEmail = findViewById(R.id.edtSignupEmail);
        edtPassword = findViewById(R.id.edtSignupPassword);
        edtRepassword = findViewById(R.id.edtSignupRepassword);

        btnSignup = findViewById(R.id.btnSignup);
        btnBack = findViewById(R.id.btnSignupBack);

        cbAccept = findViewById(R.id.cbAcceptDescription);
        tvError = findViewById(R.id.tvError);
    }

    private void bindingAction() {
        btnSignup.setOnClickListener(this::onBtnSignupClick);
        btnBack.setOnClickListener(this::onBtnBackClick);
    }

    private void onBtnSignupClick(View view) {
        String username = edtUserame.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String repassword = edtRepassword.getText().toString().trim();
        boolean accept = cbAccept.isChecked();

        if (validateSignup(username, email, password, repassword, accept)) {
            //Kiểm tra tài khoản đăng kí

            Toast.makeText(this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
        }
    }

    private void onBtnBackClick(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private boolean validateSignup(String username, String email, String password, String repassword, boolean accept) {
        if (accept) {
            tvError.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(username)) {
            edtUserame.setError("Vui lòng nhập tên đăng nhập!");
            edtUserame.requestFocus();
            return false;
        }

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

        if (!accept) {
//            cbAccept.setError("Vui lòng đồng ý với điều khoản và chính sách!");
            tvError.setVisibility(View.VISIBLE);
            cbAccept.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();
    }
}