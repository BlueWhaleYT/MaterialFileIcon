package com.bluewhaleyt.materialfileicon.core.environment;

public class FileEnvironmentHelper {

    private String filePath;

    public FileEnvironmentHelper(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public GitHelper git() {
        return new GitHelper(this);
    }

    public NodejsHelper nodejs() {
        return new NodejsHelper(this);
    }

    public AngularJsHelper angularjs() {
        return new AngularJsHelper(this);
    }

    public ReactHelper react() {
        return new ReactHelper(this);
    }

    public AndroidDevHelper android() {
        return new AndroidDevHelper(this);
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

    public static class ReactHelper {
        private FileEnvironmentHelper instance;

        public ReactHelper(FileEnvironmentHelper instance) {
            this.instance = instance;
        }

        public boolean isReactDirectory() {
            return ReactDetector.isReactDirectory(instance.filePath);
        }

        public boolean isReactFile() {
            return ReactDetector.isReactFile(instance.filePath);
        }

        public boolean isReactPackageJsonFile() {
            return ReactDetector.isReactPackageJsonFile(instance.filePath);
        }
    }

    public static class GitHelper {
        private FileEnvironmentHelper instance;

        public GitHelper(FileEnvironmentHelper instance) {
            this.instance = instance;
        }

        public boolean isGitDirectory() {
            return GitDetector.isGitDirectory(instance.filePath);
        }

    }

    public static class AndroidDevHelper {
        private FileEnvironmentHelper instance;

        public AndroidDevHelper(FileEnvironmentHelper instance) {
            this.instance = instance;
        }

        public boolean isAndroidDevDirectory() {
            return AndroidDevDetector.isAndroidDevDirectory(instance.filePath);
        }

    }

}