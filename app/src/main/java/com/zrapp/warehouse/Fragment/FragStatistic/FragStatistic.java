package com.zrapp.warehouse.Fragment.FragStatistic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragStatisticBinding;
import com.zrapp.warehouse.databinding.TabLayoutBinding;

public class FragStatistic extends Fragment {
    FragStatisticBinding binding;
    StatisticPager adapter;
    TabLayoutMediator.TabConfigurationStrategy tabConfig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragStatisticBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new StatisticPager(this);
        binding.vpSt.setAdapter(adapter);

        tabConfig = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TabLayoutBinding binding = TabLayoutBinding.inflate(inflater, null, false);
                switch (position) {
                    case 0:
                        binding.tabTitle.setText("Thống kê số lượng");
                        binding.tabSubtitle.setText("123");
                        break;
                    case 1:
                        binding.tabTitle.setText("Thống kê lượng tiền");
                        binding.tabSubtitle.setText("456");
                        break;
                }
                tab.setCustomView(binding.getRoot());
            }
        };

        new TabLayoutMediator(binding.tablSt, binding.vpSt, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Thống kê số lượng");
                        break;
                    case 1:
                        tab.setText("Thống kê lượng tiền");
                        break;
                }
            }
        }).attach();
    }
}
