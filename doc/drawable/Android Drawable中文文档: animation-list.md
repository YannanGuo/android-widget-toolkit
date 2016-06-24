通过animation-list可以将一系列drawable构建成帧动画，就是将一个个drawable，一帧一帧的播放。通过添加item子标签设置每一帧使用的drawable资源，以及每一帧持续的时间。

android:oneshot属性设置是否循环播放，设为true时，只播放一轮就结束，设为false时，则会轮询播放。
android:duration属性设置该帧持续的时间，以毫秒数为单位。

```
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false">
    <item
        android:drawable="@drawable/anim1"
        android:duration="1000" />
    <item
        android:drawable="@mipmap/anim2"
        android:duration="1000" />
    <item
        android:drawable="@mipmap/anim3"
        android:duration="1000" />
</animation-list>
```