package com.bluewhaleyt.materialfileicon.core;

import android.widget.ImageView;

import com.bluewhaleyt.filemanagement.FileUtil;
import com.bluewhaleyt.filemanagement.SAFUtil;
import com.bluewhaleyt.materialfileicon.R;
import com.bluewhaleyt.materialfileicon.core.environment.FileEnvironmentHelper;

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

            if (fileEnvHelper.angularjs().isAngularJsDirectory()) fileIconRes = R.drawable.ic_material_folder_angular;
            else if (fileEnvHelper.vuejs().isVueJsDirectory()) fileIconRes = R.drawable.ic_material_folder_vue;
            else if (fileEnvHelper.nodejs().isNodeJsDirectory()) fileIconRes = R.drawable.ic_material_folder_node;
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

            else if (fileEnvHelper.isIntelliJDirectory()) fileIconRes = R.drawable.ic_material_folder_intellij;
            else if (fileEnvHelper.isGradleJDirectory()) fileIconRes = R.drawable.ic_material_folder_gradle;

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
        else if (fileHelper.isYarnFiles()) fileIconRes = R.drawable.ic_material_yarn;
        else if (fileHelper.isTestJsFiles()) fileIconRes = R.drawable.ic_material_test_js;
        else if (fileHelper.isMinecraftRelatedFiles()) fileIconRes = R.drawable.ic_material_minecraft;

        else if (is("apk")) fileIconRes = R.drawable.ic_material_android;
        else if (is("pdf")) fileIconRes = R.drawable.ic_material_pdf;
        else if (is("ppt")) fileIconRes = R.drawable.ic_material_powerpoint;

        else if (is("as")) fileIconRes = R.drawable.ic_material_actionscript;

        else if (is("bat")) fileIconRes = R.drawable.ic_material_console;

        else if (is("c")) fileIconRes = R.drawable.ic_material_c;
        else if (is("cpp")) fileIconRes = R.drawable.ic_material_cpp;
        else if (is("csharp")) fileIconRes = R.drawable.ic_material_csharp;
        else if (is("class")) fileIconRes = R.drawable.ic_material_javaclass;
        else if (is("css")) fileIconRes = R.drawable.ic_material_css;

        else if (is("dart")) fileIconRes = R.drawable.ic_material_dart;
        else if (is("diff")) fileIconRes = R.drawable.ic_material_diff;

        else if (is("go")) fileIconRes = R.drawable.ic_material_go;
        else if (is("groovy") || is("gvy") || is("gy") || is("gsh")) fileIconRes = R.drawable.ic_material_groovy;

        else if (is("htm") || is("html")) {
            if (fileEnvHelper.angularjs().isAngularJsFile()) fileIconRes = R.drawable.ic_material_angular;
            else fileIconRes = R.drawable.ic_material_html;
        }

        else if (is("jar")) fileIconRes = R.drawable.ic_material_jar;
        else if (is("java")) fileIconRes = R.drawable.ic_material_java;
        else if (is("js")) {
            if (fileEnvHelper.nodejs().isNodeJsFile()) fileIconRes = R.drawable.ic_material_nodejs;
            else if (fileEnvHelper.react().isReactFile()) fileIconRes = R.drawable.ic_material_react;
            else fileIconRes = R.drawable.ic_material_javascript;
        }
        else if (is("json")) {
            if (fileEnvHelper.isNpmPackageJson()) fileIconRes = R.drawable.ic_material_npm;
            else if (fileEnvHelper.react().isReactFile()) fileIconRes = R.drawable.ic_material_react;
            else fileIconRes = R.drawable.ic_material_json;
        }

        else if (is("kt")) fileIconRes = R.drawable.ic_material_kotlin;

        else if (is("less")) fileIconRes = R.drawable.ic_material_less;
        else if (is("log")) fileIconRes = R.drawable.ic_material_log;
        else if (is("lua")) fileIconRes = R.drawable.ic_material_lua;

        else if (is("md")) fileIconRes = R.drawable.ic_material_markdown;
        else if (is("mdx")) fileIconRes = R.drawable.ic_material_mdx;

        else if (is("pas")) fileIconRes = R.drawable.ic_material_pascal;
        else if (is("php")) fileIconRes = R.drawable.ic_material_php;
        else if (is("py")) fileIconRes = R.drawable.ic_material_python;
        else if (is("pug")) fileIconRes = R.drawable.ic_material_pug;
        else if (is("properties")) fileIconRes = R.drawable.ic_material_settings;

        else if (is("sass") || is("scss")) fileIconRes = R.drawable.ic_material_sass;
        else if (is("sql")) fileIconRes = R.drawable.ic_material_database;
        else if (is("stylus")) fileIconRes = R.drawable.ic_material_stylus;
        else if (is("swift")) fileIconRes = R.drawable.ic_material_swift;

        else if (is("ts")) {
            if (fileEnvHelper.react().isReactFile()) fileIconRes = R.drawable.ic_material_react_ts;
            else fileIconRes = R.drawable.ic_material_typescript;
        }

        else if (is("vue")) fileIconRes = R.drawable.ic_material_vue;

        else if (is("xml") || is("xsl")) fileIconRes = R.drawable.ic_material_xml;

        else if (is("yml") || is("yaml")) fileIconRes = R.drawable.ic_material_yaml;

        if (fileEnvHelper.readme().isReadmeFile()) fileIconRes = R.drawable.ic_material_readme;
        else if (fileEnvHelper.git().isGitIgnoreFile()) fileIconRes = R.drawable.ic_material_git;
        else if (fileEnvHelper.isLicenseFile()) fileIconRes = R.drawable.ic_material_certificate;
        else if (FileUtil.isFileHidden(filePath)) fileIconRes = R.drawable.ic_material_lock;

    }
    
    private boolean is(String str) {
        return fileHelper.equals(str);
    }

}