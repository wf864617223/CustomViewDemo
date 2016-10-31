package com.hb.rimi.customviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rimi on 2016/10/19.
 */
public class FlowLayoutView extends ViewGroup {
    /**
     * 存储所有的view ，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<>();

    public FlowLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 产生布局
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 测量子View的宽度高度
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获取父容器测量的模式和宽高
         */
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        //如果是wrap_content模式则，记录宽高
        int width = 0;
        int height = 0;

        int lineWidth = 0;//记录每一行的宽
        int lineHeight = 0;//记录每一行的高


        int subCount = getChildCount();//获取子subView的数量
        for (int i = 0; i < subCount; i++) {
            View childView = getChildAt(i);
            //必须先测量出每个subview，然后才能获取宽高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //得到childview的lp
            MarginLayoutParams cParams = (MarginLayoutParams) childView.getLayoutParams();
            //当前childView实际占据的宽度
            int childWidth = childView.getMeasuredWidth() + cParams.leftMargin + cParams.rightMargin;
            //当前childView实际占据的高度
            int childHeight = childView.getMeasuredHeight() + cParams.topMargin + cParams.bottomMargin;
            /**
             * 如果加入当前child,则超出最大宽度，则得到目前最大宽度给width,累加height,然后开启新行
             */

            if ((lineWidth + childWidth) > sizeWidth) {
                width = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;//重新开始行
                height += lineHeight;
                //开启记录下一项的高度
                lineHeight = childHeight;
            } else {
                //否则累加值lineWIth,lineHeight取最大高度
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            //如果是最后一个，则将当前记录的最大宽度和当前lineWIdth做比较
            if (i == subCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    /**
     * 设置subview的位置
     *
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();
        int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        //存储每一行所有的childView
        List<View> lineViews = new ArrayList<>();
        int subCount = getChildCount();
        //变量所有的孩子
        for (int i = 0; i < subCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams cParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            //如果已经需要换行。 如果当前行的宽度 +上一个childView的后的宽度大于整个ViewGorup的行宽，则换行。
            if (childWidth + cParams.leftMargin + cParams.rightMargin + lineWidth > width) {
                //记录这一行所有的View以及最大高度
                mLineHeight.add(lineHeight);
                //将当前行的childView保存，然后开启新的ArrayList保存下一行的childView；
                mAllViews.add(lineViews);
                lineWidth = 0;//重置行宽
                lineViews = new ArrayList<>();
            }
            /**
             * 如果不需要换行则累加
             */
            lineWidth += childWidth + cParams.leftMargin + cParams.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + cParams.topMargin + cParams.bottomMargin);
            lineViews.add(childView);
        }
        //记录最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        //得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            lineViews = mAllViews.get(i);

            //遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                //计算child的left top right bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                child.layout(lc, tc, rc, bc);

                left +=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;

            }
            left=0;
            top +=lineHeight;
        }

    }
}
