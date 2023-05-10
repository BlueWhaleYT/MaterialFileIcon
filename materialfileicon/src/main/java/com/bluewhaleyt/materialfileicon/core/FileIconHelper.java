package com.bluewhaleyt.materialfileicon.core;

import android.widget.ImageView;

import com.bluewhaleyt.filemanagement.FileUtil;
import com.bluewhaleyt.filemanagement.SAFUtil;
import com.bluewhaleyt.materialfileicon.R;
import com.bluewhaleyt.materialfileicon.core.environment.FileEnvironmentHelper;

import org.apache.commons.io.FileUtils;

public class FileIconHelper {

    private String filePath;
    private String mimeType;

    private int fileIconRes;

    private FileHelper fileHelper;
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
        fileHelper = new FileHelper(filePath);
        fileEnvHelper = new FileEnvironmentHelper(filePath);

        if (mimeType == null) mimeType = "";

        if (FileUtil.isDirectory(filePath) || SAFUtil.isDirectory(mimeType)) {
            var fileName = FileUtil.getFileNameOfPath(filePath);
            if (filePath.equals("")) fileIconRes = R.drawable.ic_material_folder;
            if (fileEnvHelper.nodejs().isNodeJsDirectory()) fileIconRes = R.drawable.ic_material_folder_node;
            else if (fileEnvHelper.angularjs().isAngularJsDirectory()) fileIconRes = R.drawable.ic_material_folder_angular;
            else if (fileEnvHelper.react().isReactDirectory()) fileIconRes = R.drawable.ic_material_folder_react_component;

            else if (fileEnvHelper.android().isAndroidDevDirectory()) fileIconRes = R.drawable.ic_material_folder_android;

            else if (fileEnvHelper.git().isGitDirectory()) fileIconRes = R.drawable.ic_material_folder_git;

            else if (fileEnvHelper.isJavaDirectory()) fileIconRes = R.drawable.ic_material_folder_java;

            else if (fileEnvHelper.isDownloadDirectory()) fileIconRes = R.drawable.ic_material_folder_download;
            else if (fileEnvHelper.isDCIMDirectory() || fileEnvHelper.isPicturesDirectory()) fileIconRes = R.drawable.ic_material_folder_images;
            else if (fileEnvHelper.isMusicDirectory() || fileEnvHelper.isNotificationsDirectory()) fileIconRes = R.drawable.ic_material_folder_audio;
            else if (fileEnvHelper.isMoviesDirectory()) fileIconRes = R.drawable.ic_material_folder_video;

            else if (fileEnvHelper.isSrcDirectory()) fileIconRes = R.drawable.ic_material_folder_src;
            else if (fileEnvHelper.isPublicDirectory()) fileIconRes = R.drawable.ic_material_folder_public;
            else if (fileEnvHelper.isAppDirectory()) fileIconRes = R.drawable.ic_material_folder_app;

            else {
                if (FileUtil.isFileHidden(fileName)) {
                    fileIconRes = R.drawable.ic_material_folder_secure;
                } else {
                    fileIconRes = R.drawable.ic_material_folder;
                }
            }
        } else {
            apply();
        }
    }

    private void apply() {
        fileIconRes = R.drawable.ic_material_document;

        if (fileHelper.isCompressFiles()) fileIconRes = R.drawable.ic_material_zip;
        else if (fileHelper.isBitmapFiles()) fileIconRes = R.drawable.ic_material_image;
        else if (fileHelper.isVectorFiles()) fileIconRes = R.drawable.ic_material_svg;
        else if (fileHelper.isVideoFiles()) fileIconRes = R.drawable.ic_material_video;
        else if (fileHelper.isAudioFiles()) fileIconRes = R.drawable.ic_material_audio;
        else if (fileHelper.isFontFiles()) fileIconRes = R.drawable.ic_material_font;
        else if (fileHelper.isMicrosoftWordFiles()) fileIconRes = R.drawable.ic_material_word;
        else if (fileHelper.isGradleFiles()) fileIconRes = R.drawable.ic_material_gradle;
        else if (fileHelper.isTestJsFiles()) fileIconRes = R.drawable.ic_material_test_js;
        else if (fileHelper.isMinecraftRelatedFiles()) fileIconRes = R.drawable.ic_material_minecraft;

        else if (fileHelper.equals("apk")) fileIconRes = R.drawable.ic_material_android;
        else if (fileHelper.equals("pdf")) fileIconRes = R.drawable.ic_material_pdf;
        else if (fileHelper.equals("ppt")) fileIconRes = R.drawable.ic_material_powerpoint;

        else if (fileHelper.equals("as")) fileIconRes = R.drawable.ic_material_actionscript;

        else if (fileHelper.equals("bat")) fileIconRes = R.drawable.ic_material_console;

        else if (fileHelper.equals("c")) fileIconRes = R.drawable.ic_material_c;
        else if (fileHelper.equals("cpp")) fileIconRes = R.drawable.ic_material_cpp;
        else if (fileHelper.equals("csharp")) fileIconRes = R.drawable.ic_material_csharp;
        else if (fileHelper.equals("class")) fileIconRes = R.drawable.ic_material_javaclass;
        else if (fileHelper.equals("css")) fileIconRes = R.drawable.ic_material_css;

        else if (fileHelper.equals("dart")) fileIconRes = R.drawable.ic_material_dart;

        else if (fileHelper.equals("go")) fileIconRes = R.drawable.ic_material_go;
        else if (fileHelper.equals("groovy") || fileHelper.equals("gvy") || fileHelper.equals("gy") || fileHelper.equals("gsh")) fileIconRes = R.drawable.ic_material_groovy;

        else if (fileHelper.equals("htm") || fileHelper.equals("html")) {
            if (fileEnvHelper.angularjs().isAngularJsFile()) fileIconRes = R.drawable.ic_material_angular;
            else fileIconRes = R.drawable.ic_material_html;
        }

        else if (fileHelper.equals("java")) fileIconRes = R.drawable.ic_material_java;
        else if (fileHelper.equals("js")) {
            if (fileEnvHelper.nodejs().isNodeJsFile()) fileIconRes = R.drawable.ic_material_nodejs;
            else if (fileEnvHelper.react().isReactFile()) fileIconRes = R.drawable.ic_material_react;
            else fileIconRes = R.drawable.ic_material_javascript;
        }
        else if (fileHelper.equals("json")) {
            if (fileEnvHelper.isNpmPackageJson()) fileIconRes = R.drawable.ic_material_npm;
            else if (fileEnvHelper.react().isReactFile()) fileIconRes = R.drawable.ic_material_react;
            else fileIconRes = R.drawable.ic_material_json;
        }

        else if (fileHelper.equals("kt")) fileIconRes = R.drawable.ic_material_kotlin;

        else if (fileHelper.equals("less")) fileIconRes = R.drawable.ic_material_less;
        else if (fileHelper.equals("log")) fileIconRes = R.drawable.ic_material_log;
        else if (fileHelper.equals("lua")) fileIconRes = R.drawable.ic_material_lua;

        else if (fileHelper.equals("md")) fileIconRes = R.drawable.ic_material_markdown;
        else if (fileHelper.equals("mdx")) fileIconRes = R.drawable.ic_material_mdx;

        else if (fileHelper.equals("pas")) fileIconRes = R.drawable.ic_material_pascal;
        else if (fileHelper.equals("php")) fileIconRes = R.drawable.ic_material_php;
        else if (fileHelper.equals("py")) fileIconRes = R.drawable.ic_material_python;
        else if (fileHelper.equals("pug")) fileIconRes = R.drawable.ic_material_pug;

        else if (fileHelper.equals("sass") || fileHelper.equals("scss")) fileIconRes = R.drawable.ic_material_sass;
        else if (fileHelper.equals("sql")) fileIconRes = R.drawable.ic_material_database;
        else if (fileHelper.equals("stylus")) fileIconRes = R.drawable.ic_material_stylus;
        else if (fileHelper.equals("swift")) fileIconRes = R.drawable.ic_material_swift;

        else if (fileHelper.equals("ts")) {
            if (fileEnvHelper.react().isReactFile()) fileIconRes = R.drawable.ic_material_react_ts;
            else fileIconRes = R.drawable.ic_material_typescript;
        }

        else if (fileHelper.equals("xml") || fileHelper.equals("xsl")) fileIconRes = R.drawable.ic_material_xml;
        else if (fileHelper.equals("yml") || fileHelper.equals("yaml")) fileIconRes = R.drawable.ic_material_yaml;

        if (fileEnvHelper.readme().isReadmeFile()) fileIconRes = R.drawable.ic_material_readme;
        else if (fileEnvHelper.git().isGitIgnoreFile()) fileIconRes = R.drawable.ic_material_git;
        else if (fileEnvHelper.isLicenseFile()) fileIconRes = R.drawable.ic_material_certificate;
        else if (FileUtil.isFileHidden(filePath)) fileIconRes = R.drawable.ic_material_lock;

    }

}