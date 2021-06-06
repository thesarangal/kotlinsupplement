package `in`.sarangal.kotlinsupplement

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