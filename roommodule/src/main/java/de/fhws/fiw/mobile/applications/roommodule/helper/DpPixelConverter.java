package de.fhws.fiw.mobile.applications.roommodule.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Patrick Müller on 21.06.2016.
 */
public class DpPixelConverter {

    public static int dpToPixels(Context context, float dp){

        Resources r = context.getResources();

        float pixels =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());

        return (int) pixels;
    }
}