package com.fu.fe.minhtq.prm392g5fa24bl5.Login;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.HomePage;
import com.fu.fe.minhtq.prm392g5fa24bl5.Home_of_5_fragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AccountDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Account;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnForgotPassword, btnRegister, btnBack;
    private CheckBox cbSaveAccount;
    private TextView tvLoginError;
    private AccountDAO accountDAO;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private void bindingView() {
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword = findViewById(R.id.edtLoginPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnLoginForgotPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnLoginBack);

        cbSaveAccount = findViewById(R.id.cbSaveAccount);
        tvLoginError = findViewById(R.id.tvLoginError);

        accountDAO = AppDatabase.getInstance(this).accountDAO();
        pref = getSharedPreferences("DataPref", MODE_PRIVATE);
        editor = pref.edit();
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
        boolean saveAccount = cbSaveAccount.isChecked();

        if (validateLogin(email, password)) {
            Account account = accountDAO.getAccountForLogin(email, password);
            if (account == null) {
                tvLoginError.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            } else {
                if (saveAccount) {
                    editor.putBoolean("isLogin", true);
                    editor.putInt("user_id", account.getAccount_id());
                    editor.apply();
                }
                Intent i = new Intent(this, Home_of_5_fragment.class);
                i.putExtra("user_id", account.getAccount_id());
                startActivity(i);
                finish();
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateLogin(String email, String password) {
        tvLoginError.setVisibility(View.GONE);

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
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
        finish();
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
        if (pref.getBoolean("isLogin", false)) {
            Intent i = new Intent(this, HomePage.class);
            startActivity(i);
            finish();
        }
        bindingAction();
    }
}