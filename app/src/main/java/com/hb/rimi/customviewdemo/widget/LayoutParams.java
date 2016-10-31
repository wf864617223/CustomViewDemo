package com.hb.rimi.customviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by rimi on 2016/10/19.
 */
public class LayoutParams extends ViewGroup.LayoutParams {
    public int left=0;
    public int top=0;
    public int bottom=0;
    public LayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
    }

    public LayoutParams(int width, int height) {
        super(width, height);
    }

    public LayoutParams(ViewGroup.LayoutParams source) {
        super(source);
    }

}
