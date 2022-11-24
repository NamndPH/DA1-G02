package com.zrapp.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.databinding.ActivitySigninBinding;
import com.zrapp.warehouse.model.Staff;

public class SigninActivity extends AppCompatActivity implements View.OnKeyListener {
    ActivitySigninBinding binding;
    StaffDAO dao;
    public static Staff account = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dao = new StaffDAO();

        binding.edUsername.setOnKeyListener(this);
        binding.edPassword.setOnKeyListener(this);

        binding.tvRegister.setOnClickListener(view -> {
            Intent i = new Intent(SigninActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        binding.btnLogin.setOnClickListener(view -> {
            signIn();
        });
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                (i == KeyEvent.KEYCODE_ENTER)) {
            signIn();
            return true;
        }
        return false;
    }

    public void signIn(){
        String username = binding.edUsername.getText().toString();
        String pass = binding.edPassword.getText().toString();
        if (username.isEmpty() || pass.isEmpty()) {
            CustomToast.makeText(SigninActivity.this, "Vui lòng nhập đủ thông tin", CustomToast.SHORT, CustomToast.ERROR).show();
        } else {
            account = dao.getStaff(username);
            if (account.getUsername() == null) {
                CustomToast.makeText(SigninActivity.this, "Tài khoản không tồn tại!!", CustomToast.SHORT, CustomToast.ERROR).show();
            } else if (!pass.equals(account.getPass())) {
                CustomToast.makeText(SigninActivity.this, "Mật khẩu không đúng!!", CustomToast.SHORT, CustomToast.ERROR).show();
            } else if (!account.isStatus()) {
                CustomToast.makeText(SigninActivity.this, "Vui lòng chờ xác nhận!!", CustomToast.SHORT, CustomToast.WARNING).show();
            } else {
                Intent i = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}