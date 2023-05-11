# MaterialFileIcon 質感檔案圖示

MaterialFileIcon is a simple, but an efficient library providing a way that equipping the material file icons to your desired image view or whatever you want it binds to.
It's useful when you are developing a file manager or an IDE.

## Features

- 50+ languages supported
- environment languages supported (not fully stable)
  - Nodejs, React, Vue, etc...

## Get Started

coming soon

## How to use?

It's simple, just by adding a few codes.
> Note: The icon to be displayed, depends on the extension name of the file path.

For example, if you file path is:
```
/{EXTERNAL_STORAGE}/MyFolder/Main.java
```

It will display Java icon.

```java
FileIconHelper fileIconHelper = new FileIconHelper(filePath);
fileIconHelper.bindIcon(imageView);
```

or you can simply get the file icon and use it not just to ImageView.

```java
imageView.setImageResource(fileIconHelper.getFileIcon());
```

## Thanks to

- [Pkief](https://github.com/PKief/vscode-material-icon-theme) - Material Icon Theme