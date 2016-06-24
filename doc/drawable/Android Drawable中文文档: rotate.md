使用rotate标签可以对一个drawable进行旋转操作

android:drawable 指定drawable资源，如果不设置该属性，也可以定义drawable类型的子标签
android:fromDegrees 起始的角度度数
android:toDegrees 结束的角度度数，正数表示顺时针，负数表示逆时针
android:pivotX 旋转中心的X坐标，浮点数或是百分比。浮点数表示相对于drawable的左边缘距离单位为px，如5; 百分比表示相对于drawable的左边缘距离按百分比计算，如5%; 另一种百分比表示相对于父容器的左边缘，如5%p; 一般设置为50%表示在drawable中心
android:pivotY 旋转中心的Y坐标
android:visible 设置初始的可见性状态，默认为false

```
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/ic_arrow"
    android:fromDegrees="0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:toDegrees="180" />
```