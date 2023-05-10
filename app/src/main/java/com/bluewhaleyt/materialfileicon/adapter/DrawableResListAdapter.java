package com.bluewhaleyt.materialfileicon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluewhaleyt.component.dialog.DialogUtil;
import com.bluewhaleyt.materialfileicon.R;
import com.bluewhaleyt.materialfileicon.model.DrawableResModel;

import java.util.ArrayList;

public class DrawableResListAdapter extends RecyclerView.Adapter<DrawableResListAdapter.ViewHolder> {

    ArrayList<DrawableResModel> list;
    DrawableResModel model;

    public DrawableResListAdapter(ArrayList<DrawableResModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_icon_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model = list.get(position);
        holder.stringCount.setText(model.getCount()+"");
        holder.drawableRes.setText(model.getDrawableRes());
        holder.drawablePreview.setImageResource(model.getDrawablePreview());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stringCount, drawableRes;
        ImageView drawablePreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stringCount = itemView.findViewById(R.id.tvCount);
            drawableRes = itemView.findViewById(R.id.tvDrawableRes);
            drawablePreview = itemView.findViewById(R.id.ivDrawablePreview);

        }
    }

}