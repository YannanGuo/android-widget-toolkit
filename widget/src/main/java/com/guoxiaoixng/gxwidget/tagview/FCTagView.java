package com.guoxiaoixng.gxwidget.tagview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.guoxiaoxing.gxwidget.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FCTagView
 * <p>
 * For more information, you can contact me by guoxiaoxing@souche.com
 *
 * @author guoxiaoxing
 * @since 16/10/31 下午2:34
 */
public class FCTagView extends ViewGroup {

    public static final int TAG_VIEW_STYLE_DEFAULT = 0x000000;
    public static final int TAG_VIEW_STYLE_NO_BORDER = 0x000001;

    private static final String TAG = FCTagView.class.getSimpleName();
    private static final int TYPE_TEXT_NORMAL = 1;
    private List<String> mTagList = new ArrayList<>();

    private LayoutInflater mInflater;
    private OnTagClickListener onTagClickListener;

    private int mStyle;

    private int sizeWidth;
    private int sizeHeight;

    private float mTagSize;
    private int mTagColor;
    private int mBackground;
    private int mViewBorder;
    private int mTagBorderHor;
    private int mTagBorderVer;

    private int mTagResId;
    private int mRightImageResId;
    private boolean mSingleLine;
    private boolean mShowRightImage;
    private boolean mShowEndText;
    private boolean mCanTagClick;
    private String endTextString;

    private int imageWidth;
    private int imageHeight;
    private ImageView imageView = null;

    private int endTextWidth = 0;
    private int endTextHeight = 0;
    private TextView endText = null;

    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_TEXT_SIZE = 14;
    private static final int DEFAULT_TEXT_BACKGROUND = R.drawable.fctagview_background_yellow;
    private static final int DEFAULT_VIEW_BORDER = 30;
    private static final int DEFAULT_TEXT_BORDER_HORIZONTAL = 0;
    private static final int DEFAULT_TEXT_BORDER_VERTICAL = 15;

    private static final int DEFAULT_TAG_RESID = R.layout.fctagview_item_tag_view;
    private static final int DEFAULT_RIGHT_IMAGE = R.drawable.fctagview_arrow_right;
    private static final boolean DEFAULT_SINGLE_LINE = false;
    private static final boolean DEFAULT_SHOW_RIGHT_IMAGE = true;
    private static final boolean DEFAULT_SHOW_END_TEXT = true;
    private static final String DEFAULT_END_TEXT_STRING = " … ";
    private static final boolean DEFAULT_CAN_TAG_CLICK = true;

    public FCTagView(Context context) {
        this(context, null);
    }

    public FCTagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FCTagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mInflater = LayoutInflater.from(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.FCTagView,
                defStyleAttr,
                defStyleAttr
        );

        mStyle = a.getInteger(R.styleable.FCTagView_fcTagViewStyle, 0);

        if (mStyle == TAG_VIEW_STYLE_DEFAULT) {
            mViewBorder = DEFAULT_VIEW_BORDER;
            mTagBorderHor = DEFAULT_TEXT_BORDER_HORIZONTAL;
            mTagBorderVer = DEFAULT_TEXT_BORDER_VERTICAL;
        } else if (mStyle == TAG_VIEW_STYLE_NO_BORDER) {
            mViewBorder = 0;
            mTagBorderHor = 0;
            mTagBorderVer = DEFAULT_TEXT_BORDER_VERTICAL;
        }

        mTagSize = DEFAULT_TEXT_SIZE;
        mTagColor = DEFAULT_TEXT_COLOR;
        mBackground = DEFAULT_TEXT_BACKGROUND;
        mCanTagClick = DEFAULT_CAN_TAG_CLICK;

        mRightImageResId = DEFAULT_RIGHT_IMAGE;
        mSingleLine = DEFAULT_SINGLE_LINE;
        mShowRightImage = DEFAULT_SHOW_RIGHT_IMAGE;
        mShowEndText = DEFAULT_SHOW_END_TEXT;
        endTextString = DEFAULT_END_TEXT_STRING;
        mTagResId = DEFAULT_TAG_RESID;

