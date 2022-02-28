package github.sachin2dehury.owlmail.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.common.Email
import github.sachin2dehury.owlmail.data.constants.ZimbraEmail
import github.sachin2dehury.owlmail.data.searchgal.Attrs
import java.text.SimpleDateFormat

fun Email.getName() = fullName ?: firstName

fun List<Email>.getCCs() = filter { it.type == ZimbraEmail.CC.value }
fun List<Email>.getBCCs() = filter { it.type == ZimbraEmail.BCC.value }
fun List<Email>.getFrom() = firstOrNull { it.type == ZimbraEmail.FROM.value }
fun List<Email>.getTo() = filter { it.type == ZimbraEmail.TO.value }

fun String.getFirstCharacter() = first().toString()
fun String.isStared() = contains('f', true)
fun String.hasAttachments() = contains('a', true)

@SuppressLint("SimpleDateFormat")
fun Long.getFormattedDate(context: Context): String = when {
    (System.currentTimeMillis() - this) < DateUtils.DAY_IN_MILLIS -> SimpleDateFormat(
        context.getString(
            R.string.day_format
        )
    ).format(this)
    else -> SimpleDateFormat(context.getString(R.string.month_format)).format(this)
}

fun Attrs.getName() = fullName ?: firstName
