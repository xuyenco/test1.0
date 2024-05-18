package com.example.test10.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test10.Model.ThiSinhModel;
import com.example.test10.R;

import java.util.ArrayList;
import java.util.List;

//khi có tìm kiếm thì phải implements Filterable
public class ThiSinhAdapter extends RecyclerView.Adapter<ThiSinhAdapter.ViewHolder> implements Filterable {
    //Recycleview khi được tạo chắc chắn phải có list và context (maybe?)
    ArrayList<ThiSinhModel> list;
    Context context;

    //2 list sau được tạo để sử dụng cho tìm kiếm
    ArrayList<ThiSinhModel> filteredList = new ArrayList<>();
    ArrayList<ThiSinhModel> filteredList2 = new ArrayList<>();

    //position cùng get set dùng để lấy vị trí ra khi chọn context menu
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //hàm tạp adapter
    public ThiSinhAdapter(ArrayList<ThiSinhModel> list, Context context){
        this.list = list;
        this.context = context;
        this.filteredList = list;
        this.filteredList2 = list;
    }

    //3 hàm sau được dùng để tạo recycleview (sub adapter)
    // hàm sau được dùng để quyết định item con sẽ hiển thị trong recycleview
    @NonNull
    @Override
    public ThiSinhAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thisinh_adapter,parent,false);
        return new ViewHolder(view);
    }

    // hàm sau để gắn giá trị cho item con
    @Override
    public void onBindViewHolder(@NonNull ThiSinhAdapter.ViewHolder holder, int position) {
        ThiSinhModel temp =list.get(position);
        holder.HoVaTen.setText(temp.getHoTen());
        holder.SoBaoDanh.setText(temp.getSoBaoDanh());
        holder.DiemTb.setText(String.valueOf(temp.getDiemTb()));

        //onlongclick để lấy vị trí khi giữ (cho context menu)
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });
    }

    //hàm khởi tạo viewholder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView SoBaoDanh;
        TextView HoVaTen;
        TextView DiemTb;
        public ViewHolder(View itemview){
            super(itemview);
            SoBaoDanh = itemview.findViewById(R.id.SoBaoDanhTXT);
            HoVaTen = itemview.findViewById(R.id.HoVaTenTXT);
            DiemTb = itemview.findViewById(R.id.DiemTbTXT);
            itemview.setOnCreateContextMenuListener(this);
        }

        //hàm tạo context menu
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(context);
            inflater.inflate(R.menu.context_menu, menu);
        }
    }

    //như tên
    @Override
    public int getItemCount() {
        return list.size();
    }

    //hàm tìm kiếm
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if(constraint==null || constraint.length()==0){
                    results.count = filteredList.size();
                    results.values = filteredList;
                }else{
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    List<ThiSinhModel> filteredListTemp = new ArrayList<>();
                    for(ThiSinhModel song : filteredList2){
                        if(song.getHoTen().toLowerCase().contains(filterPattern)){
                            filteredListTemp.add(song);
                        }
                    }

                    results.count = filteredListTemp.size();
                    results.values = filteredListTemp;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list =(ArrayList<ThiSinhModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }



}
