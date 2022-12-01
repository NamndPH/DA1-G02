package com.zrapp.warehouse.Fragment.FragStaff;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragStaffAddBinding;
import com.zrapp.warehouse.model.Staff;

import java.util.List;


public class FragStaffAdd extends Fragment implements PopupMenu.OnMenuItemClickListener {
    FragStaffAddBinding binding;
    StaffDAO dao;
    List<Staff> listnv;

    public FragStaffAdd() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragStaffAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = new StaffDAO();
        listnv = dao.getAll();

        binding.dropdownPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        //Thêm nhân viên
        binding.btnXacnhanNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNhanVien();
            }
        });

    }

    //    Thêm nhân viên
    private void addNhanVien() {
        Log.d("TAG Kiem Tra: ", "addNhanVien: " + FragStaffList.stt);
        String edNameNV = binding.edNameFragthemnv.getText().toString();
        String edUsernameNV = binding.edUsernameFragthemnv.getText().toString();
        String edPasswordNV = binding.edPasswordFragthemnv.getText().toString();
        String edSdtNV = binding.edSdtFragthemnv.getText().toString();
        String edPostNV = binding.edPosition.getText().toString();
        String regexSdt = "^\\d{10}$";

        if (edNameNV.equals("")
                || edUsernameNV.equals("")
                || edPasswordNV.equals("")) {
            binding.edNameFragthemnv.setHint("Vui lòng không để trống!");
            binding.edUsernameFragthemnv.setHint("Vui lòng không để trống!");
            binding.edPasswordFragthemnv.setHint("Vui lòng không để trống!");
            binding.edPosition.setHint("Vui lòng không để trống!");
            return;
        }

        if (edSdtNV.length() >= 1 && edSdtNV.length() < 10) {
            binding.edSdtFragthemnv.setText("");
            binding.edSdtFragthemnv.setHint("Vui lòng nhập đầy đủ 10 số!");
            return;
        }

        if (edSdtNV.length() > 0 && edSdtNV.matches(regexSdt) == false) {
            binding.edSdtFragthemnv.setText("");
            binding.edSdtFragthemnv.setHint("Không đúng định dạng số điện thoại!");
            return;
        }

        Staff staff = new Staff();
        staff.setName(edNameNV);
        staff.setUsername(edUsernameNV);
        staff.setPass(edPasswordNV);
        staff.setTel(edSdtNV);
        staff.setPost(edPostNV);

        for (int i = 0; i < listnv.size(); i++) {
            if (edUsernameNV.equals(listnv.get(i).getUsername())) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setMessage("Tên tài khoản đã tồn tại!");
                mBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.show();
                return;
            }
        }
        dao.insertStaff(staff, 1);
        loadFrag(new FragStaffList());
        Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
        FragStaffList.flag = false;
    }

    public void loadFrag(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getActivity(), v);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popup.setGravity(Gravity.RIGHT);
        }
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_position);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.quan_ly:
                binding.edPosition.setText("Quản lý");
                return true;
            case R.id.nhan_vien:
                binding.edPosition.setText("Nhân viên");
                return true;
            default:
                return false;
        }
    }
}
