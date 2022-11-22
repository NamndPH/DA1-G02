package com.zrapp.warehouse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.zrapp.warehouse.Fragment.FragOrder.FragOrder;
import com.zrapp.warehouse.Fragment.FragProd.FragProd;
import com.zrapp.warehouse.Fragment.FragStaff.FragStaff;
import com.zrapp.warehouse.Fragment.FragStatistic;
import com.zrapp.warehouse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFrag(new FragProd());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        binding.searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.searchBar.isIconfiedByDefault()) {
                    binding.searchBar.onActionViewExpanded();
                    binding.tvToolbar.setVisibility(View.GONE);
                } else {
                    binding.searchBar.onActionViewCollapsed();
                    binding.tvToolbar.setVisibility(View.VISIBLE);
                }
            }
        });

        // Làm filter dùng binding.searchBar.setOnQueryTextFocusChangeListener();
        // Lấy chuỗi tìm kiếm dùng binding.searchBar.getQuery();
        // hoặc dùng phương thức bên dưới được cả 2

        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_sp:
                        loadFrag(new FragProd());
                        binding.tvToolbar.setText("Sản phẩm");
                        break;
                    case R.id.nav_donhang:
                        loadFrag(new FragOrder());
                        binding.tvToolbar.setText("Đơn hàng");
                        break;
                    case R.id.nav_nv:
                        loadFrag(new FragStaff());
                        binding.tvToolbar.setText("Nhân viên");
                        break;
                    case R.id.nav_thongke:
                        loadFrag(new FragStatistic());
                        binding.tvToolbar.setText("Thống kê");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirmRequest:
                Toast.makeText(this, "Tính năng chưa phát triển", Toast.LENGTH_LONG).show();
                break;

            case R.id.changePass:
                Toast.makeText(this, "Tính năng chưa phát triển", Toast.LENGTH_LONG).show();
                break;

            case R.id.logOut:
                logOut();
                break;

            case R.id.exit:
                exit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadFrag(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("Không", null);
        builder.show();
    }

    public void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thoát");
        builder.setMessage("Bạn có chắc chắn muốn thoát chương trình?");
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
            }
        });
        builder.setPositiveButton("Không", null);
        builder.show();
    }
}