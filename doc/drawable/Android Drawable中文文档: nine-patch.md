
使用nine-patch标签可以对点九图片做一些设置处理


adroid:src 必填项，必须指定点九类型的图片
android:dither 设置是否抖动，图片与屏幕的像素配置不同时会用到，比如图片是ARGB 8888的，而屏幕是RGB565
android:tint 给图片着色，比如图片本来是黑色的，着色后可以变成白色
android:tintMode 着色模式，API Level 21(Android 5.0)才添加的属性
android:alpha 设置图片的透明度，取值范围为0.0~1.0之间，0.0为全透明，1.0为全不透明，API Level最低要求是11
android:autoMirrored 设置图片是否需要镜像反转，当布局方向是RTL，即从右到左布局时才有用，API Level 19(Android 4.4)才添加的属性