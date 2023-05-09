package com.bluewhaleyt.materialfileicon.core.environment;

import java.io.File;

public class GitDetector {

    public static boolean isGitDirectory(String dirPath) {
        File file = new File(dirPath);
        if (!file.isDirectory()) {
            return false;
        }
        File gitDir = new File(file, ".git");
        return gitDir.exists() && gitDir.isDirectory();
    }

    public static boolean isGitFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        File parentDir = new File(file.getParent());
        return isGitDirectory(parentDir.getAbsolutePath());
    }
}