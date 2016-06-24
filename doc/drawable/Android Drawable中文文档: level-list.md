当需要在一个View中显示不同图片的时候，比如手机剩余电量不同时显示的图片不同，level-list就可以派上用场了。level-list可以管理一组drawable，每个drawable设置一组level范围，最终会根据level值选取对应的drawable绘制出来。

android:drawable 指定drawable资源，如果不设置该属性，也可以定义drawable类型的子标签
android:minLevel 该item的最小level值
android:maxLevel 该item的最大level值

```
<?xml version="1.0" encoding="utf-8"?>
<level-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:drawable="@drawable/battery_low"
        android:maxLevel="10"
        android:minLevel="0" />
    <item
        android:drawable="@drawable/battery_below_half"
        android:maxLevel="50"
        android:minLevel="10" />
    <item
        android:drawable="@drawable/battery_over_half"
        android:maxLevel="99"
        android:minLevel="50" />
    <item
        android:drawable="@drawable/battery_full"
        android:maxLevel="100"
        android:minLevel="100" />
</level-list>
```


```
img.getDrawable().setLevel(10);
```