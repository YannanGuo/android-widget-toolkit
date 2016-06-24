transition其实是继承自layer-list的，只是，transition只能管理两层drawable，另外提供了两层drawable之间切换的方法，切换时还会有淡入淡出的动画效果。

```
<?xml version="1.0" encoding="utf-8"?>
<transition xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/on" />
    <item android:drawable="@drawable/off" />
</transition>
```

```
((TransitionDrawable)drawable).startTransition(500); //正向切换，即从第一个drawable切换到第二个
((TransitionDrawable)drawable).reverseTransition(500); //逆向切换，即从第二个drawable切换回第一个
```