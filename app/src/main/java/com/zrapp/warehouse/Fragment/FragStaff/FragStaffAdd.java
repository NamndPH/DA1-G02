package com.zrapp.warehouse.Fragment.FragStaff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.Model.Staff;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragStaffAddBinding;


public class FragStaffAdd extends Fragment {

    FragStaffAddBinding binding;
    StaffDAO dao;

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

        //Thêm nhân viên
        binding.btnXacnhanNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNhanVien();
            }
        });
    }

    private void addNhanVien() {
        String edNameNV = binding.edNameFragthemnv.getText().toString();
        String edUsernameNV = binding.edUsernameFragthemnv.getText().toString();
        String edPasswordNV = binding.edPasswordFragthemnv.getText().toString();
        String edSdtNV = binding.edSdtFragthemnv.getText().toString();
        String regexSdt = "^\\d{10}$";

        if (edNameNV.equals("")
                || edUsernameNV.equals("")
                || edPasswordNV.equals("")){
            binding.edNameFragthemnv.setHint("Vui lòng không để trống!");
            binding.edUsernameFragthemnv.setHint("Vui lòng không để trống!");
            binding.edPasswordFragthemnv.setHint("Vui lòng không để trống!");

            return;
        }

        if (edSdtNV.length() >=1 && edSdtNV.length() <10){
            binding.edSdtFragthemnv.setText("");
            binding.edSdtFragthemnv.setHint("Vui lòng nhập đầy đủ 10 số!");
            return;
      }

        if(edSdtNV.length()>0 && edSdtNV.matches(regexSdt) == false){
            binding.edSdtFragthemnv.setText("");
            binding.edSdtFragthemnv.setHint("Không đúng định dạng số điện thoại!");
            return;
        }

        Staff staff = new Staff();
        staff.setName(edNameNV);
        staff.setUsername(edPasswordNV);
        staff.setPass(edUsernameNV);
        staff.setTel(edSdtNV);
        dao = new StaffDAO();
        dao.insertStaff(staff);

        loadFrag(new FragStaffList());
        Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
    }

    public void loadFrag(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
