package com.example.a7279.tulingapp;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a7279 on 2017/11/19.
 */
public class ActivitiyCollector {
    public static List<Activity> activities=new ArrayList<>();
    public  static void add(Activity activity)
    {
        activities.add(activity);
    }
    public  static  void  remove(Activity activity)
    {
        activities.remove(activity);
    }
    public static void finishAll()
    {
        for(Activity activity:activities)
        {
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
