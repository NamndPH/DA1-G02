package com.zrapp.warehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zrapp.warehouse.Model.Staff;
import com.zrapp.warehouse.R;

import java.util.ArrayList;

public class StaffAdapter extends ArrayAdapter<Staff> {

    private Context context;
    private int resource;
    private ArrayList<Staff> list;
    private LayoutInflater inflater;

    public StaffAdapter(@NonNull Context context, int resource, ArrayList<Staff> list) {
        super(context,resource,list);
        this.context = context;
        this.resource = resource;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StaffViewHolder holder;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_staff, null);

            holder = new StaffViewHolder();
            holder.tv_name = convertView.findViewById(R.id.tvname_nhanvien);
            holder.tv_sdt = convertView.findViewById(R.id.tvsdt_nhanvien);
            convertView.setTag(holder);
        }else{
            holder = (StaffViewHolder)convertView.getTag();
        }

        //gán dữ liệu
        Staff nv = list.get(position);
        holder.tv_name.setText(nv.getName());
        holder.tv_sdt.setText(nv.getTel());

        return convertView;
    }

    public class StaffViewHolder{
        TextView tv_name, tv_sdt;
    }
}
