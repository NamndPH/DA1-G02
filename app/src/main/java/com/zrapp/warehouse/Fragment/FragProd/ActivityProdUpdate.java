package com.zrapp.warehouse.Fragment.FragProd;

import static com.zrapp.warehouse.SigninActivity.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zrapp.warehouse.ChangePassActivity;
import com.zrapp.warehouse.DAO.ProductDAO;
import com.zrapp.warehouse.MainActivity;
import com.zrapp.warehouse.RequestActivity;
import com.zrapp.warehouse.SigninActivity;
import com.zrapp.warehouse.databinding.ActivityProdUpdateBinding;
import com.zrapp.warehouse.model.Product;
import com.zrapp.warehouse.R;

import java.util.List;

public class ActivityProdUpdate extends AppCompatActivity {
    ActivityProdUpdateBinding binding;
    List<Product> listP;

    ProductDAO dao_prod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProdUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("pos");

        dao_prod = new ProductDAO();
        listP = dao_prod.getAll_Prod();

        binding.edNameUD.setText(listP.get(index).getName());
        binding.edViTriUD.setText(listP.get(index).getViTri());
        binding.edPriceUD.setText(listP.get(index).getPrice() + "");
        binding.edCostPriceUD.setText(listP.get(index).getCost_price() + "");

        binding.btnUpdateProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSp = listP.get(index).getId();
                String nameSp = binding.edNameUD.getText().toString();
                String locationSp = binding.edViTriUD.getText().toString();
                int priceSp = Integer.valueOf(binding.edPriceUD.getText().toString());
                int costpriceSp = Integer.valueOf(binding.edCostPriceUD.getText().toString());
                String img = "";

                Product prod_update = new Product();
                prod_update.setId(idSp);
                prod_update.setName(nameSp);
                prod_update.setViTri(locationSp);
                prod_update.setPrice(priceSp);
                prod_update.setCost_price(costpriceSp);
                prod_update.setImg(img);

                dao_prod.updateProd(prod_update);
                Toast.makeText(ActivityProdUpdate.this, "Cập nhật sản phẩm thành công!!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ActivityProdUpdate.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (account.getPost().equals("Nhân viên")) {
            getMenuInflater().inflate(R.menu.menu_option_staff, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_option_manager, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.confirmRequest:
                Intent intentR = new Intent(getApplicationContext(), RequestActivity.class);
                startActivity(intentR);
                break;

            case R.id.changePass:
                Intent intentC = new Intent(getApplicationContext(), ChangePassActivity.class);
                startActivity(intentC);
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

    public void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityProdUpdate.this);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("Không", null);
        builder.show();
    }

    public void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityProdUpdate.this);
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