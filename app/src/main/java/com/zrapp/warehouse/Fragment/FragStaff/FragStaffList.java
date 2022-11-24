package com.zrapp.warehouse.Fragment.FragStaff;

import static com.zrapp.warehouse.MainActivity.loadFrag;

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
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.zrapp.warehouse.Adapter.StaffAdapter;
import com.zrapp.warehouse.DAO.StaffDAO;
import com.zrapp.warehouse.MainActivity;
import com.zrapp.warehouse.model.Staff;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragStaffListBinding;

import java.util.ArrayList;

public class FragStaffList extends Fragment implements PopupMenu.OnMenuItemClickListener {

    FragStaffListBinding binding;
    StaffDAO dao;
    ArrayList<Staff> listStaff;
    StaffAdapter staffAdapter;
    public static int stt;
    public static boolean flag;

    public FragStaffList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragStaffListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fabAddNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new FragStaffAdd());
            }
        });

        //Hiển thi danh sách nhân viên
        dao = new StaffDAO();
        changeListStaff();
        binding.lvNv.setDivider(null);
        binding.lvNv.setDividerHeight(0);

        binding.lvNv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                stt = i;
                showPopup(view);
                return false;
            }
        });

        MainActivity.binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Xử lý chuỗi tìm kiếm;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Tạo filter để lọc cho đẹp
                return false;
            }
        });
    }

    private void changeListStaff() {
        listStaff = dao.getAll();
        staffAdapter = new StaffAdapter(getActivity(), R.layout.item_staff, listStaff);
        binding.lvNv.setAdapter(staffAdapter);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getActivity(), v);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popup.setGravity(Gravity.RIGHT);
        }
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu_staff);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.inforStaff:
                Toast.makeText(getActivity(), "Information Staff", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.changeStaff:
                flag = true;
                loadFrag(new FragStaffAdd());
                Log.d("TAG Kiem Tra: ", "onMenuItemClick: " + flag);
                return true;
            case R.id.deleteStaff:
                Staff staff = listStaff.get(stt);
                deleteStaff(staff.getId(), staff.getName());
                return true;
            default:
                return false;
        }
    }

    public void deleteStaff(String maNv, String nameNv) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setMessage("Bạn muốn xoá nhân viên " + nameNv + "?");
        mBuilder.setPositiveButton("Xác Nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.deleteRow(maNv);
                changeListStaff();
                Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_LONG).show();
                dialogInterface.cancel();
            }
        });


        mBuilder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.show();
    }
}
