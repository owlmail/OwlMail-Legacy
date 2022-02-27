package github.sachin2dehury.owlmail.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import github.sachin2dehury.owlmail.R
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(time: Long, context: Context): String = when {
    (System.currentTimeMillis() - time) < DateUtils.DAY_IN_MILLIS -> SimpleDateFormat(
        context.getString(
            R.string.day_format
        )
    ).format(time)
    else -> SimpleDateFormat(context.getString(R.string.month_format)).format(time)
}

fun getCalender(page: Int): Calendar = Calendar.getInstance().apply {
    add(Calendar.MONTH, -page)
    set(Calendar.DAY_OF_MONTH, 1)
    set(Calendar.HOUR_OF_DAY, 0)
}

@SuppressLint("SimpleDateFormat")
fun getFormattedHeaderDate(page: Int, context: Context): String =
    SimpleDateFormat(context.getString(R.string.year_format)).format(getCalender(page))
