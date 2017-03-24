package com.guoxiaoxing.widget.toast;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guoxiaoxing.widget.R;


/**
 * Author: guoxiaoxing
 * Date: 16/9/10 下午2:16
 * Function: Toast
 * <p>
 * For more information, you can contact me by guoxiaoxing@souche.com
 */
public class SCToast {

    public static final String TOAST_TYPE_DONE = "done";
    public static final String TOAST_TYPE_SUCCESS = "success";
    public static final String TOAST_TYPE_COLLECTION = "collection";

    private static Toast mToast;

    public static void toast(Context context, String icon, String msg, int length) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        mToast = new Toast(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.souche_widget_toast, null, false);
        ImageView image = (ImageView) layout.findViewById(R.id.iv_toast_icon);
        TextView text = (TextView) layout.findViewById(R.id.tv_toast_message);
        if (!TextUtils.isEmpty(icon)) {
            image.setVisibility(View.VISIBLE);
            switch (icon) {
                case TOAST_TYPE_DONE:
                    image.setImageResource(R.drawable.souche_widget_toast_done);
                    break;
                case TOAST_TYPE_SUCCESS:
                    image.setImageResource(R.drawable.souche_widget_toast_success);
                    break;
                case TOAST_TYPE_COLLECTION:
                    image.setImageResource(R.drawable.souche_widget_toast_collect);
                    break;
            }
        }
        text.setText(msg);
        mToast.setView(layout);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        if (length <= 2000) {
            mToast.setDuration(Toast.LENGTH_SHORT);
        } else if (length > 2000) {
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static int getDuration() {
        return mToast.getDuration();
    }
}
