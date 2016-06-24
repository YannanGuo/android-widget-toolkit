使用scale标签可以对drawable进行缩放操作，和clip一样是通过设置level来控制缩放的比例。

android:drawable 指定drawable资源，如果不设置该属性，也可以定义drawable类型的子标签
android:scaleHeight 设置可缩放的高度，用百分比表示，格式为XX%，0%表示不做任何缩放，50%表示只能缩放一半
android:scaleWidth 设置可缩放的宽度，用百分比表示，格式为XX%，0%表示不做任何缩放，50%表示只能缩放一半
android:scaleGravity 设置drawable缩放后的位置，取值和bitmap标签的一样，就不一一列举说明了，不过默认值是left
android:useIntrinsicSizeAsMinimum 设置drawable原有尺寸作为最小尺寸，设为true时，缩放基本无效，API Level最低要求为11

```
<?xml version="1.0" encoding="utf-8"?>
<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/img4scale"
    android:scaleGravity="left"
    android:scaleHeight="50%"
    android:scaleWidth="50%"
    android:useIntrinsicSizeAsMinimum="false" />
```


```
<ImageView
    android:id="@+id/img"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@drawable/bg_img"
    android:src="@drawable/scale" />
```

```
ImageView img =  (ImageView) findViewById(R.id.img);
img.getDrawable().setLevel(5000); //level范围值0~10000 
```