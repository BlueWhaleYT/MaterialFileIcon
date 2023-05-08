package com.bluewhaleyt.materialfileicon.core;

import android.widget.ImageView;

import com.bluewhaleyt.filemanagement.FileUtil;
import com.bluewhaleyt.filemanagement.SAFUtil;
import com.bluewhaleyt.materialfileicon.R;
import com.bluewhaleyt.materialfileicon.core.detector.FileEnvironmentHelper;

public class FileIconHelper {

    private String filePath;
    private String mimeType;

    private int fileIconRes;

    private FileEnvironmentHelper fileEnvHelper;

    public FileIconHelper(String filePath) {
        this.filePath = filePath;
        this.mimeType = "";
        check();
    }

    public FileIconHelper(String filePath, String mimeType) {
        this.filePath = filePath;
        this.mimeType = mimeType;
        check();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getFileIcon() {
        return fileIconRes;
    }

    public void bindIcon(ImageView imageView) {
        imageView.setImageResource(fileIconRes);
    }

    private void check() {
        fileEnvHelper = new FileEnvironmentHelper(filePath);

        if (mimeType == null) mimeType = "";

        if (FileUtil.isDirectory(filePath) || SAFUtil.isDirectory(mimeType)) {
            var fileName = FileUtil.getFileNameOfPath(filePath);
            if (filePath.equals("")) {
                fileIconRes = R.drawable.ic_material_folder;
            }

            else if (fileEnvHelper.isNodeJsDirectory()) {
                fileIconRes = R.drawable.ic_material_folder_node;
            }

            else {
                if (FileUtil.isFileHidden(fileName)) {
                    fileIconRes = R.drawable.ic_material_folder_hidden;
                } else {
                    fileIconRes = R.drawable.ic_material_folder;
                }
            }
        } else {
            apply();
        }
    }

    private void apply() {
        var str = "";
        if (!filePath.equals("")) str = FileUtil.getFileExtensionOfPath(filePath.toLowerCase());

        switch (str) {
            // compress files
            case "7z":
            case "rar":
            case "tar":
            case "tar.xz":
            case "zip":
                fileIconRes = R.drawable.ic_material_zip;
                break;

            // image files
            case "bmp":
            case "jpg":
            case "jpeg":
            case "png":
            case "tiff":
            case "webp":
                fileIconRes = R.drawable.ic_material_image;
                break;
            case "ai":
            case "swf":
            case "svg":
                fileIconRes = R.drawable.ic_material_svg;
                break;

            // font files
            case "otf":
            case "ttc":
            case "ttf":
                fileIconRes = R.drawable.ic_material_font;
                break;

            // text files
            case "as":
                fileIconRes = R.drawable.ic_material_actionscript;
                break;
            case "bat":
                fileIconRes = R.drawable.ic_material_console;
                break;
            case "c":
                fileIconRes = R.drawable.ic_material_c;
                break;
            case "class":
                fileIconRes = R.drawable.ic_material_javaclass;
                break;
            case "cpp":
                fileIconRes = R.drawable.ic_material_cpp;
                break;
            case "csharp":
                fileIconRes = R.drawable.ic_material_csharp;
                break;
            case "css":
                fileIconRes = R.drawable.ic_material_css;
                break;
            case "dart":
                fileIconRes = R.drawable.ic_material_dart;
                break;
            case "go":
                fileIconRes = R.drawable.ic_material_go;
                break;
            case "gradle":
            case "gradle.kts":
                fileIconRes = R.drawable.ic_material_gradle;
                break;
            case "groovy":
            case "gvy":
            case "gy":
            case "gsh":
                fileIconRes = R.drawable.ic_material_groovy;
                break;
            case "htm":
            case "html":
                fileIconRes = R.drawable.ic_material_html;
                break;
            case "java":
                fileIconRes = R.drawable.ic_material_java;
                break;
            case "js":
                fileIconRes = fileEnvHelper.isNodeJsFile() ? R.drawable.ic_material_nodejs : R.drawable.ic_material_javascript;
                break;
            case "json":
                fileIconRes = fileEnvHelper.isNodeJsPackageJsonFile() ? R.drawable.ic_material_nodejs : R.drawable.ic_material_json;
                break;
            case "kt":
                fileIconRes = R.drawable.ic_material_kotlin;
                break;
            case "less":
                fileIconRes = R.drawable.ic_material_less;
                break;
            case "log":
                fileIconRes = R.drawable.ic_material_log;
                break;
            case "lua":
                fileIconRes = R.drawable.ic_material_lua;
                break;
            case "md":
                fileIconRes = R.drawable.ic_material_markdown;
                break;
            case "mdx":
                fileIconRes = R.drawable.ic_material_mdx;
                break;
            case "pdf":
                fileIconRes = R.drawable.ic_material_pdf;
                break;
            case "php":
                fileIconRes = R.drawable.ic_material_php;
                break;
            case "pug":
                fileIconRes = R.drawable.ic_material_pug;
                break;
            case "py":
                fileIconRes = R.drawable.ic_material_python;
                break;
            case "sass":
            case "scss":
                fileIconRes = R.drawable.ic_material_sass;
                break;
            case "sql":
                fileIconRes = R.drawable.ic_material_database;
                break;
            case "stylus":
                fileIconRes = R.drawable.ic_material_stylus;
                break;
            case "swift":
                fileIconRes = R.drawable.ic_material_swift;
                break;
            case "ts":
                fileIconRes = R.drawable.ic_material_typescript;
                break;
            case "xml":
            case "xsl":
                fileIconRes = R.drawable.ic_material_xml;
                break;
            case "yaml":
            case "yml":
                fileIconRes = R.drawable.ic_material_yaml;
                break;

            // other files
            case "apk":
                fileIconRes = R.drawable.ic_material_android;
                break;

            default:
                fileIconRes = R.drawable.ic_material_document;
        }
    }

}