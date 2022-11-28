package com.zrapp.warehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.zrapp.warehouse.DAO.OrderDao;
import com.zrapp.warehouse.Fragment.FragOrder.FragOrderDetail;
import com.zrapp.warehouse.MainActivity;
import com.zrapp.warehouse.databinding.ItemOrderBinding;
import com.zrapp.warehouse.model.Order;
import com.zrapp.warehouse.R;
import com.zrapp.warehouse.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> implements Filterable {

    private Context context;
    private int layout;
    private ArrayList<Order> OrderList;
    private ArrayList<Order> listFilter;
    private OrderDao db;

    public OrderAdapter(Context context, int layout, ArrayList<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.OrderList = orderList;
        this.listFilter = orderList;
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
                MainActivity.loadFrag(fragment);
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


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    OrderList = listFilter;
                }else {
                    ArrayList<Order> listO = new ArrayList<>();
                    for (Order order : listFilter){
                        if (order.getId_order().toLowerCase().contains(strSearch.toLowerCase())){
                            listO.add(order);
                        }
                    }

                    OrderList = listO;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = OrderList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                OrderList = (ArrayList<Order>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
