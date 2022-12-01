package com.zrapp.warehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zrapp.warehouse.model.Staff;
import com.zrapp.warehouse.R;

import java.util.ArrayList;

public class StaffAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private int resource;
    private ArrayList<Staff> list;
    private LayoutInflater inflater;
    public ArrayList<Staff> listFilter;

    public StaffAdapter(@NonNull Context context, int resource, ArrayList<Staff> list) {
        this.context = context;
        this.resource = resource;
        this.list = list;
        this.listFilter = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_sdt.setText(list.get(position).getTel());

        return convertView;
    }

    public class StaffViewHolder{
        TextView tv_name, tv_sdt;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    list = listFilter;
                }else {
                    ArrayList<Staff> listStaff = new ArrayList<>();
                    for (Staff staff : listFilter){
                        if (staff.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            listStaff.add(staff);
                        }
                    }

                    list = listStaff;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Staff>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
