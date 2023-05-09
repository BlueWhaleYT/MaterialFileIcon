package com.bluewhaleyt.materialfileicon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.bluewhaleyt.common.IntentUtil;
import com.bluewhaleyt.common.PermissionUtil;
import com.bluewhaleyt.crashdebugger.CrashDebugger;
import com.bluewhaleyt.filemanagement.FileComparator;
import com.bluewhaleyt.filemanagement.FileUtil;
import com.bluewhaleyt.materialfileicon.core.FileIconHelper;
import com.bluewhaleyt.materialfileicon.databinding.ActivityFileListBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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