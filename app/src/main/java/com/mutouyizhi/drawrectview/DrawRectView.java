package com.mutouyizhi.drawrectview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017-08-16.
 */

public class DrawRectView extends View {
    private MyRect mCurrentMyRect;
    private List<MyRect> mMyRects = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public DrawRectView(Context context) {
        super(context, null);
    }

    public DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);
// Paint the background off-white
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF pointF = new PointF(event.getX(), event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentMyRect = new MyRect(pointF);
                mMyRects.add(mCurrentMyRect);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentMyRect != null) {
                    mCurrentMyRect.setCurrent(pointF);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                mCurrentMyRect = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                mCurrentMyRect = null;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mBackgroundPaint);
        for (MyRect myRect : mMyRects) {
            float top = Math.min(myRect.getOrigin().y, myRect.getCurrent().y);
            float bottom = Math.max(myRect.getOrigin().y, myRect.getCurrent().y);
            float left = Math.min(myRect.getOrigin().x, myRect.getCurrent().x);
            float right = Math.max(myRect.getOrigin().x, myRect.getCurrent().x);
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }
}
