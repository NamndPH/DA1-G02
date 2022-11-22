package com.zrapp.warehouse.Fragment.FragProd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zrapp.warehouse.DAO.ProductDAO;
import com.zrapp.warehouse.Model.Product;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragProdBinding;

import java.util.List;

public class FragProd extends Fragment {

    FragProdBinding binding;
    ProductDAO dao;
    List<Product> listsp;

    public FragProd() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragProdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dao = new ProductDAO();
        listsp = dao.getAll_Prod();
        if (!listsp.isEmpty()) {
            loadFrag(new FragProdList());
        }

        binding.btnAddPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFrag(new FragProdAdd());
            }
        });
    }


    public void loadFrag(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}