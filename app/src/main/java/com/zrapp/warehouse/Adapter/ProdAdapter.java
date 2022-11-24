package com.zrapp.warehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zrapp.warehouse.databinding.ItemProdBinding;
import com.zrapp.warehouse.model.Product;
import com.zrapp.warehouse.R;

import java.util.List;

public class ProdAdapter extends BaseAdapter {

    private Context context;
    private List<Product> list;
    private int layout;


    public ProdAdapter(Context context, List<Product> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int i, View convertView, ViewGroup container) {
        ItemProdBinding binding;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = ItemProdBinding.inflate(inflater, container, false);
            convertView = binding.getRoot();
            convertView.setTag(R.layout.item_prod, binding);
        } else {
            binding = ((ItemProdBinding ) convertView.getTag(R.layout.item_prod));
        }

        binding.tvnameProd.setText(list.get(i).getName());
        binding.tvPriceProd.setText(String.valueOf(list.get(i).getPrice())+" vnđ");
        binding.imgItem.setImageResource(R.drawable.img_prod_default);

        return convertView;
    }
}