        a.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return (!mCanTagClick && mSingleLine) || super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 计算 ChildView 宽高
     *
     * @param widthMeasureSpec  widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 计算 ViewGroup 上级容器为其推荐的宽高
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        //计算 childView 宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        setupSingleLineView(widthMeasureSpec, heightMeasureSpec);

        int totalWidth = 0;
        int totalHeight = mTagBorderVer;

        if (mSingleLine) {
            totalHeight = getSingleTotalHeight(totalWidth, totalHeight);
        } else {
            totalHeight = getMultiTotalHeight(totalWidth, totalHeight);
        }

        /**
         * 高度根据设置改变
         * 如果为 MATCH_PARENT 则充满父窗体，否则根据内容自定义高度
         */
        setMeasuredDimension(
                sizeWidth,
                (heightMode == MeasureSpec.EXACTLY ? sizeHeight : totalHeight));

    }

    private void setupSingleLineView(int widthMeasureSpec, int heightMeasureSpec) {
        if (!mSingleLine) {
            return;
        }
        if (mShowRightImage) {
            imageView = new ImageView(getContext());
            imageView.setImageResource(mRightImageResId);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            measureChild(imageView, widthMeasureSpec, heightMeasureSpec);
            imageWidth = imageView.getMeasuredWidth();
            imageHeight = imageView.getMeasuredHeight();
            addView(imageView);
        }

        if (mShowEndText) {
            endText = (TextView) mInflater.inflate(mTagResId, null);
            if (mTagResId == DEFAULT_TAG_RESID) {
                endText.setBackgroundResource(mBackground);
                endText.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTagSize);
                endText.setTextColor(mTagColor);
            }
            @SuppressLint("DrawAllocation")
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            endText.setLayoutParams(layoutParams);
            endText.setText(endTextString == null || endTextString.equals("") ? DEFAULT_END_TEXT_STRING : endTextString);
            measureChild(endText, widthMeasureSpec, heightMeasureSpec);
            endTextHeight = endText.getMeasuredHeight();
            endTextWidth = endText.getMeasuredWidth();
            addView(endText);
            endText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTagClickListener != null) {
                        onTagClickListener.onTagClick(-1);
                    }
                }
            });
        }
    }

    private int getSingleTotalHeight(int totalWidth, int totalHeight) {
        int childWidth;
        int childHeight;

        totalWidth += mViewBorder;

        int textTotalWidth = getTextTotalWidth();
        if (textTotalWidth < sizeWidth - imageWidth) {
            endText = null;
            endTextWidth = 0;
        }

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();


            if (i == 0) {
                totalWidth += childWidth;
                totalHeight = childHeight + mViewBorder;
            } else {
                totalWidth += childWidth + mTagBorderHor;
            }

            if (child.getTag() != null && (int) (child.getTag()) == TYPE_TEXT_NORMAL) {
                if (totalWidth + mTagBorderHor + mViewBorder + mViewBorder + endTextWidth + imageWidth < sizeWidth) {
                    child.layout(
                            totalWidth - childWidth + mTagBorderVer,
                            totalHeight - childHeight,
                            totalWidth + mTagBorderVer,
                            totalHeight);
                } else {
                    totalWidth -= childWidth + mViewBorder;
                    break;
                }
            }
        }

        if (endText != null) {
            endText.layout(
                    totalWidth + mViewBorder + mTagBorderVer,
                    totalHeight - endTextHeight,
                    totalWidth + mViewBorder + mTagBorderVer + endTextWidth,
                    totalHeight);
        }

        totalHeight += mViewBorder;

        if (imageView != null) {
            imageView.layout(
                    sizeWidth - imageWidth - mViewBorder,
                    (totalHeight - imageHeight) / 2,
                    sizeWidth - mViewBorder,
                    (totalHeight - imageHeight) / 2 + imageHeight);
        }

        return totalHeight;
    }

    private int getMultiTotalHeight(int totalWidth, int totalHeight) {
        int childWidth;
        int childHeight;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();

            totalWidth += childWidth + mViewBorder;

            if (i == 0) {
                totalHeight = childHeight + mViewBorder;
            }
            // + marginLeft 保证最右侧与 ViewGroup 右边距有边界
            child.layout(
                    totalWidth - childWidth + mTagBorderHor,
                    totalHeight - childHeight,
                    totalWidth + mTagBorderHor,
                    totalHeight);
        }
        return totalHeight + mViewBorder;
    }

    private int getTextTotalWidth() {
        if (getChildCount() == 0) {
            return 0;
        }
        int totalChildWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getTag() != null && (int) child.getTag() == TYPE_TEXT_NORMAL) {
                totalChildWidth += child.getMeasuredWidth() + mViewBorder;
            }
        }
        return totalChildWidth + mTagBorderHor * 2;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    public void setTagViewList(List<String> tagList) {
        this.mTagList = tagList;
        this.removeAllViews();
        if (mTagList != null && mTagList.size() > 0) {
            for (int i = 0; i < mTagList.size(); i++) {
                addView(i, mTagList.get(i));
            }
        }
    }

    public void addTagView(String tag) {
        mTagList.add(tag);
        addView(mTagList.size(), tag);
    }

    public void addTagView(int position, String tag) {
        mTagList.add(position, tag);
        addView(position, tag);
    }

    public void removeTagView(int position) {
        if (position < 0 || position > mTagList.size()) {
            throw new IndexOutOfBoundsException("The position is out of index in the TagList");
        }
        mTagList.remove(position);
        removeViewAt(position);
    }

    public void removeAllTagView() {
        mTagList.clear();
        this.removeAllViews();
    }

    public void setSingleLine(boolean mSingleLine) {
        this.mSingleLine = mSingleLine;
        this.setTagViewList(mTagList);
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    private void addView(final int position, String tag) {
        TextView tagView = (TextView) mInflater.inflate(mTagResId, null);
        if (mTagResId == DEFAULT_TAG_RESID) {
            switch (mStyle) {
                case TAG_VIEW_STYLE_DEFAULT:

                    if (position == 0) {
                        tagView.setBackgroundResource(R.drawable.fctagview_background_yellow);
                        tagView.setTextColor(ContextCompat.getColor(getContext(), R.color.fcwidget_fc_c11));
                    } else {
                        tagView.setBackgroundResource(R.drawable.fctagview_background_gray);
                        tagView.setTextColor(ContextCompat.getColor(getContext(), R.color.fcwidget_fc_c4));
                    }
                    break;
                case TAG_VIEW_STYLE_NO_BORDER:
                    mTagBorderHor = 0;
                    if (position == 0) {
                        tagView.setBackgroundResource(R.drawable.fctagview_background_yellow);
                        tagView.setTextColor(ContextCompat.getColor(getContext(), R.color.fcwidget_fc_c11));
                    } else {
                        tagView.setBackgroundResource(R.drawable.fctagview_background_white);
                        tagView.setTextColor(ContextCompat.getColor(getContext(), R.color.fcwidget_fc_c1));
                    }
                    break;
                default:
                    break;
            }
            tagView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTagSize);
        }
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tagView.setLayoutParams(layoutParams);
        tagView.setText(tag);
        tagView.setTag(TYPE_TEXT_NORMAL);
        tagView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTagClickListener != null) {
                    onTagClickListener.onTagClick(position);
                }
            }
        });
        addView(tagView);
    }

    private void removeView(int position) {
        removeViewAt(position);
    }

    public void setStyle(int style) {
        mStyle = style;
    }


    public interface OnTagClickListener {
        void onTagClick(int position);
    }

}
