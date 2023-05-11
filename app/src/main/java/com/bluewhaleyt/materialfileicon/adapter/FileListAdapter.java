package com.bluewhaleyt.materialfileicon.adapter;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.bluewhaleyt.common.DateTimeFormatUtil;
import com.bluewhaleyt.common.DynamicColorsUtil;
import com.bluewhaleyt.filemanagement.FileUtil;
import com.bluewhaleyt.materialfileicon.R;
import com.bluewhaleyt.materialfileicon.core.FileHelper;
import com.bluewhaleyt.materialfileicon.core.FileIconHelper;
import com.bluewhaleyt.materialfileicon.model.FileItem;
import com.bluewhaleyt.materialfileicon.utils.Utils;
import com.bluewhaleyt.unit.UnitUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
//        Collections.sort(mItems, (o1, o2) -> {
//            if (o1.isDirectory() && !o2.isDirectory()) {
//                return -1;
//            } else if (!o1.isDirectory() && o2.isDirectory()) {
//                return 1;
//            } else {
//                return o1.getName().compareToIgnoreCase(o2.getName());
//            }
//        });
        Collections.sort(mItems, (o1, o2) -> {
            String name1 = o1.getName();
            String name2 = o2.getName();
            boolean isDir1 = o1.isDirectory();
            boolean isDir2 = o2.isDirectory();

            var fileHelper1 = new FileHelper(o1.getPath());
            var fileHelper2 = new FileHelper(o2.getPath());

            // 如果 o1 或 o2 是 Android 設備預設建有的資料夾，優先排序
            if (isDir1 && fileHelper1.isAndroidDefaultDirectories(name1)) {
                return -1;
            } else if (isDir2 && fileHelper2.isAndroidDefaultDirectories(name2)) {
                return 1;
            }

            // 如果只有 o1 或 o2 是資料夾，優先排序
            if (isDir1 && !isDir2) {
                return -1;
            } else if (!isDir1 && isDir2) {
                return 1;
            } else {
                // 其他情況按字母順序排序
                return name1.compareToIgnoreCase(name2);
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
        holder.tvFileName.setText(item.getName());
        holder.tvFilePath.setText(item.getPath());
        holder.imgIcon.setImageResource(item.getIcon());

        var context = holder.itemView.getContext();
        var alpha = 20;
        var color = Utils.getDominantColor(holder.imgIcon.getDrawable());
        var gd = new GradientDrawable();
        gd.setColor(ColorUtils.setAlphaComponent(color, alpha));
        gd.setCornerRadius(20);
        holder.layoutIcon.setBackground(gd);

        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(item);
            }
        });

        var empty = "Empty";
        var dirCount = FileUtil.getFileAmountOfPath(item.getPath()) != 0 ? FileUtil.getFileAmountOfPath(item.getPath()) + " Files" : empty;
        var fileCount = FileUtil.getFileSizeOfPath(item.getPath()) != 0 ? UnitUtil.byteHumanize(FileUtil.getFileSizeOfPath(item.getPath())) : empty;
        holder.tvFileSize.setText(FileUtil.isDirectory(item.getPath()) ? dirCount : fileCount);
        holder.tvFileSize.setTextColor(
                FileUtil.isDirectory(item.getPath())
                ? new DynamicColorsUtil(context).getColorPrimary()
                : getStrengthColorOfFileSize(item.getPath(), 100)
        );

        holder.tvFileLastModifiedTime.setText(
                FileUtil.getFileLastModifiedTimeFormatString(
                        item.getPath(), DateTimeFormatUtil.FORMAT_DATE + " " + DateTimeFormatUtil.FORMAT_TIME_AM_PM
                )
        );

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(FileItem item);
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFileName, tvFilePath, tvFileSize, tvFileLastModifiedTime;
        private final ImageView imgIcon;
        private final LinearLayout layoutIcon;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tv_file_name);
            tvFilePath = itemView.findViewById(R.id.tv_file_path);
            tvFileSize = itemView.findViewById(R.id.tv_file_size);
            tvFileLastModifiedTime = itemView.findViewById(R.id.tv_file_last_modified_time);
            imgIcon = itemView.findViewById(R.id.img_file_icon);
            layoutIcon = itemView.findViewById(R.id.layout_img_file_icon);
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
        var fileIconHelper = new FileIconHelper(file.getAbsolutePath());
        return fileIconHelper.getFileIcon();
    }

    private int getStrengthColorOfFileSize(String filePath, int max) {
        File file = new File(filePath);
        long fileSize = file.length();
        double fileSizeInKB = fileSize / 1024.0; // Convert to KB
        double fileSizeInMB = fileSizeInKB / 1024.0; // Convert to MB

//        double strengthLevel = fileSizeInMB > 100.0 ? 1.0 : fileSizeInMB / 100.0;
        double strengthLevel = fileSizeInMB / max;

        int color;
        if (strengthLevel < 0.33) {
            color = 0xFF1dd1a1; // green
        } else if (strengthLevel < 0.67) {
            color = 0xFFff9f43; // yellow
        } else {
            color = 0xFFee5253; // red
        }
        return color;
    }
}
