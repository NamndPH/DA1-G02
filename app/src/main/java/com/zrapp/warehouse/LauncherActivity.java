package com.zrapp.warehouse;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zrapp.warehouse.databinding.ActivityLauncherBinding;

public class LauncherActivity extends AppCompatActivity {
    ActivityLauncherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLauncherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animator animBtn = AnimatorInflater.loadAnimator(LauncherActivity.this, R.animator.btn_l_trans);
        animBtn.setTarget(binding.btnStart);
        animBtn.start();

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


    }
}