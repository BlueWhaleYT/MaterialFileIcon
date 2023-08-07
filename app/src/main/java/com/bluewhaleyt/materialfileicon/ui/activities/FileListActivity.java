package com.bluewhaleyt.materialfileicon.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bluewhaleyt.crashdebugger.CrashDebugger;
import com.bluewhaleyt.materialfileicon.adapter.FileListAdapter;
import com.bluewhaleyt.materialfileicon.core.FileUtil;
import com.bluewhaleyt.materialfileicon.core.IntentUtil;
import com.bluewhaleyt.materialfileicon.core.PermissionUtil;
import com.bluewhaleyt.materialfileicon.core.Util;
import com.bluewhaleyt.materialfileicon.databinding.ActivityFileListBinding;
import com.bluewhaleyt.materialfileicon.model.FileItem;
import com.bluewhaleyt.materialfileicon.utils.Utils;

import java.io.File;
import java.util.List;

public class FileListActivity extends AppCompatActivity {

    private ActivityFileListBinding binding;

    private FileListAdapter adapter;

    private String mCurrentPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashDebugger.init(this);
        binding = ActivityFileListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (isGrantedPermissions()) setupFileList();
        else PermissionUtil.requestAllFileAccess(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean isGrantedPermissions() {
        return PermissionUtil.isAlreadyGrantedExternalStorageAccess();
    }

    private void setupFileList() {
        binding.rvFileList.setLayoutManager(new LinearLayoutManager(this));

        mCurrentPath = IntentUtil.intentGetString(this, "filePath");
        List<FileItem> fileItems = FileListAdapter.getFileItems(mCurrentPath);
        adapter = new FileListAdapter(fileItems);
        binding.rvFileList.setAdapter(adapter);

        adapter.setOnItemClickListener(item -> {
            if (item.isDirectory()) {
                mCurrentPath = item.getPath();
                List<FileItem> fileItems1 = FileListAdapter.getFileItems(mCurrentPath);
                adapter.setItems(fileItems1);
                adapter.notifyDataSetChanged();
            } else {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!mCurrentPath.equals(FileUtil.getExternalStoragePath())) {
            File currentDirectory = new File(mCurrentPath);
            File parentDirectory = currentDirectory.getParentFile();
            if (parentDirectory != null) {
                mCurrentPath = parentDirectory.getPath();
                List<FileItem> fileItems = FileListAdapter.getFileItems(mCurrentPath);
                adapter.setItems(fileItems);
                adapter.notifyDataSetChanged();
            }
        } else {
            super.onBackPressed();
        }
    }
}