package com.devikiran.assignments.utils

import android.content.Context
import androidx.window.layout.WindowMetricsCalculator

object Utils {

    const val BASE_URL = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/dev/"
    const val TAG = "AssignmentsLogError"


    fun isTablet(context: Context): Boolean {
        val windowMetrics =
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context)
        val screenWidthDp = windowMetrics.bounds.width() / context.resources.displayMetrics.density
        val isTablet = screenWidthDp > 600
        return isTablet
    }
}