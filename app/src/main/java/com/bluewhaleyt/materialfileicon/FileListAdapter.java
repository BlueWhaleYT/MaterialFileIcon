package com.bluewhaleyt.materialfileicon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluewhaleyt.filemanagement.FileComparator;
import com.bluewhaleyt.materialfileicon.core.FileIconHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FileViewHolder> {

    private List<FileItem> mItems;

    private OnItemClickListener mOnItemClickListener;

    public FileListAdapter(List<FileItem> items) {
        mItems = items;
        setup();
    }

    public void setItems(List<FileItem> items) {
        mItems = items;
        setup();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private void setup() {
        Collections.sort(mItems, (o1, o2) -> {
            if (o1.isDirectory() && !o2.isDirectory()) {
                return -1;
            } else if (!o1.isDirectory() && o2.isDirectory()) {
                return 1;
            } else {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_file_list_item, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        FileItem item = mItems.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPath.setText(item.getPath());
        holder.ivIcon.setImageResource(item.getIcon());
        if (item.isDirectory()) {

        } else {

        }
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(FileItem item);
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName, tvPath;
        private final ImageView ivIcon;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_file_name);
            tvPath = itemView.findViewById(R.id.tv_file_path);
            ivIcon = itemView.findViewById(R.id.img_file_icon);
        }
    }

    public static List<FileItem> getFileItems(String path) {
        List<FileItem> fileItems = new ArrayList<>();
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                FileItem item = new FileItem(
                        file.getName(),
                        file.getPath(),
                        getIconForFile(file),
                        file.isDirectory()
                );
                fileItems.add(item);
            }
        }
        return fileItems;
    }

    private static int getIconForFile(File file) {
        return new FileIconHelper(file.getAbsolutePath()).getFileIcon();
    }
}
