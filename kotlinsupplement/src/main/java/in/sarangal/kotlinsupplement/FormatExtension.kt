package `in`.sarangal.kotlinsupplement

import java.text.DecimalFormat
import java.util.*

/**
 * Format Values Extensions
 * */

/**
 * @return Double from String
 * */
fun String.toDoubleFormat(): Double {

    try {
        return toDouble()
    } catch (e: Exception) {
    }

    return 0.0
}

/**
 * @return String after Formatting Double Value
 * */
fun Double.formatDouble(): String {
    val df = DecimalFormat("#,###,###.##")
    return df.format(this)
}

/**
 * @return String after Formatting Double Value upto N Decimal Number
 *
 * @param upTo Decimal Number
 * */
fun Double.formatDecimal(upTo: Int? = 2) =
    String.format(Locale.US, "%.${upTo}f", this).toDouble()