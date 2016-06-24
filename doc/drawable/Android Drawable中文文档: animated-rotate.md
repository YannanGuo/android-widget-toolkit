rotate标签只是将原有的drawable转个角度变成另一个drawable，它是静态的。而animated-rotate则会让drawable不停地做旋转动画。

android:drawable 指定drawable资源，如果不设置该属性，也可以定义drawable类型的子标签
android:pivotX 旋转中心的X坐标
android:pivotY 旋转中心的Y坐标
android:visible 设置初始的可见性状态，默认为false

```
<?xml version="1.0" encoding="utf-8"?>
<animated-rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/img_daisy"
    android:pivotX="50%"
    android:pivotY="50%"
    android:visible="false" />
```