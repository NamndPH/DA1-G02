package com.zrapp.warehouse.Fragment.FragProd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.zrapp.warehouse.DAO.ProductDAO;
import com.zrapp.warehouse.MainActivity;
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

        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("pos");

        dao_prod = new ProductDAO();
        listP = dao_prod.getAll_Prod();


        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar02);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.edNameUD.setText(listP.get(index).getName());
        binding.edViTriUD.setText(listP.get(index).getViTri());
        binding.edPriceUD.setText(listP.get(index).getPrice()+"");
        binding.edCostPriceUD.setText(listP.get(index).getCost_price()+"");




        binding.btnUpdateProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSp = listP.get(index).getId();
                String nameSp = binding.edNameUD.getText().toString();
                String locationSp =  binding.edViTriUD.getText().toString();
                int priceSp =  Integer.valueOf(binding.edPriceUD.getText().toString());
                int costpriceSp =  Integer.valueOf(binding.edCostPriceUD.getText().toString());
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



}