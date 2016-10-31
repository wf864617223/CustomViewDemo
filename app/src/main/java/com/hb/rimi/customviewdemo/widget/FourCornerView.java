package com.hb.rimi.customviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rimi on 2016/10/19.
 */
public class FourCornerView extends ViewGroup {

    public FourCornerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 生成布局参数
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 开始测量所有view的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 测量使用的模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        //测量出所有childView的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //获取子view的数量
        int subCount = getChildCount();
        /**
         * 记录我们要设置ViewGroup的宽高 如果是wrap_content
         */
        int width = 0;
        int height = 0;

        int lHeight = 0;//计算左边两个View的高度
        int rHeight = 0;//计算右边两个View的高度

        int tWidth = 0;//计算上边两个view的宽度
        int bWidth = 0;//计算下边两个view的宽度

        int cWidth = 0;//子view测量出的宽度
        int cHeight = 0;//子view测量出的高度

        MarginLayoutParams cParams;//margin计算容器 布局参数

        /**
         * 根据childView计算出的宽和高，以及设置的margin的计算容器的宽和高 主要是用于ViewGroup ->wrap_content模式
         */
        for (int i = 0; i < subCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();

            cParams = (MarginLayoutParams) childView.getLayoutParams();
            //上边两个view
            if (i == 0 || i == 1) {
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if (i == 2 || i == 3) {
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if (i == 0 || i == 2) {
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
            if (i == 1 || i == 3) {
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
        }

        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth : width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    /**
     * 锁定所有子View的位置
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int subCount=getChildCount();
        int cWidth=0;
        int cHeight=0;
        MarginLayoutParams cParams=null;

        for (int i=0;i<subCount;i++){
            View childView=getChildAt(i);
            cWidth=childView.getMeasuredWidth();
            cHeight=childView.getMeasuredHeight();
            cParams=(MarginLayoutParams)childView.getLayoutParams();

            int cl=0,cr=0,ct=0,cb=0;
            switch (i){
                case 0:
                    cl=cParams.leftMargin;
                    ct=cParams.topMargin;
                    break;
                case 1:
                    cl=getWidth()-cWidth-cParams.leftMargin-cParams.rightMargin;
                    ct=cParams.topMargin;
                    break;
                case 2:
                    cl=cParams.leftMargin;
                    ct=getHeight()-cHeight-cParams.bottomMargin;
                    break;
                case 3:
                    cl=getWidth()-cWidth-cParams.leftMargin-cParams.rightMargin;
                    ct=getHeight()-cHeight-cParams.bottomMargin;
                    break;
            }

            cr=cl+cWidth;
            cb=cHeight+ct;
            System.out.println(cl+"*"+ct+"*"+cr+"*"+cb);
            childView.layout(cl,ct,cr,cb);
        }
    }
}
