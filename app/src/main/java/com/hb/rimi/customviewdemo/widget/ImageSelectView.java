package com.hb.rimi.customviewdemo.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hb.rimi.customviewdemo.R;

import java.util.ArrayList;

/**
 * Created by rimi on 2016/10/19.
 */
public class ImageSelectView extends ViewGroup {

    private static final int MAX_PHOTO_NUMBER = 9;
    int hSpace = 20;//横向间隔20个像素
    int vSpace = 20;//纵向间隔20个像素
    //subview的宽和高
    int childWidth = 0;
    int childHeight = 0;
    //初始化容量
    ArrayList<Integer> mImageResArrayList = new ArrayList<>(9);
    private int[] constImageIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private View addPhotoView;

    public ImageSelectView(Context context) {
        super(context);
    }

    public ImageSelectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageSelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.ImageSelectView, 0, 0);
        hSpace = t.getDimensionPixelSize(R.styleable.ImageSelectView_imageselect_hspace, hSpace);
        vSpace = t.getDimensionPixelSize(R.styleable.ImageSelectView_imageselect_vspace, vSpace);
        t.recycle();
        addPhotoView = new View(context);
        addView(addPhotoView);
        mImageResArrayList.add(new Integer(0));
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new com.hb.rimi.customviewdemo.widget.LayoutParams(getContext(), attrs);
    }

    /**
     * 注意调用默认的时候参数参数返回的是自定义的layoutparams
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
        int rw = MeasureSpec.getSize(widthMeasureSpec);
        int rh = MeasureSpec.getSize(heightMeasureSpec);

        //正方形
        childWidth = (rw - 2 * hSpace) / 3;
        childHeight = childWidth;

        int subCount = this.getChildCount();
        for (int i = 0; i < subCount; i++) {
            View child = getChildAt(i);
            //先测量在获取宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            com.hb.rimi.customviewdemo.widget.LayoutParams cParams = (com.hb.rimi.customviewdemo.widget.LayoutParams) child.getLayoutParams();
            cParams.left = (i % 3) * (childWidth + hSpace);
            cParams.top = (i / 3) * (childWidth + vSpace);
        }

        int vw = rw;
        int vh = rh;

        if (subCount < 3) {
            vw = subCount * (childWidth + hSpace);
        }
        vh = ((subCount + 3) / 3) * (childWidth + vSpace);
        setMeasuredDimension(vw, vh);
    }

    @Override
    protected void onLayout(boolean changed, int lc, int tc, int rc, int bc) {
        int subCount = getChildCount();
        for (int i = 0; i < subCount; i++) {
            View child = getChildAt(i);
            com.hb.rimi.customviewdemo.widget.LayoutParams layoutParams = (com.hb.rimi.customviewdemo.widget.LayoutParams) child.getLayoutParams();
            child.layout(layoutParams.left, layoutParams.top, layoutParams.left + childWidth, layoutParams.top + childHeight);
            if (i == mImageResArrayList.size() - 1 && mImageResArrayList.size() != MAX_PHOTO_NUMBER) {
                child.setBackgroundResource(R.mipmap.ic_launcher);
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addPhotoBtnClick();
                    }
                });
            } else {
                child.setBackgroundResource(constImageIds[i]);
                child.setOnClickListener(null);
            }
        }
    }

    private void addPhotoBtnClick() {
        final CharSequence[] items = {"take photo", "photo from gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addPhoto();
            }
        });
        builder.show();
    }

    private void addPhoto() {
        if (mImageResArrayList.size() < MAX_PHOTO_NUMBER) {
            View viewChild = new View(getContext());
            addView(viewChild);
            mImageResArrayList.add(new Integer(1));
            requestLayout();
            invalidate();
        }
    }
}
