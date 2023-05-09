package com.bluewhaleyt.materialfileicon.core.detector;

public class FileEnvironmentHelper {

    private String filePath;

    public FileEnvironmentHelper(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public NodejsHelper nodejs() {
        return new NodejsHelper(this);
    }

    public AngularJsHelper angularjs() {
        return new AngularJsHelper(this);
    }

    public static class NodejsHelper {
        private FileEnvironmentHelper instance;

        public NodejsHelper(FileEnvironmentHelper instance) {
            this.instance = instance;
        }

        public boolean isNodeJsDirectory() {
            return NodejsDetector.isNodeJsDirectory(instance.filePath);
        }

        public boolean isNodeJsFile() {
            return NodejsDetector.isNodeJsFile(instance.filePath);
        }

        public boolean isNodeJsPackageJsonFile() {
            return NodejsDetector.isNodeJsPackageJsonFile(instance.filePath);
        }
    }

    public static class AngularJsHelper {
        private FileEnvironmentHelper instance;

        public AngularJsHelper(FileEnvironmentHelper instance) {
            this.instance = instance;
        }

        public boolean isAngularJsDirectory() {
            return AngularJsDetector.isAngularJSDirectory(instance.filePath);
        }

        public boolean isAngularJsFile() {
            return AngularJsDetector.isAngularJSFile(instance.filePath);
        }

        public boolean isAngularJsPackageJsonFile() {
            return AngularJsDetector.isAngularJSPackageJsonFile(instance.filePath);
        }
    }

}