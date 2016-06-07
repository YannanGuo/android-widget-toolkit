#一 InputType

官方文档:https://developer.android.com/reference/android/text/InputType.html
只允许输入数字:android:inputType="number"
只允许输入数字和英文字母:android:inputType="textVisiblePassword"
只允许输入 @ . 数字和英文字母:android:inputType="textEmailAddress"。

#二 windowSoftInputMode

官方文档:https://developer.android.com/guide/topics/manifest/activity-element.html

>windowSoftInputMode属性用来设置窗口软键盘的交互模式, 它影响两件事情: 
软键盘的状态——是否它是隐藏或显示——当活动(Activity)成为用户关注的焦点。
活动的主窗口调整——是否减少活动主窗口大小以便腾出空间放软键盘或是否当活动窗口的部分被软键盘覆盖时它的内容的当前焦点是可见的。

stateUnspecified

>未指定状态，当我们没有设置android:windowSoftInputMode属性的时候，软件默认采用的就是这种交互方式, 当设置属性为stateUnspecified的
时候，系统是默认不弹出软键盘的，但是当有获得焦点的输入框的界面有滚动的需求的时候，会自动弹出软键盘。至于为什么非要强调要获取焦点的输入框，
这是因为，如果不是输入框获取焦点，软键盘也是不会自动弹出的，让界面不自动弹出软键盘的其中一个解决方案，就是在xml文件中，设置一个非输入框控
件获取焦点，从而阻止键盘弹出。

stateUnchanged

>状态不改变, 当前界面的软键盘状态，取决于上一个界面的软键盘状态。举个例子，假如当前界面键盘是隐藏的，那么跳转之后的界面，软键盘也是隐藏的；
如果当前界面是显示的，那么跳转之后的界面，软键盘也是显示状态。

stateHidden

>隐藏状态, 当用户选择该Activity时，软键盘被隐藏。注意这里是选择了该Activity, 而不是从别的Activity返回到该Activity。

stateAlwaysHidden

>隐藏状态，当该Activity主窗口获取焦点时, 软键盘总是被显示。

stateVisible

>当用户选择该Activity时，软键盘被显示。注意这里是选择了该Activity, 而不是从别的Activity返回到该Activity。

stateAlwaysVisible

>显示状态，当该Activity主窗口获取焦点时, 软键盘总是被显示。

举个例子，说明一下stateHidden与stateAlwaysHidden, stateVisible与stateAlwaysVisible

当我们设置为stateVisible属性，如果当前的界面键盘是显示的，当我们点击按钮跳转到下个界面的时候，软键盘会因为输入框失去焦点而隐藏起来，
当我们再次回到当前界面的时候，键盘这个时候是隐藏的。但是如果我们设置为stateAlwaysVisible，我们跳转到下个界面，软键盘还是隐藏的，但是当我们再
次回来的时候，软键盘是会显示出来的。所以，这个Always就解释了这个区别，不管什么情况到达当前界面(正常跳转或者是上一个界面被用户返回)，软键盘都是
显示状态。

同理，上面的stateHidden和stateAlwaysHidden，我估计区别也是这样的，就是说，stateAlwaysHidden无论如何都是隐藏的，但是如果在
跳转到下个界面的时候，软键盘被召唤出来了，那么当下个界面被用户返回的时候，键盘应该是不会被隐藏的，但是，我还没有找到能够跳转到下个界面，还让当前
界面软键盘不消失的方法，所以暂时不能验证。

adjustUnspecified

>默认的设置模式。在这中情况下，系统会根据界面选择不同的模式。如果界面里面有可以滚动的控件，比如ScrowView，系统会减小可以滚动的界面的大小，从而
保证即使软键盘显示出来了，也能够看到所有的内容。如果布局里面没有滚动的控件，那么软键盘可能就会盖住一些内容，

adjustResize

>表示Activity的主窗口总是会被调整大小，从而保证软键盘显示空间。

adjustPan

>Activity的屏幕大小并不会调整来保证软键盘的空间，而是采取了另外一种策略，系统会通过布局的移动，来保证用户要进行输入的输入框肯定在用户的失业范围里面，
从而让用户可以看到自己输入的内容。

**注意**

如果我们不设置"adjust..."的属性，对于没有滚动控件的布局来说，采用的是adjustPan方式，而对于有滚动控件的布局，则是采用的adjustResize方式。