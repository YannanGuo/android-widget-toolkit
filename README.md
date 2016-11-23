# 大风车Widget

[![](https://jitpack.io/v/guoxiaoxing/android-gxwidget.svg)](https://jitpack.io/#guoxiaoxing/android-gxwidget)

添加依赖

To get a Git project into your build:

Step 1. Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
		...
	    maven { url "https://jitpack.io" }
    }
}
```

Step 2. Add the dependency

```
dependencies {
    compile 'com.github.guoxiaoxing:android-gxwidget:v1.0.0'
}
```


版本更迭

android-gxwidget:v1.0.0 -- 添加流光文字View, 添加步骤统计View(也可以用作时间线)

# 一 FlickerTextView

![](/art/screenshot_flicker_text_view.gif)

XML代码:

```
<com.souche.android.sdk.fcwidget.flicker.FlickerTextView
    android:id="@+id/ftv_skip"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="跳过"
    android:textColor="@color/base_fc_c11"
    android:textSize="@dimen/base_fc_s5"
    app:reflectionColor="#000000"/>
```

Java代码:

```java
//开始流光
Flicker flicker = new Flicker();
flicker.setDuration(1333);
c.start(mFTVSkipText);

//取消流光
flicker.cancel();
```

# 二 FCStepView