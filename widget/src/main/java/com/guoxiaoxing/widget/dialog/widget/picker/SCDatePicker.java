package com.guoxiaoxing.widget.dialog.widget.picker;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.guoxiaoxing.widget.R;
import com.guoxiaoxing.widget.animation.BaseAnimatorSet;
import com.guoxiaoxing.widget.animation.SlideEnter.SlideBottomEnter;
import com.guoxiaoxing.widget.animation.SlideExit.SlideBottomExit;
import com.guoxiaoxing.widget.dialog.widget.base.BaseDialog;
import com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelDayPicker;
import com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelMonthPicker;
import com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelPicker;
import com.guoxiaoxing.widget.dialog.widget.picker.impl.SCWheelYearPicker;
import com.guoxiaoxing.widget.dialog.widget.picker.model.IPickerModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SCDatePicker extends BaseDialog implements SCWheelPicker.OnItemSelectedListener {

    public static final String DATE_FORMAT_YMD = "yyyy/MM/dd";
    public static final String DATE_FORMAT_YM = "yyyy/MM";
    public static final String DATE_FORMAT_Y = "yyyy";

    private static final int DEFAULT_MIN_YEAR = 1900;
    private static final int DEFAULT_MAX_YEAR = 2100;

    private String mType = DATE_FORMAT_YMD;

    private int mMinYear = DEFAULT_MIN_YEAR;
    private int mMaxYear = DEFAULT_MAX_YEAR;

    private long mPickedMin = 0;
    private long mPickedMax = 0;
    private long mPickedDate = 0;

    private TextView mTvCancel;
    private TextView mTvConfirm;

    private int mYear, mMonth, mDay;

    private String rightText = "确定";
    private int rightColor = Color.parseColor("#4CA6FF");
    private int rightSize = 16;
    private String leftText = "";
    private int leftColor = Color.parseColor("#1A1A1A");
    private int leftSize = 16;

    private SCWheelYearPicker mPickerYear;
    private SCWheelMonthPicker mPickerMonth;
    private SCWheelDayPicker mPickerDay;

    private OnDatePickedListener mOnDatePickedListener;

    public SCDatePicker(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.souche_widget_dialog_date_picker, null, false);

        mTvCancel = (TextView) view.findViewById(R.id.fcprompt_date_picker_tv_cancel);
        mTvConfirm = (TextView) view.findViewById(R.id.fcprompt_date_picker_tv_confirm);

        mPickerYear = (SCWheelYearPicker) view.findViewById(R.id.wheel_date_picker_year);
        mPickerMonth = (SCWheelMonthPicker) view.findViewById(R.id.wheel_date_picker_month);
        mPickerDay = (SCWheelDayPicker) view.findViewById(R.id.wheel_date_picker_day);
        return view;
    }


    @Override
    public void setupView() {
        if (getWindow() != null) {
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        setCanceledOnTouchOutside(true);

        switch (mType) {
            case DATE_FORMAT_YMD:
                break;
            case DATE_FORMAT_YM:
                mPickerDay.setVisibility(View.GONE);
                break;
            case DATE_FORMAT_Y:
                mPickerMonth.setVisibility(View.GONE);
                mPickerDay.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        mTvCancel.setText(leftText);
        mTvCancel.setTextColor(leftColor);
        mTvCancel.setTextSize(leftSize);

        mTvConfirm.setText(rightText);
        mTvConfirm.setTextColor(rightColor);
        mTvConfirm.setTextSize(rightSize);

        mPickerYear.setOnItemSelectedListener(this);
        mPickerMonth.setOnItemSelectedListener(this);
        mPickerDay.setOnItemSelectedListener(this);

        mPickerYear.setMaxYear(mMaxYear);
        mPickerYear.setMinYear(mMinYear);
        setMaximumWidthTextYear();
        mPickerMonth.setMaximumWidthText("00");
        mPickerDay.setMaximumWidthText("00");

        if (mPickedDate == -1) {
            mPickedDate = System.currentTimeMillis();
        }

        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(mPickedDate);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        mPickerYear.setPickedYear(mYear);
        mPickerMonth.setPickedMonth(mMonth);
        mPickerDay.setYear(mYear);
        mPickerDay.setMonth(mMonth);
        mPickerDay.setPickedDay(mDay);

//        mTvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = null;
                String formatYear = String.format("%04d", mYear);
                String formatMonth = String.format("%02d", mMonth);
                String formatDay = String.format("%02d", mDay);

                switch (mType) {
                    case DATE_FORMAT_YMD:
                        date = formatYear + "/" + formatMonth + "/" + formatDay;
                        mPickedDate = parseDateYMD(date);
                        break;
                    case DATE_FORMAT_YM:
                        date = formatYear + "/" + formatMonth;
                        mPickedDate = parseDateYM(date);
                        break;
                    case DATE_FORMAT_Y:
                        date = formatYear;
                        mPickedDate = parseDateY(date);
                        break;
                    default:
                        break;
                }
                //mPickedMin > 0 || mPickedMax > 0 才会进行比较
                boolean isMin = true;
                boolean isMax = true;
                if (mPickedMin > 0) {
                    isMin = mPickedDate >= mPickedMin;
                }
                if (mPickedMax > 0) {
                    isMax = mPickedDate <= mPickedMax;
                }
                if (isMin && isMax) {
                    dismiss();
                    if (mOnDatePickedListener != null) {
                        mOnDatePickedListener.onDatePicked(mPickedDate, date);
                    }
                } else {
                    if (mOnDatePickedListener != null) {
                        mOnDatePickedListener.onDatePickFailed();
                    }
                }
            }
        });
    }

    private void setMaximumWidthTextYear() {
        List years = mPickerYear.getData();
        String lastYear = String.valueOf(years.get(years.size() - 1));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastYear.length(); i++)
            sb.append("0");
        mPickerYear.setMaximumWidthText(sb.toString());
    }

    @Override
    public void onItemSelected(SCWheelPicker picker, IPickerModel data, int position) {
        if (picker.getId() == R.id.wheel_date_picker_year) {
            mYear = Integer.valueOf(data.getCode());
            mPickerDay.setYear(mYear);
        } else if (picker.getId() == R.id.wheel_date_picker_month) {
            mMonth = Integer.valueOf(data.getCode());
            mPickerDay.setMonth(mMonth);
        }
        mDay = mPickerDay.getCurrentDay();
    }

    @Override
    public BaseAnimatorSet createShowAnimation() {
        return new SlideBottomEnter();
    }

    @Override
    public BaseAnimatorSet createDismissAnimation() {
        return new SlideBottomExit();
    }

    public SCDatePicker withType(String type) {
        if(TextUtils.isEmpty(type)){
            return this;
        }
        mType = type;
        return this;
    }

    public SCDatePicker withLeftText(String text) {
        if (TextUtils.isEmpty(text)) {
            return this;
        }
        leftText = text;
        return this;
    }

    public SCDatePicker withLeftTextColor(String textColor) {
        if (TextUtils.isEmpty(textColor)) {
            return this;
        }
        leftColor = Color.parseColor(textColor);
        return this;
    }

    public SCDatePicker withLeftTextSize(String textSize) {
        if (TextUtils.isEmpty(textSize)) {
            return this;
        }
        leftSize = Integer.valueOf(textSize);
        return this;
    }

    public SCDatePicker withRightText(String text) {
        if (TextUtils.isEmpty(text)) {
            return this;
        }
        rightText = text;
        return this;
    }

    public SCDatePicker withRightTextColor(String textColor) {
        if (TextUtils.isEmpty(textColor)) {
            return this;
        }
        rightColor = Color.parseColor(textColor);
        return this;
    }

    public SCDatePicker withRightTextSize(String textSize) {
        if (TextUtils.isEmpty(textSize)) {
            return this;
        }
        rightSize = Integer.valueOf(textSize);
        return this;
    }

    public SCDatePicker withPickedDate(long pickedDate) {
        mPickedDate = pickedDate;
        return this;
    }

    public SCDatePicker withPickedDate(String pickedDate) {
        long timestamp = 0;
        if (!TextUtils.isEmpty(pickedDate)) {
            switch (mType) {
                case DATE_FORMAT_YMD:
                    timestamp = parseDateYMD(pickedDate);
                    break;
                case DATE_FORMAT_YM:
                    timestamp = parseDateYM(pickedDate);
                    break;
                case DATE_FORMAT_Y:
                    timestamp = parseDateY(pickedDate);
                    break;
                default:
                    break;
            }
        } else {
            timestamp = System.currentTimeMillis();
        }
        withPickedDate(timestamp);
        return this;
    }

    public SCDatePicker withPickedRange(String pickedMin, String pickedMax) {
        long min = 0;
        long max = 0;
        if (!TextUtils.isEmpty(pickedMin)) {
            switch (mType) {
                case DATE_FORMAT_YMD:
                    min = parseDateYMD(pickedMin);
                    break;
                case DATE_FORMAT_YM:
                    min = parseDateYM(pickedMin);
                    break;
                case DATE_FORMAT_Y:
                    min = parseDateY(pickedMin);
                    break;
                default:
                    break;
            }
            mPickedMin = min;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(min);
            mMinYear = calendar.get(Calendar.YEAR);
        }
        if (!TextUtils.isEmpty(pickedMax)) {
            switch (mType) {
                case DATE_FORMAT_YMD:
                    max = parseDateYMD(pickedMax);
                    break;
                case DATE_FORMAT_YM:
                    max = parseDateYM(pickedMax);
                    break;
                case DATE_FORMAT_Y:
                    max = parseDateY(pickedMax);
                    break;
                default:
                    break;
            }
            mPickedMax = max;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(max);
            mMaxYear = calendar.get(Calendar.YEAR);
        }
        return this;
    }

    /**
     * get long from yyyy/MM/dd
     *
     * @param date date
     * @return return
     */
    private long parseDateYMD(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    /**
     * get long from yyyy/MM
     *
     * @param date date
     * @return return
     */
    private long parseDateYM(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat(DATE_FORMAT_YM, Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    /**
     * get long from yyyy
     *
     * @param date date
     * @return return
     */
    private long parseDateY(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat(DATE_FORMAT_Y, Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    public SCDatePicker withDatePickedListener(OnDatePickedListener listener) {
        mOnDatePickedListener = listener;
        return this;
    }

    public interface OnDatePickedListener {

        void onDatePickFailed();

        void onDatePicked(long timestamp, String date);
    }
}