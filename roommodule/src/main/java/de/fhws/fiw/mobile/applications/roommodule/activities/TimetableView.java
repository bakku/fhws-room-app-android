package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;

/**
 * Created by Patrick Müller on 30.06.2016.
 */
public class TimetableView extends View {

    // setup initial color
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;

    private Room room;

    private String s;

    public TimetableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    public TimetableView(Context context, AttributeSet attributeSet, Room room){
        this(context, attributeSet);
        this.room = room;
    }

    public void setText(String s){
        this.s = s;
    }

    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        setMeasuredDimension(screenWidth, screenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(50, 50, 20, drawPaint);
        drawPaint.setColor(Color.GREEN);
        canvas.drawCircle(50, 150, 20, drawPaint);
        drawPaint.setColor(Color.BLUE);
        canvas.drawCircle(50, 250, 20, drawPaint);
        canvas.drawCircle(50, 800, 20, drawPaint);
        canvas.drawCircle(50, 1200, 20, drawPaint);
        canvas.drawCircle(50, 1500, 20, drawPaint);

        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setColor(Color.MAGENTA);
        canvas.drawRect(new Rect(10, 10, 300, 300), drawPaint);
        drawPaint.setColor(Color.BLACK);
        drawPaint.setTextSize(30);
        //50 ist linke untere ecke
        canvas.drawText("blob", 40, 50, drawPaint);
    }
}
