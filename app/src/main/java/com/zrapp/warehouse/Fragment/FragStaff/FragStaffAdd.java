package com.zrapp.warehouse.Fragment.FragStaff;

import static com.zrapp.warehouse.MainActivity.loadFrag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.databinding.FragStaffAddBinding;
import com.zrapp.warehouse.model.Staff;

import java.util.List;


public class FragStaffAdd extends Fragment {
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

        Log.d("TAG Kiem Tra: ", "onViewCreated: "+FragStaffList.flag);
        //Thêm nhân viên
        binding.btnXacnhanNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNhanVien();
            }
        });

        if (FragStaffList.flag == true){
            binding.edNameFragthemnv.setText(listnv.get(FragStaffList.stt).getName());
            binding.edUsernameFragthemnv.setText(listnv.get(FragStaffList.stt).getUsername());
            binding.edPasswordFragthemnv.setText(listnv.get(FragStaffList.stt).getPass());
            binding.edSdtFragthemnv.setText(listnv.get(FragStaffList.stt).getTel());

        }
    }

    private void addNhanVien() {
        Log.d("TAG Kiem Tra: ", "addNhanVien: "+FragStaffList.stt);
        String edNameNV = binding.edNameFragthemnv.getText().toString();
        String edUsernameNV = binding.edUsernameFragthemnv.getText().toString();
        String edPasswordNV = binding.edPasswordFragthemnv.getText().toString();
        String edSdtNV = binding.edSdtFragthemnv.getText().toString();
        String maNv = listnv.get(FragStaffList.stt).getId();
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
        staff.setUsername(edUsernameNV);
        staff.setPass(edPasswordNV);
        staff.setTel(edSdtNV);

        Staff staffUpdate = new Staff();
        staffUpdate.setName(edNameNV);
        staffUpdate.setUsername(edUsernameNV);
        staffUpdate.setPass(edPasswordNV);
        staffUpdate.setTel(edSdtNV);
        staffUpdate.setId(maNv);


            if (FragStaffList.flag==true){
                dao.updateRow(staffUpdate);
                loadFrag(new FragStaffList());
                Toast.makeText(getActivity(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                FragStaffList.flag = false;
                return;
            }else {
                for (int i = 0; i < listnv.size(); i++) {
                    if (edUsernameNV.equals(listnv.get(i).getUsername())){

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
                dao.insertStaff(staff,1);
                loadFrag(new FragStaffList());
                Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                FragStaffList.flag = false;
            }
    }
}
