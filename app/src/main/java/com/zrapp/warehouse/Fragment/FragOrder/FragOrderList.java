package com.zrapp.warehouse.Fragment.FragOrder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.zrapp.warehouse.Adapter.OrderAdapter;
import com.zrapp.warehouse.DAO.OrderDao;
import com.zrapp.warehouse.MainActivity;
import com.zrapp.warehouse.databinding.FragOrderListBinding;
import com.zrapp.warehouse.model.Order;
import com.zrapp.warehouse.R;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class FragOrderList extends Fragment {
    FragOrderListBinding binding;
    private List<Order> OrderList = new ArrayList();
    private OrderAdapter adapter;

    public FragOrderList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragOrderListBinding.inflate(inflater, container, false);

        OrderDao db = new OrderDao();
        OrderList = db.getAll();

        adapter = new OrderAdapter((Context) getActivity(), R.layout.item_order, (ArrayList<Order>) OrderList);
        binding.rcvOrder.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rcvOrder.setLayoutManager(gridLayoutManager);

        for (int i = 0; i < OrderList.size(); i++) {
            Order order = OrderList.get(i);
            Log.d("zzzzz", "onCreate: phần tử thứ " + i + ":  id = " + order.getId_order() + ", name = " + order.getId_staff());
        }
        binding.btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;
                fragment = new FragOrderAdd();
                MainActivity.loadFrag(fragment);
            }
        });

        MainActivity.binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return binding.getRoot();
    }
}