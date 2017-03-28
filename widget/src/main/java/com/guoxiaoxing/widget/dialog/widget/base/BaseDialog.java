package com.guoxiaoxing.widget.dialog.widget.base;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.animation.BaseAnimatorSet;

public abstract class BaseDialog extends Dialog {
    private String TAG = BaseDialog.class.getSimpleName();
    protected Context mContext;
    private boolean mCancel = false;
    private BaseAnimatorSet mShowAnim;
    private BaseAnimatorSet mDismissAnim;
    private View mOnCreateView;
    private boolean mIsShowAnim;
    private boolean mIsDismissAnim;
    private boolean mAutoDismiss;
    private long mAutoDismissDelay = 1500;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public BaseDialog(Context context) {
        super(context, R.style.fcprompt_dialog);
        mContext = context;
        setCanceledOnTouchOutside(true);
        Log.d(TAG, "constructor");
    }

    /**
     * inflate layout for dialog ui and return (填充对话框所需要的布局并返回)
     * <pre>
     * public View onCreateView() {
     *      View inflate = View.inflate(mContext, R.layout.dialog_share, null);
     *      return inflate;
     * }
     * </pre>
     */
    public abstract View onCreateView();

    /**
     * set Ui data or logic opreation before attatched window(在对话框显示之前,设置界面数据或者逻辑)
     */
    public abstract void setupView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        mOnCreateView = onCreateView();
        setContentView(mOnCreateView);
        setupView();
    }

    /**
     * get actual created view(获取实际创建的View)
     */
    public View getCreateView() {
        return mOnCreateView;
    }

    /**
     * when dailog attached to window,set dialog width and height and show anim
     * (当dailog依附在window上,设置对话框宽高以及显示动画)
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
        mShowAnim = createShowAnimation();
        mDismissAnim = createDismissAnimation();
        if (mShowAnim != null) {
            mShowAnim.listener(new BaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mIsShowAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsShowAnim = false;
                    delayDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsShowAnim = false;
                }
            }).playOn(mOnCreateView);
        } else {
            BaseAnimatorSet.reset(mOnCreateView);
            delayDismiss();
        }
    }


    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        super.setCanceledOnTouchOutside(cancel);
    }

    @Override
    public void show() {
        Log.d(TAG, "show");
        super.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
    }

    @Override
    public void dismiss() {
        Log.d(TAG, "dismiss");
        if (mDismissAnim != null) {
            mDismissAnim.listener(new BaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mIsDismissAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }
            }).playOn(mOnCreateView);
        } else {
            superDismiss();
        }
    }

    /**
     * dismiss without anim(无动画dismiss)
     */
    public void superDismiss() {
        super.dismiss();
    }

    /**
     * dialog anim by styles(动画弹出对话框,style动画资源)
     */
    public void show(int animStyle) {
        Window window = getWindow();
        window.setWindowAnimations(animStyle);
        show();
    }

    /**
     * set window dim or not(设置背景是否昏暗)
     */
    public BaseDialog dimEnabled(boolean isDimEnabled) {
        if (isDimEnabled) {
            getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);
        } else {
            getWindow().clearFlags(LayoutParams.FLAG_DIM_BEHIND);
        }
        return this;
    }

    public BaseAnimatorSet createShowAnimation() {
        return null;
    }

    public BaseAnimatorSet createDismissAnimation() {
        return null;
    }

    /**
     * automatic dimiss dialog after given delay(在给定时间后,自动消失)
     */
    public BaseDialog autoDismiss(boolean autoDismiss) {
        mAutoDismiss = autoDismiss;
        return this;
    }

    /**
     * set dealy (milliseconds) to dimiss dialog(对话框消失延时时间,毫秒值)
     */
    public BaseDialog autoDismissDelay(long autoDismissDelay) {
        mAutoDismissDelay = autoDismissDelay;
        return this;
    }

    private void delayDismiss() {
        if (mAutoDismiss && mAutoDismissDelay > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, mAutoDismissDelay);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return;
        }
        super.onBackPressed();
    }
}
