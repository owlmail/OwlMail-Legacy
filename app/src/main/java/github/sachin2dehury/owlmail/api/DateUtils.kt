package github.sachin2dehury.owlmail.api

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import github.sachin2dehury.owlmail.R
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
fun getDateFormat(time: Long, context: Context) = when {
    (System.currentTimeMillis() - time) < DateUtils.DAY_IN_MILLIS -> context.getString(R.string.day_format)
    (System.currentTimeMillis() - time) < DateUtils.YEAR_IN_MILLIS -> context.getString(R.string.month_format)
    else -> context.getString(R.string.year_format)
}


@SuppressLint("SimpleDateFormat")
fun getMonth(page: Int, context: Context) =
    SimpleDateFormat(context.getString(R.string.zimbra_month_format)).format(getCalender(page).time)

fun getCalender(page: Int) = Calendar.getInstance().apply {
    add(Calendar.MONTH, -page)
    val firstDay = getActualMinimum(Calendar.DAY_OF_MONTH)
    set(Calendar.DAY_OF_MONTH, firstDay)
}