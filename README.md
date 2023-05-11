# MaterialFileIcon

MaterialFileIcon is a simple, but an efficient library providing a way that equipping the material file icons to your desired image view or whatever you want it binds to.
It's useful when you are developing a file manager or an IDE.

## Features

- 50+ languages supported
- environment languages supported (not fully stable)
  - Nodejs, React, Vue, etc...

## Get Started

You are required to follow the instruction if you want to use this library in your project.

### Add JitPack Repository

Add the JitPack repository to your root `build.gradle` or `build.gradle (Project: xxx)` inside Gradle Scripts.

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

However, if you’re using Android Studio Bumblebee, the method describes above is not working in this version. Instead, you need to add JitPack repository in your `settings.gradle`.

```gradle
pluginManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Add Dependency

Add the dependency in your app `build.gradle` or `build.gradle (Module: xxx.app)` inside Gradle Scripts.

The `$version` can be found in Releases.-

```gradle
dependencies {
    implementation 'com.github.BlueWhaleYT:MaterialFileIcon:$version'
}
```

## How to use?

It's simple, just by adding a few codes.

> **Note**
> The icon to be displayed, depends on the extension name of the file path.

```java
FileIconHelper fileIconHelper = new FileIconHelper(filePath);
fileIconHelper.bindIcon(imageView);
```

or you can simply get the file icon and use it not just to ImageView.

```java
imageView.setImageResource(fileIconHelper.getFileIcon());
```

### General icons

For example, if you file path is:
```
/{EXTERNAL_STORAGE}/MyFolder/main.py
```

It will display Python icon.

### Environment icons

Environment icons means the icon of environment languages namely React, Vue, etc..

> **Warning**
> Environment icons require valid file path.

```java
fileIconHelper.setEnvironmentEnabled(true);
```

### Dynamic folder icons

If you want to get rid of the plain, which only has blue and yellow folder appearance. You can enable this for more visual icons.

```java
fileIconHelper.setDynamicFolderEnabled(true);
```

This option also supports environment icons.

## Thanks to

- [Pkief](https://github.com/PKief/vscode-material-icon-theme) - Material Icon Theme