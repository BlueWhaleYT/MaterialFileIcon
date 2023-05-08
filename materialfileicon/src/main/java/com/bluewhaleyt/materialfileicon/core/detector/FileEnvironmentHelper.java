package com.bluewhaleyt.materialfileicon.core.detector;

public class FileEnvironmentHelper {

    private String filePath;

    public FileEnvironmentHelper(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isNodeJsDirectory() {
        return NodejsDetector.isNodeJsDirectory(filePath);
    }

    public boolean isNodeJsFile() {
        return NodejsDetector.isNodeJsFile(filePath);
    }

    public boolean isNodeJsPackageJsonFile() {
        return NodejsDetector.isNodeJsPackageJsonFile(filePath);
    }

}
