package com.zrapp.warehouse.Fragment.FragOrder;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zrapp.warehouse.DAO.OrderDao;
import com.zrapp.warehouse.MainActivity;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragOrderAddBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragOrderAdd extends Fragment {

    FragOrderAddBinding binding;
    private OrderDao db = new OrderDao();

    public FragOrderAdd() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragOrderAddBinding.inflate(inflater, container, false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        binding.edtDate.setText(sdf.format(c.getTime()));

        String[] kind = {"nhập", " xuất"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, kind);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spnKind.setAdapter(adapter);

        binding.btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edtIdOrder = binding.edtIdOrder.getText().toString();
                String edtIdStaff = binding.edtIdStaff.getText().toString();
                String spnKind = binding.spnKind.getSelectedItem().toString();
                String edtDate = binding.edtDate.getText().toString();
                Fragment fragment;
                fragment = new FragOrderList();
                MainActivity.loadFrag(fragment);
//                if (edtIdStaff.isEmpty() || edtDate.isEmpty()) {
//                    Toast.makeText(getActivity(), "vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                } else {
//                    Order order = new Order(edtIdOrder, edtIdStaff, spnKind, edtDate);
//                    db.insertRow(order);
//
//                    Toast.makeText(getActivity(), "thêm thành công ", Toast.LENGTH_SHORT).show();
//                    Fragment fragment;
//                    fragment = new FragListProduct();
//                    loadFrag(fragment);
//                }
            }
        });

        binding.edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });
        return binding.getRoot();
    }

    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                binding.edtDate.setText(simpleDateFormat.format(calendar.getTime()));

            }
        }, year, month, day);
        datePickerDialog.show();
    }


}