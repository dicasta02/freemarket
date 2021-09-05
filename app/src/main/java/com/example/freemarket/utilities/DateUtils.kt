package com.example.freemarket.utilities

import android.text.format.DateUtils.isToday
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    val currentDateFormat: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
            return dateFormat.format(Date())
        }

    val currentDateTimeFormat: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault())
            return dateFormat.format(Date())
        }

    @kotlin.jvm.JvmStatic
    val currentDateShortFormat: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat(Constants.DATE_FORMAT_SHORT, Locale.getDefault())
            return dateFormat.format(Date())
        }

    @kotlin.jvm.JvmStatic
    fun isToday(timeInMilliseconds: Long): Boolean {
        return DateUtils.isToday(timeInMilliseconds)
    }

    @kotlin.jvm.JvmStatic
    fun isFuture(timeInMilliseconds: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMilliseconds
        return calendar.time.after(Calendar.getInstance().time)
    }
}