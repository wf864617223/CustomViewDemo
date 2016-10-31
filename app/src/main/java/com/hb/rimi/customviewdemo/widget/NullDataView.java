package com.hb.rimi.customviewdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.rimi.customviewdemo.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by rimi on 2016/10/20.
 */
public class NullDataView extends ViewGroup {

    private int border_space = 0;
    private int top_space = 0;

    public NullDataView(Context context) {
        super(context);
    }

    public NullDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.NullDataView, 0, 0);
        border_space = t.getDimensionPixelSize(R.styleable.NullDataView_nulldataview_border_space, border_space);
        top_space = t.getDimensionPixelSize(R.styleable.NullDataView_nulldataview_top_space, top_space);
        t.recycle();
        initView(context);
    }

    public NullDataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void initView(Context context) {
        TextView textView = new TextView(context);
        ImageView imageView = new ImageView(context);
        textView.setTextAppearance(context, R.style.TextViewStyle_No_Data);
        textView.setText(R.string.no_data_notice);
        imageView.setBackgroundResource(R.mipmap.icon_no_data_refresh);
        addView(imageView);
        addView(textView);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new com.hb.rimi.customviewdemo.widget.LayoutParams(getContext(), attrs);
    }

    /**
     * 注意调用默认的时候参数参数返回的是自定义的layoutparams
     *
     * @return
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new com.hb.rimi.customviewdemo.widget.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof com.hb.rimi.customviewdemo.widget.LayoutParams;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int subCount = getChildCount();

        int width = 0;
        int height = 0;

        int w1 = 0;
        int w2 = 0;

        int tempHeight = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < subCount; i++) {

            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            int cWidth = childView.getMeasuredWidth();
            int cHeight = childView.getMeasuredHeight();
            if (i == 0) {
                tempHeight += cHeight;
            } else {
                tempHeight += top_space + cHeight;
            }

            map.put(i, cWidth);
        }
        width = getMax(map) + 2 * border_space;
        height = border_space + tempHeight + border_space;
        setMeasuredDimension(width, height);
    }

    /**
     * 获取一组list中的最大值
     *
     * @param map
     * @return
     */
    public Integer getMax(HashMap<Integer, Integer> map) {
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        int lastNum = 0;
        int maxNum = 0;
        while (iterator.hasNext()) {
            int currNum = map.get(iterator.next());
            if (currNum > lastNum) {
                maxNum = currNum;
            } else {
                maxNum = lastNum;
            }
            lastNum = maxNum;
        }
        return maxNum;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int subCount = getChildCount();
        //子view的四个顶点
        int rl = 0;
        int rt = 0;
        int rr = 0;
        int rb = 0;
        for (int i = 0; i < subCount; i++) {
            View childView = getChildAt(i);
            int cW = childView.getMeasuredWidth();
            int cH = childView.getMeasuredHeight();
            if (i == 0) {
                rl = (getWidth() - cW) / 2;
                rt = border_space;
                rr = rl + cW;
                rb = rt + cH;
            } else if (i == subCount - 1) {
                rl = (getWidth() - cW) / 2;
                rt = rb + top_space;
                rr = rl + cW;
                rb = rt + cH;
            } else {
                rl = (getWidth() - cW) / 2;
                rt = rb + top_space;
                rr = rl + cW;
                rb = rt + cH;
            }
            childView.layout(rl, rt, rr, rb);
        }
    }
}
