# MaterialFileIcon 質感檔案圖示

MaterialFileIcon is a simple, but an efficient library providing a way that equipping the material file icons to your desired image view or whatever you want it binds to.
It's useful when you are developing a file manager or an IDE.

## Features

- 50+ languages supported
- environment languages supported (not fully stable)
  - NodeJS
  - AngularJS
  - React

## Get Started

coming soon

## How to use?

It's simple, just by adding a few codes.

```java
FileIconHelper fileIconHelper = new FileIconHelper(filePath);
fileIconHelper.bindIcon(imageView);
```

or you can simply get the file icon.

```java
imageView.setImageResource(fileIconHelper.getFileIcon());
```

## Thanks to

- [Pkief](https://github.com/PKief/vscode-material-icon-theme) - Material Icon Theme