
通过bitmap标签对图片做一些设置，如平铺、拉伸或保持图片原始大小，也可以指定对齐方式。


android:src 必填项，指定图片资源，只能是图片，不能是xml定义的drawable资源
android:gravity 设置图片的对齐方式，比如在layer-list中，默认会尽量填满整个视图，导致图片可能会被拉伸，为了避免被拉伸，就可以设置对齐方式，可取值为下面的值，多个取值可以用 | 分隔：

top 图片放于容器顶部，不改变图片大小
bottom 图片放于容器底部，不改变图片大小
left 图片放于容器左边，不改变图片大小
right 图片放于容器右边，不改变图片大小
center 图片放于容器中心位置，包括水平和垂直方向，不改变图片大小
fill 拉伸整张图片以填满容器的整个高度和宽度，默认值
center_vertical 图片放于容器垂直方向的中心位置，不改变图片大小
center_horizontal 图片放于容器水平方向的中心位置，不改变图片大小
fill_vertical 在垂直方向上拉伸图片以填满容器的整个高度
fill_horizontal 在水平方向上拉伸图片以填满容器的整个宽度
clip_vertical 附加选项，裁剪基于垂直方向的gravity设置，设置top时会裁剪底部，设置bottom时会裁剪顶部，其他情况会同时裁剪顶部和底部
clip_horizontal 附加选项，裁剪基于水平方向的gravity设置，设置left时会裁剪右侧，设置right时会裁剪左侧，其他情况会同时裁剪左右两侧
android:antialias 设置是否开启抗锯齿

android:dither 设置是否抖动，图片与屏幕的像素配置不同时会用到，比如图片是ARGB 8888的，而屏幕是RGB565

android:filter 设置是否允许对图片进行滤波，对图片进行收缩或者延展使用滤波可以获得平滑的外观效果

android:tint 给图片着色，比如图片本来是黑色的，着色后可以变成白色

android:tileMode 设置图片平铺的方式，取值为下面四种之一：

disable 不做任何平铺，默认设置
repeat 图片重复铺满
mirror 使用交替镜像的方式重复图片的绘制
clamp 复制图片边缘的颜色来填充容器剩下的空白部分，比如引入的图片如果是白色的边缘，那么图片所在的容器里除了图片，剩下的空间都会被填充成白色
android:alpha 设置图片的透明度，取值范围为0.0~1.0之间，0.0为全透明，1.0为全不透明，API Level最低要求是11，即Android 3.0

android:mipMap 设置是否可以使用mipmap，但API Level最低要求是17，即Android 4.2

android:autoMirrored 设置图片是否需要镜像反转，当布局方向是RTL，即从右到左布局时才有用，API Level 19(Android 4.4)才添加的属性

android:tileModeX 和tileMode一样设置图片的平铺方式，只是这个属性只设置水平方向的平铺方式，这是API Level 21(Android 5.0)才添加的属性

android:tileModeY 和tileMode一样设置图片的平铺方式，只是这个属性只设置垂直方向的平铺方式，这是API Level 21(Android 5.0)才添加的属性

android:tintMode 着色模式，也是API Level 21(Android 5.0)才添加的属性