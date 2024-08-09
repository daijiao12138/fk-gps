package com.android.bluetooths.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.bluetooths.R;
import com.android.bluetooths.database.LocationDao;
import com.android.bluetooths.database.LocationData;
import com.android.bluetooths.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class CoordinateAdapter extends RecyclerView.Adapter<CoordinateAdapter.Holder> {


    private ArrayList<LocationData> mList;
    private LocationDao mLocationDao;

    CoordinateAdapter(ArrayList<LocationData> list, LocationDao locationDao) {
        mList = list;
        mLocationDao = locationDao;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coordinate_adapter,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(mList.get(position).toString());
        int ps = position;
        LocationData ld = mList.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mLocationDao.deleteLocation(ld);
                mList.remove(ps);
                notifyItemChanged(ps);
                notifyItemRangeChanged(ps, mList.size());
                Util.DisplayToast(Application.getInstance(), ld.toString());
                return true;
            }
        });
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
        }
    }
}