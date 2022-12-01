package com.zrapp.warehouse.Fragment.FragStaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.MainActivity;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.ActivityProdUpdateBinding;
import com.zrapp.warehouse.databinding.ActivityStaffUpdateBinding;
import com.zrapp.warehouse.model.Staff;

import java.util.List;

public class ActivityStaffUpdate extends AppCompatActivity {

    ActivityStaffUpdateBinding bindingActivityStaff;
    List<Staff> listUpdate;
    StaffDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingActivityStaff = ActivityStaffUpdateBinding.inflate(getLayoutInflater());
        setContentView(bindingActivityStaff.getRoot());

        dao = new StaffDAO();
        listUpdate = dao.getAll();

        bindingActivityStaff.toolbarUpdateStaff.setTitle("Cập nhật nhân viên");
        bindingActivityStaff.toolbarUpdateStaff.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bindingActivityStaff.edNameUpdatenv.setText(listUpdate.get(FragStaffList.stt).getName());
        bindingActivityStaff.edUsernameUpdatenv.setText(listUpdate.get(FragStaffList.stt).getUsername());
        bindingActivityStaff.edSdtUpdatenv.setText(listUpdate.get(FragStaffList.stt).getTel());
        bindingActivityStaff.edPositionUpdate.setText(listUpdate.get(FragStaffList.stt).getPost());

        bindingActivityStaff.btnUpdateNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edNameNV = bindingActivityStaff.edNameUpdatenv.getText().toString();
                String edUsernameNV = bindingActivityStaff.edUsernameUpdatenv.getText().toString();
                String edSdtNV = bindingActivityStaff.edSdtUpdatenv.getText().toString();
                String edPostNV = bindingActivityStaff.edPositionUpdate.getText().toString();
                String maNv = listUpdate.get(FragStaffList.stt).getId();

                Staff staffUpdate = new Staff();
                staffUpdate.setName(edNameNV);
                staffUpdate.setUsername(edUsernameNV);
                staffUpdate.setTel(edSdtNV);
                staffUpdate.setId(maNv);
                staffUpdate.setPost(edPostNV);

                dao.updateRow(staffUpdate);
                Toast.makeText(getApplicationContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                FragStaffList.flag = false;
                finish();
            }
        });
    }

}