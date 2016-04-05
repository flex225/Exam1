package com.yantur.exam1;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Artur on 4/5/2016.
 */
public class CustomView extends View {
    Bitmap backgroundBitmap;
    private List<Integer> colorRes;
    private List<MyCircle> circles;
    private float touchX = 0;
    private float touchY = 0;

    public CustomView(Context context, AttributeSet attrs) {
        super(context,attrs);
        circles = new ArrayList<>();
        colorRes = new ArrayList<>();
        colorRes.add(Color.BLACK);
        colorRes.add(Color.YELLOW);
        colorRes.add(Color.GRAY);
        colorRes.add(Color.GREEN);
        colorRes.add(Color.RED);
        colorRes.add(Color.BLUE);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (backgroundBitmap != null) {
            backgroundBitmap.setHeight(canvas.getHeight());
            backgroundBitmap.setWidth(canvas.getWidth());
            canvas.drawBitmap(backgroundBitmap, 0, 0, null);
        }
        for (int i = circles.size() - 1; i >= 0; i--) {
            canvas.drawCircle(circles.get(i).getX(), circles.get(i).getY(), circles.get(i).getRadius(), circles.get(i).getPaint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_UP:
                addCircle(event);
                break;
        }
        invalidate();
        return true;
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        backgroundBitmap = bitmap;
    }

    private void addCircle(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        Random random = new Random();
        int circleColorIndex = random.nextInt(colorRes.size());
        Paint paint = new Paint();
        paint.setColor(colorRes.get(circleColorIndex));

        int radius = (random.nextInt(9) + 1) * 10;
        MyCircle newCircle = new MyCircle(event.getX(index), event.getY(index),radius, paint);
        circles.add(id, newCircle);
    }
}
