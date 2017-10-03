package com.rosinante24.ojekonlineuserside.Fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by KOCHOR on 9/11/2017.
 */

public class Fonts {

    public static void RobotoLight(Context context, TextView tvData){
        Typeface Roboto = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_light.ttf");
        tvData.setTypeface(Roboto);
    }
    public static void RobotoThin(Context context, TextView tvData){
        Typeface Roboto = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_thin.ttf");
        tvData.setTypeface(Roboto);
    }
    public static void RobotoCondensedLight(Context context, TextView tvData){
        Typeface Roboto = Typeface.createFromAsset(context.getAssets(), "fonts/robotocondensed_light.ttf");
        tvData.setTypeface(Roboto);
    }
    public static void RobotoRegular(Context context, TextView tvData){
        Typeface Robotos = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf");
        tvData.setTypeface(Robotos);
    }
    public static void DeathNote(Context context, TextView tvData){
        Typeface Robotos = Typeface.createFromAsset(context.getAssets(), "fonts/death_note.ttf");
        tvData.setTypeface(Robotos);
    }
    public static void MontserratExtraLight(Context context, TextView tvData){
        Typeface Robotos = Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_extra_light.otf");
        tvData.setTypeface(Robotos);
    }
}
