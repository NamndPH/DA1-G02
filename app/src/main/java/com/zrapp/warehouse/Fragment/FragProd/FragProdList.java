package com.zrapp.warehouse.Fragment.FragProd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zrapp.warehouse.Adapter.ProdAdapter;
import com.zrapp.warehouse.DAO.ProductDAO;
import com.zrapp.warehouse.Model.Product;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.FragProdListBinding;
import java.util.ArrayList;
import java.util.List;

public class FragProdList extends Fragment {

    FragProdListBinding binding;
    List<Product> list = new ArrayList<>();
    ProductDAO dao_prod;
    ProdAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragProdListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dao_prod = new ProductDAO();
        list = dao_prod.getAll_Prod();

        adapter = new ProdAdapter(getContext(),list,R.layout.item_prod);

        binding.lvSp.setDivider(null);
        binding.lvSp.setDividerHeight(0);

        binding.lvSp.setAdapter(adapter);

        binding.btnFloatAddProd.setOnClickListener(new View.OnClickListener() {
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