package com.zrapp.warehouse.Fragment.FragStatistic;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragStatisticsQtyBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragStatisticQuantity extends Fragment {
    FragStatisticsQtyBinding binding;
    Calendar calendar;
    int day, month, year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragStatisticsQtyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    public void dateSelect(EditText ed) {
        DatePickerDialog picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                ed.setText(dateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        picker.show();
    }
}
