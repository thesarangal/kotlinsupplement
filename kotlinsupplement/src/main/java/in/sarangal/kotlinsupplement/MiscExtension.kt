package `in`.sarangal.kotlinsupplement

import android.graphics.Color
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

/**
 * @return Mixed Color from Two Colors
 *
 * @param color1 First Color
 * @param color2 Second Color
 * @param amount Mixing Amount
 * */
fun mixTwoColors(color1: Int, color2: Int, amount: Float = 0.5f): Int {
    val ALPHA_CHANNEL: Byte = 24
    val RED_CHANNEL: Byte = 16
    val GREEN_CHANNEL: Byte = 8
    val BLUE_CHANNEL: Byte = 0
    val inverseAmount = 1.0f - amount
    val a = ((color1 shr ALPHA_CHANNEL.toInt() and 0xff).toFloat() * amount +
            (color2 shr ALPHA_CHANNEL.toInt() and 0xff).toFloat() * inverseAmount).toInt() and 0xff
    val r = ((color1 shr RED_CHANNEL.toInt() and 0xff).toFloat() * amount +
            (color2 shr RED_CHANNEL.toInt() and 0xff).toFloat() * inverseAmount).toInt() and 0xff
    val g = ((color1 shr GREEN_CHANNEL.toInt() and 0xff).toFloat() * amount +
            (color2 shr GREEN_CHANNEL.toInt() and 0xff).toFloat() * inverseAmount).toInt() and 0xff
    val b = ((color1 and 0xff).toFloat() * amount +
            (color2 and 0xff).toFloat() * inverseAmount).toInt() and 0xff
    return a shl ALPHA_CHANNEL.toInt() or
            (r shl RED_CHANNEL.toInt()) or
            (g shl GREEN_CHANNEL.toInt()) or
            (b shl BLUE_CHANNEL.toInt())
}

/** Check COLOR is DARK or LIGHT */
fun Int.isColorDark(): Boolean =
    (Color.red(this) * 0.299
            + Color.green(this) * 0.587
            + Color.blue(this) * 0.114) <= 187 //186

/**
 * Request to Navigate if Navigation Graph is Available
 *
 * @param id    ID of Action of Navigation
 * @param args  Bundle
 * */
fun NavController.navigateSafely(@IdRes id: Int, args: Bundle? = null) {
    currentDestination?.getAction(id)?.run { navigate(id, args) }
}