package com.zrapp.warehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.zrapp.warehouse.DAO.OrderDao;
import com.zrapp.warehouse.Fragment.FragOrder.FragOrderDetail;
import com.zrapp.warehouse.Model.Order;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.databinding.ItemOrderBinding;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private int layout;
    private ArrayList<Order> OrderList = new ArrayList<>();
    private OrderDao db;

    public OrderAdapter(Context context, int layout, ArrayList<Order> orderList) {
        this.context = context;
        this.layout = layout;
        OrderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemOrderBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_order, parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.binding.tvIdOrder.setText("Đơn hàng: "+OrderList.get(position).getId_order());
        holder.binding.tvIdStaff.setText("Mã nv: "+OrderList.get(position).getId_staff());
        holder.binding.tvKindOfOrder.setText("Loại: "+OrderList.get(position).getKindOfOrder());
        holder.binding.tvDate.setText("ngày tạo: "+OrderList.get(position).getDate());
        holder.binding.cvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;
                fragment = new FragOrderDetail();
                loadFrag(fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return OrderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        private final ItemOrderBinding binding;

        public OrderViewHolder(@NonNull ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void loadFrag(Fragment fragment) {
        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
