# 大风车Widget

```
compile 'com.souche.android.sdk:fcwidget:1.0.0-SNAPSHOT'
```


版本更迭:

1.0.0-SNAPSHOT -- 添加流光文字View

1.0.0-SNAPSHOT -- 添加步骤统计View(也可以用作时间线)


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