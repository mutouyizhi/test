package com.mutouyizhi.drawrectview;

import android.graphics.PointF;

/**
 * Created by DELL on 2017-08-16.
 */

public class MyRect {
    private PointF mCurrent;
    private PointF mOrigin;

    public MyRect(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }
}
