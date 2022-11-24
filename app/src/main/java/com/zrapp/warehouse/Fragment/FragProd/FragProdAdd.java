package com.zrapp.warehouse.Fragment.FragProd;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zrapp.warehouse.DAO.ProductDAO;
import com.zrapp.warehouse.MainActivity;
import com.zrapp.warehouse.databinding.FragProdAddBinding;
import com.zrapp.warehouse.model.Product;


public class FragProdAdd extends Fragment {
    FragProdAddBinding binding;
    ProductDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragProdAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dao = new ProductDAO();

        binding.btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSP = binding.edNameProd.getText().toString();
                String viTri = binding.edViTriProd.getText().toString();
                String giaBan = binding.edPriceProd.getText().toString();
                String giaVon = binding.edCostPriceProd.getText().toString();

                if (tenSP.isEmpty()) {
                    binding.edNameProd.setHint("Vui lòng không để trống!");
                    binding.edNameProd.setHintTextColor(Color.parseColor("#E53935"));
                } else if (viTri.isEmpty()) {
                    binding.edViTriProd.setHint("Vui lòng không để trống!");
                    binding.edViTriProd.setHintTextColor(Color.parseColor("#E53935"));
                } else if (giaBan.isEmpty()) {
                    binding.edPriceProd.setHint("Vui lòng không để trống!");
                    binding.edPriceProd.setHintTextColor(Color.parseColor("#E53935"));
                } else {
                    Product model = new Product();
                    model.setName(tenSP);
                    model.setViTri(viTri);
                    model.setPrice(Integer.valueOf(giaBan));
                    try {

                    } catch (Exception e) {
                        model.setCost_price(0);
                    }
                    model.setImg("");
                    dao.insertProd(model);
                    MainActivity.loadFrag(new FragProdList());
                }
            }
        });
    }
}
