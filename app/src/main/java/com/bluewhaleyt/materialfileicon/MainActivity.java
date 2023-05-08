package com.bluewhaleyt.materialfileicon;

import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.bluewhaleyt.materialfileicon.core.FileIconHelper;
import com.bluewhaleyt.materialfileicon.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etFilePath.setText(Environment.getExternalStorageDirectory() + "/");
        check(binding.etFilePath.getText().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
    }

    private void initialize() {
        binding.etFilePath.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                check(s.toString());
            }
        });
    }

    private void check(String filePath) {
        var inputLayout = binding.inputLayoutFileName;
        var fileIconHelper = new FileIconHelper(filePath);

        fileIconHelper.bindIcon(binding.imgIcon);
        inputLayout.setStartIconDrawable(fileIconHelper.getFileIcon());
        inputLayout.setStartIconTintList(null);

        var file = new File(filePath);

        if (file.exists()) inputLayout.setErrorEnabled(false);
        else inputLayout.setError(getString(R.string.invalid_file_path));
    }
}