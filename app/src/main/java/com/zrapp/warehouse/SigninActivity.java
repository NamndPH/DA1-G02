package com.zrapp.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.Model.Staff;
import com.zrapp.warehouse.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    StaffDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dao = new StaffDAO();

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SigninActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        final TextInputEditText[] edList = {binding.edUsername, binding.edPassword};
        for (TextInputEditText edT : edList) {
            applyTextWatcher(edT);
        }

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edUsername.getText().toString();
                String pass = binding.edPassword.getText().toString();
                if (username.isEmpty() || pass.isEmpty()) {
                    if (username.isEmpty())
                        binding.lbUsername.setError("Vui lòng nhập tài khoản");
                    if (pass.isEmpty())
                        binding.lbPassword.setError("Vui lòng nhập mật khẩu");
                } else {
                    try {
                        Staff staff = dao.getStaff(username);
                        if (!pass.equals(staff.getPass())) {
                            binding.lbPassword.setError("Mật khẩu không đúng!!");
                        } else {
                            binding.lbPassword.setErrorEnabled(false);
                            Intent i = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    } catch (Exception e) {
                        binding.lbUsername.setError("Tài khoản không tồn tại!!");
                    }
                }
            }
        });
    }

    public void applyTextWatcher(TextInputEditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (editText.getId()) {
                    case R.id.edUsername:
                        binding.lbUsername.setErrorEnabled(false);
                        break;

                    case R.id.edPassword:
                        binding.lbPassword.setErrorEnabled(false);
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}