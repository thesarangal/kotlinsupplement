package `in`.sarangal.kotlinsupplement

import java.text.DecimalFormat
import java.util.*

/**
 * Format Values Extensions
 * */

/**
 * @return Integer from String
 * */
fun String.formatInt(): Int {
    var `val` = 0
    try {
        `val` = this.toInt()
    } catch (e: Exception) {
        try {
            `val` = Integer.valueOf(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return `val`
}

/**
 * @return Float from String
 * */
fun String.formatFloat(): Float {
    return try {
        trim().toFloat()
    } catch (e: Exception) {
        0f
    }
}

/**
 * @return Double from String
 * */
fun String.toDoubleFormat(): Double {

    return try {
        return toDouble()
    } catch (e: Exception) {
        0.0
    }
}

/**
 * @return String after Formatting Double Value
 * */
fun Double.formatDouble(): String {
    val df = DecimalFormat("#,###,###.##")
    return df.format(this)
}

/**
 * @return String after Formatting Double Value E.g 2.11 -> 2.1, 1.0 -> 1
 * */
fun Double.formatDoubleTrail(): String {
    val df = DecimalFormat("#,###,###.#")
    return df.format(this)
}

/**
 * Extension function to format a Double value to a decimal format with a specific number of decimal places.
 * @param upTo The number of decimal places to format the Double to. Default value is 2 if not provided.
 * @return A Double value formatted to the specified number of decimal places.
 */
fun Double.formatDecimal(upTo: Int? = 2) =
    String.format(Locale.US, "%.${upTo}f", this).toDouble()