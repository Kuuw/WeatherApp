package dev.zuwu.weather.utils

import android.icu.text.DateFormat
import java.text.SimpleDateFormat

fun epochToHour(epoch: Long): String {
    val sdf = SimpleDateFormat("HH:00")

    return sdf.format(epoch * 1000)
}

fun epochToDay(epoch: Long): String {
    val df = DateFormat.getDateInstance(DateFormat.MEDIUM)

    return df.format(epoch * 1000)
}