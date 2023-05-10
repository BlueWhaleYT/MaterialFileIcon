package com.bluewhaleyt.materialfileicon.core.environment;

import java.io.File;

public class FileEnvironmentHelper {

    private String filePath;

    public FileEnvironmentHelper(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public ReadmeHelper readme() {
        return new ReadmeHelper(this);
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

    public boolean isNpmPackageJson() {
        return nodejs().isNodeJsPackageJsonFile() |
                angularjs().isAngularJsPackageJsonFile() |
                react().isReactPackageJsonFile();
    }

    public boolean isLicenseFile() {
        return isFileHasKeyRegex(filePath, "(?i)license(\\.(txt|md))?");
    }

    public boolean isSrcDirectory() {
        return isFileHasKeyName(filePath, "src", false, true);
    }

    public boolean isPublicDirectory() {
        return isFileHasKeyName(filePath, "public", false, true);
    }

    public boolean isAppDirectory() {
        return isFileHasKeyName(filePath, "app", false, true);
    }

    public boolean isDownloadDirectory() {
        return isFileHasKeyName(filePath, "Download", false, false);
    }

    public boolean isDCIMDirectory() {
        return isFileHasKeyName(filePath, "DCIM", false, false);
    }

    public boolean isMoviesDirectory() {
        return isFileHasKeyName(filePath, "Movies", false, false);
    }

    public boolean isMusicDirectory() {
        return isFileHasKeyName(filePath, "Music", false, false);
    }

    public boolean isNotificationsDirectory() {
        return isFileHasKeyName(filePath, "Notifications", false, false);
    }

    public boolean isPicturesDirectory() {
        return isFileHasKeyName(filePath, "Pictures", false, false);
    }

    public boolean isRingtonesDirectory() {
        return isFileHasKeyName(filePath, "Ringtones", false, false);
    }

    public boolean isPodcastsDirectory() {
        return isFileHasKeyName(filePath, "Podcasts", false, false);
    }

    public boolean isAlarmsDirectory() {
        return isFileHasKeyName(filePath, "Alarms", false, false);
    }

    public boolean isJavaDirectory() {
        return isFileHasKeyName(filePath, "java", false, true);
    }

    public boolean isIntelliJDirectory() {
        return isFileHasKeyName(filePath, ".idea", false, false);
    }

    public boolean isGradleJDirectory() {
        return isFileHasKeyName(filePath, "gradle", false, false);
    }

    public boolean isFileHasKeyRegex(String filePath, String regex) {
        File file = new File(filePath);
        if (file.isDirectory()) return false;
        String fileName = file.getName();
        return fileName.matches(regex);
    }

    public boolean isFileHasKeyName(String filePath, String name, boolean isDir, boolean isIgnoreCase) {
        File file = new File(filePath);
        if (isDir) if (file.isDirectory()) return false;
        String fileName = file.getName();
        return isIgnoreCase
                ? fileName.equalsIgnoreCase(name)
                : fileName.equals(name);
    }

    public static class ReadmeHelper {
        private FileEnvironmentHelper instance;

        public ReadmeHelper(FileEnvironmentHelper instance) {
            this.instance = instance;
        }

        public boolean isReadmeFile() {
            return ReadmeDetector.isReadmeFile(instance.filePath);
        }
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

        public boolean isGitIgnoreFile() {
            return GitDetector.isGitIgnoreFile(instance.filePath);
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