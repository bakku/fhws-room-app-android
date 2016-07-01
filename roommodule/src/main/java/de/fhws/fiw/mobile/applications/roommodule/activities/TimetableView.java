package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import de.fhws.fiw.mobile.applications.roommodule.helper.DpPixelConverter;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 30.06.2016.
 */
public class TimetableView extends View {

    private Paint drawPaint;

    private Context context;

    private int sizeOfAnQuarterHourInDp;

    private int sizeOfFiveMinutesInDp;

    private Room room;

    private int h;

    public TimetableView(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);

        this.context = context;

        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        calculateSizeOfAnQuarterHourInDp();

        calculateSizeOfFiveMinutesInDp();

        setFocusable(true);

        setFocusableInTouchMode(true);

        setupPaint();
    }

    public TimetableView(Context context, AttributeSet attributeSet, Room room) {
        this(context, attributeSet);
        this.room = room;
    }

    private void calculateSizeOfAnQuarterHourInDp() {
        int sizeOfQuarterHourInDp = (int) (((TimetableConfig.TIMETABLE_DISTANCE_BETWEEN_TWO_LINES_IN_DP) /
                getResources().getDisplayMetrics().density) / 4);
        this.sizeOfAnQuarterHourInDp = sizeOfQuarterHourInDp;
    }

    private void calculateSizeOfFiveMinutesInDp() {
        int sizeOfFiveMinutesInDp = (int) (((TimetableConfig.TIMETABLE_DISTANCE_BETWEEN_TWO_LINES_IN_DP) /
                getResources().getDisplayMetrics().density) / 12);
        this.sizeOfFiveMinutesInDp = sizeOfFiveMinutesInDp;
    }

    private void setupPaint() {

        this.drawPaint = new Paint();
        this.drawPaint.setColor(Color.BLACK);
        this.drawPaint.setAntiAlias(true);
        this.drawPaint.setStrokeWidth(4);
        this.drawPaint.setStyle(Paint.Style.STROKE);
        this.drawPaint.setStrokeJoin(Paint.Join.ROUND);
        this.drawPaint.setStrokeCap(Paint.Cap.ROUND);
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTimeLines(canvas);
        drawTimeNumbers(canvas);
//        drawLectures();
//        drawLectureTitles();
//
//        canvas.drawCircle(50, 50, 20, drawPaint);
//        drawPaint.setColor(Color.GREEN);
//        canvas.drawCircle(50, 150, 20, drawPaint);
//        drawPaint.setColor(Color.BLUE);
//        canvas.drawCircle(50, 250, 20, drawPaint);
//        canvas.drawCircle(50, 800, 20, drawPaint);
//        canvas.drawCircle(50, 1200, 20, drawPaint);
//        canvas.drawCircle(50, 1500, 20, drawPaint);
//
//        drawPaint.setStyle(Paint.Style.FILL);
//        drawPaint.setColor(Color.MAGENTA);
//
//        drawPaint.setShadowLayer(10, 3, 3, Color.GRAY);
//
//        canvas.drawRect(new Rect(10, 10, 300, 300), drawPaint);
//        drawPaint.setColor(Color.BLACK);
//        drawPaint.setTextSize(30);
//        //50 ist linke untere ecke
//        canvas.drawText("blob", 40, 50, drawPaint);
    }

    private void drawTimeLines(Canvas canvas){

        preparePaintForTimeLines();

        int timetableBeginsAtHour = TimetableConfig.TIMETABLE_BEGINS_AT_HOUR;

        int timetableEndsAtHour = TimetableConfig.TIMETABLE_END_AT_HOUR;

        int yCurrent = TimetableConfig.TIMETABLE_MARGIN_TOP_IN_DP;

        while(timetableBeginsAtHour < timetableEndsAtHour){
            canvas.drawLine(
                    toPx(TimetableConfig.TIMETABLE_LEFT_MARGIN_IN_DP),
                    toPx(yCurrent),
                    toPx(getScreenWidthInDp() - TimetableConfig.TIMETABLE_RIGHT_MARGIN_IN_DP),
                    toPx(yCurrent),
                    this.drawPaint
            );

            timetableBeginsAtHour++;
            yCurrent += TimetableConfig.TIMETABLE_DISTANCE_BETWEEN_TWO_LINES_IN_DP;
        }

    }

    private void preparePaintForTimeLines(){
        this.drawPaint.setColor(Color.GRAY);
        this.drawPaint.setStrokeWidth(2);
        this.drawPaint.setStyle(Paint.Style.FILL);
    }

    private float getScreenWidthInDp() {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        return dpWidth;
    }

    private int toPx(float dpValue){
        return DpPixelConverter.dpToPixels(this.context, dpValue);
    }

    private void drawTimeNumbers(Canvas canvas){

        preparePaintForTimeNumbers();

        int timetableBeginsAtHour = TimetableConfig.TIMETABLE_BEGINS_AT_HOUR;

        int timetableEndsAtHour = TimetableConfig.TIMETABLE_END_AT_HOUR;

        String time;

        int yCurrent = TimetableConfig.TIMETABLE_MARGIN_TOP_IN_DP;

        for(int currentTime = timetableBeginsAtHour; currentTime < timetableEndsAtHour; currentTime++){

            time = currentTime + ":00";

            canvas.drawText(time,
                    toPx(TimetableConfig.TIMETABLE_LEFT_MARGIN_IN_DP),
                    toPx(yCurrent + TimetableConfig.TIMETABLE_TIME_NUMBERS_TEXT_SIZE_IN_DP),
                    this.drawPaint);

            yCurrent += TimetableConfig.TIMETABLE_DISTANCE_BETWEEN_TWO_LINES_IN_DP;
        }

    }

    private void preparePaintForTimeNumbers(){
        this.drawPaint.setColor(Color.GRAY);
        this.drawPaint.setTextSize(toPx(TimetableConfig.TIMETABLE_TIME_NUMBERS_TEXT_SIZE_IN_DP));
    }
}