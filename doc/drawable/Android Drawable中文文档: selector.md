#一 xml属性

android:state_enabled: 设置触摸或点击事件是否可用状态，一般只在false时设置该属性，表示不可用状态
android:state_pressed: 设置是否按压状态，一般在true时设置该属性，表示已按压状态，默认为false
android:state_selected: 设置是否选中状态，true表示已选中，false表示未选中
android:state_checked: 设置是否勾选状态，主要用于CheckBox和RadioButton，true表示已被勾选，false表示未被勾选
android:state_checkable: 设置勾选是否可用状态，类似state_enabled，只是state_enabled会影响触摸或点击事件，而state_checkable影响勾选事件
android:state_focused: 设置是否获得焦点状态，true表示获得焦点，默认为false，表示未获得焦点
android:state_window_focused: 设置当前窗口是否获得焦点状态，true表示获得焦点，false表示未获得焦点，例如拉下通知栏或弹出对话框时，当前界面就会失去焦点；另外，ListView的ListItem获得焦点时也会触发true状态，可以理解为当前窗口就是ListItem本身
android:state_activated: 设置是否被激活状态，true表示被激活，false表示未激活，API Level 11及以上才支持，可通过代码调用控件的setActivated(boolean)方法设置是否激活该控件
android:state_hovered: 设置是否鼠标在上面滑动的状态，true表示鼠标在上面滑动，默认为false(API Level 14 or above)
android:enterFadeDuration 状态改变时，新状态展示时的淡入时间，以毫秒为单位(API Level 11 or above)
android:exitFadeDuration 状态改变时，旧状态消失时的淡出时间，以毫秒为单位(API Level 11 or above)


**举例**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 当前窗口失去焦点时 -->
    <item android:color="@android:color/black" android:state_window_focused="false" />
    <!-- 不可用时 -->
    <item android:color="@android:color/background_light" android:state_enabled="false" />
    <!-- 按压时 -->
    <item android:color="@android:color/holo_blue_light" android:state_pressed="true" />
    <!-- 被选中时 -->
    <item android:color="@android:color/holo_green_dark" android:state_selected="true" />
    <!-- 被激活时 -->
    <item android:color="@android:color/holo_green_light" android:state_activated="true" />
    <!-- 默认时 -->
    <item android:color="@android:color/white" />
</selector>
```

**注意**

1. selector作为drawable资源时，item指定android:drawable属性，并放于drawable目录下；
2. selector作为color资源时，item指定android:color属性，并放于color目录下；
3. color资源也可以放于drawable目录，引用时则用@drawable来引用，但不推荐这么做，drawable资源和color资源最好还是分开；
4. android:drawable属性除了引用@drawable资源，也可以引用@color颜色值；但android:color只能引用@color；
5. item是从上往下匹配的，如果匹配到一个item那它就将采用这个item，而不是采用最佳匹配的规则；所以设置默认的状态，一定要写在最后，如果写在前面，则后面所有的item都不会起作用了。
