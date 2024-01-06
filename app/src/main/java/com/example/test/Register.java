package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputEditText confirmPassword;
    private TextInputLayout confirmPasswordLayout;
    private TextInputLayout passwordLayout;
    private Button registerButton;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        passwordLayout = findViewById(R.id.passwordInputLayout);
        confirmPassword = findViewById(R.id.confirmPasswordInput);
        confirmPasswordLayout = findViewById(R.id.confirmPasswordInputLayout);
        registerButton = findViewById(R.id.registerButton);

        dbHelper = new DatabaseHelper(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            String pass = password.getText().toString();
            String confirmPass = confirmPassword.getText().toString();
            @Override
            public void onClick(View view) {
                String usernametxt = username.getText().toString();
                String passwordtxt = password.getText().toString();
                if (pass.equals(confirmPass)) {
                    long result = dbHelper.addUser(usernametxt, passwordtxt);
                    if (result != -1) {
                        Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Register.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        password.addTextChangedListener(new TextWatcher() { // watch thằng text
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // bth
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Đang nhập
                checkPasswordComplexity(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // Sau khi nhập
            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() { // watch thằng text
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // bth
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Đang nhập
                checkPasswordMatch();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // Sau khi nhập
            }
        });
    }


    private void checkPasswordMatch() { // ktra nếu trùng
        String pass = password.getText().toString();
        String confirmPass = confirmPassword.getText().toString();

        if (!pass.equals(confirmPass)) {
            confirmPasswordLayout.setError("Mật khẩu không khớp");
        } else {
            confirmPasswordLayout.setError(null);
        }

    }

    private void checkPasswordComplexity(String password) { // ktra yêu cầu về pass
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (password.length() < 6) {
            passwordLayout.setError("Mật khẩu phải có ít nhất 6 ký tự");
            return;
        }
        if (password.equals(username)) {
            passwordLayout.setError("Mật khẩu không được trùng với tài khoản");
            return;
        }
        if (!matcher.matches()) {
            passwordLayout.setError("Mật khẩu phải có ít nhất một chữ hoa, một chữ số và một kí tự đặc biệt");
        } else {
            passwordLayout.setError(null);
        }
    }
}
