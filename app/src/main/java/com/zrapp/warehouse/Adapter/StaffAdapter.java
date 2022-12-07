    package com.zrapp.warehouse.Adapter;

    import static com.zrapp.warehouse.RequestActivity.adapter;
    import static com.zrapp.warehouse.RequestActivity.binding;

    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.BaseAdapter;
    import android.widget.Filter;
    import android.widget.Filterable;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AlertDialog;

    import com.squareup.picasso.Picasso;
    import com.squareup.picasso.Target;
    import com.zrapp.warehouse.ChangePassActivity;
    import com.zrapp.warehouse.DAO.StaffDAO;
    import com.zrapp.warehouse.RequestActivity;
    import com.zrapp.warehouse.SigninActivity;
    import com.zrapp.warehouse.model.Staff;
    import com.zrapp.warehouse.R;

    import java.util.ArrayList;

    public class StaffAdapter extends BaseAdapter implements Filterable {

        private Context context;
        private int resource;
        private ArrayList<Staff> list;
        private LayoutInflater inflater;
        public ArrayList<Staff> listFilter;
        StaffDAO dao;

        public StaffAdapter(@NonNull Context context, int resource, ArrayList<Staff> list) {
            this.context = context;
            this.resource = resource;
            this.list = list;
            this.listFilter = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dao = new StaffDAO();
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
                convertView = inflater.inflate(resource, null);

                holder = new StaffViewHolder();
                holder.tv_name = convertView.findViewById(R.id.tvname_nhanvien);
                holder.tv_sdt = convertView.findViewById(R.id.tvsdt_nhanvien);
                holder.img_avt = convertView.findViewById(R.id.img_item_avt);
                convertView.setTag(holder);
            }else{
                holder = (StaffViewHolder)convertView.getTag();
            }

            //gán dữ liệu
            holder.tv_name.setText(list.get(position).getName());
            holder.tv_sdt.setText(list.get(position).getTel());
            if (list.get(position).getImg().equals("null")){
                holder.img_avt.setImageResource(R.drawable.avatar_default);
            }else {
                Picasso.get().load(list.get(position).getImg()).into(holder.img_avt);
            }
            return convertView;
        }

        public class StaffViewHolder{
            TextView tv_name, tv_sdt;
            ImageView img_avt;
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
