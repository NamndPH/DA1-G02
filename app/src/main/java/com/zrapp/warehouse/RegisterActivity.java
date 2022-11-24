package com.zrapp.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.databinding.ActivityRegisterBinding;
import com.zrapp.warehouse.model.Staff;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    StaffDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dao = new StaffDAO();

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, SigninActivity.class);
                startActivity(i);
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edName.getText().toString();
                String username = binding.edUsername.getText().toString();
                String pass = binding.edPassword.getText().toString();
                if (name.isEmpty() || username.isEmpty() || pass.isEmpty()) {
                    CustomToast.makeText(RegisterActivity.this, "Vui lòng nhập đủ thông tin", CustomToast.SHORT, CustomToast.ERROR).show();
                } else {
                    Staff staff = dao.getStaff(username);
                    if (staff.getUsername() != null) {
                        CustomToast.makeText(RegisterActivity.this, "Username đã được sử dụng!!", CustomToast.SHORT, CustomToast.ERROR).show();
                    } else {
                        Staff account = new Staff();
                        account.setName(name);
                        account.setUsername(username);
                        account.setPass(pass);
                        account.setPost("Nhân viên");
                        dao.insertStaff(account, 0);
                        CustomToast.makeText(RegisterActivity.this, "Đăng ký thành công", CustomToast.SHORT, CustomToast.SUCCESS).show();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent i = new Intent(RegisterActivity.this, SigninActivity.class);
                                startActivity(i);
                            }
                        },2300);
                    }
                }
            }
        });
    }
}