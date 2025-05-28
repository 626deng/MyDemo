package com.example.premissionx.mydemo

import android.app.Activity

object ActivityCollector {

    val list=ArrayList<Activity>()

    fun addActivity(activity: Activity){
        list.add(activity)
    }

    fun removeActivity(activity: Activity)
    {
        list.remove(activity)
    }

    fun finishAllActivities()
    {
        for(activity in list)
        {
            if(!activity.isFinishing)
            {
                activity.finish()
            }
        }
        list.clear()
    }
}