
layer-list可以将多个drawable按照顺序层叠在一起显示, layer-list可以作为根节点，也可以作为selector中item的子节点。layer-list可以添加多个item
子节点，每个item子节点对应一个drawable资源，按照item从上到下的顺序叠加在一起，

layer-list的item可以通过下面四个属性设置偏移量：

android:top 顶部的偏移量
android:bottom 底部的偏移量
android:left 左边的偏移量
android:right 右边的偏移量

**注意**
1. 根节点不同时，可设置的属性是会不同的，比如selector下，可以设置一些状态属性，而在layer-list下，可以设置偏移量；
2. 就算父节点同样是selector，放在drawable目录和放在color目录下可用的属性也会不同，比如drawable目录下可用的属性为android:drawable，在color目录
下可用的属性为android:color；
3. item的子节点可以为任何类型的drawable类标签，除了上面例子中的shape、color、layer-list，也可以是selector，还有其他没讲过的bitmap、clip、scale、
inset、transition、rotate、animated-rotate、lever-list等等。

Tab背景

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 第一种加载方式 -->
    <!--<item android:drawable="@drawable/bg_tab_selected" android:state_checked="true" />-->
    <!-- 第二种加载方式 -->
    <item android:state_checked="true">
        <layer-list>
            <!-- 红色背景 -->
            <item>
                <color android:color="#E4007F" />
            </item>
            <!-- 白色背景 -->
            <item android:bottom="4dp" android:drawable="@android:color/white" />
        </layer-list>
    </item>
    <item>
        <layer-list>
            <!-- 红色背景 -->
            <item>
                <color android:color="#E4007F" />
            </item>
            <!-- 白色背景 -->
            <item android:bottom="1dp" android:drawable="@android:color/white" />
        </layer-list>
    </item>
</selector>
```

带阴影的圆角矩形

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 灰色阴影 -->
    <item
        android:left="2dp"
        android:top="4dp">
        <shape>
            <solid android:color="@android:color/darker_gray" />
            <corners android:radius="10dp" />
        </shape>
    </item>
    <!-- 白色前景 -->
    <item
        android:bottom="4dp"
        android:right="2dp">
        <shape>
            <solid android:color="#FFFFFF" />
            <corners android:radius="10dp" />
        </shape>
    </item>
</layer-list>
```