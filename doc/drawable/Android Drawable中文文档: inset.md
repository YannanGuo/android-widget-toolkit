使用inset标签可以对drawable设置边距，其用法和View的padding类似，只不过padding是设置内容与边界的距离，而inset则可以设置背景drawable与View边界的距离。

android:drawable 指定drawable资源，如果不设置该属性，也可以定义drawable类型的子标签
android:visible 设置初始的可见性状态，默认为false
android:insetLeft 左边距
android:insetRight 右边距
android:insetTop 顶部边距
android:insetBottom 底部边距
android:inset 设置统一边距，会覆盖上面四个属性，但API Level要求为21，即Android 5.0