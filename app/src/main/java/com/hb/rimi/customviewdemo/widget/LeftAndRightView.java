package com.hb.rimi.customviewdemo.widget;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.hb.rimi.customviewdemo.R;

/**
 * Created by rimi on 2016/10/8.
 */
public class LeftAndRightView {
//    private boolean mShowText;
//    private final int mTextPos;
//    private Paint mTextPaint;
//    private Paint mPiePaint;
//    private Paint mShadowPaint;
//    private float mTextHeight;
//    private RectF mShadowBounds;
//
//
//    public LeftAndRightView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.LeftAndRightView,0,0);
//        try {
//            mShowText=a.getBoolean(R.styleable.LeftAndRightView_showText,false);
//            mTextPos=a.getInteger(R.styleable.LeftAndRightView_labelPosition,0);
//        }finally {
//            a.recycle();
//        }
//        init();
//    }
//
//    public boolean ismShowText() {
//        return mShowText;
//    }
//    public void setmShowText(boolean showText){
//        mShowText=showText;
//        invalidate();
//        requestLayout();
//    }
//
//    private void init(){
//        mTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setColor(0xff101010);
//        if(mTextHeight==0) {
//            mTextHeight = mTextPaint.getTextSize();
//        }else{
//            mTextPaint.setTextSize(mTextHeight);
//        }
//        mPiePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPiePaint.setStyle(Paint.Style.FILL);
//        mPiePaint.setTextSize(mTextHeight);
//
//        mShadowPaint=new Paint(Paint.HINTING_OFF);
//        mShadowPaint.setColor(0xff101010);
//        mShadowPaint.setMaskFilter(new BlurMaskFilter(8,BlurMaskFilter.Blur.NORMAL));
//    }
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawOval(mShadowBounds,mShadowPaint);
//
//        canvas.drawText(mData.get(mCurrentItem).mLabel,mTextX,mTextY,mTextPaint);
//        for (int i=0;i<mData.size();i++){
//            Item it=mData.get(i);
//            mPiePaint.setShader(it.mShader);
//            canvas.draArc(mBounds,360-it.mEndAngle-it.mStartAngle,true,mPiePaint);
//        }
//        canvas.drawLine(mTextX,mPointerY,mPointerX,mPointerY,mTextPaint);
//        canvas.drawCircle(mPointerX,mPointerSize,mTextPaint);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        boolean result=mDetector.onTouchEvent(event);
//        if(!result){
//            if(event.getAction()==MotionEvent.ACTION_UP){
//                stopScrolling();
//                result=true;
//            }
//        }
//        return result;
//    }
//    class mListener extends GestureDetector.SimpleOnGestureListener{
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//    }
//    GestureDetector mDetector=new GestureDetector(LeftAndRightView.this.getContext(),new mListener());

}
