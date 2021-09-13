package `in`.sarangal.kotlinsupplement

import android.graphics.Color
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * String Extensions
 * */

/**
 * @return String in Uppercase
 * */
fun String.upperCase() = this.uppercase(Locale.getDefault())

/**
 * @return String in Lowercase
 * */
fun String.lowerCase() = this.lowercase(Locale.getDefault())

/**
 * @return String by Capital first character
 *
 * @param locale Locale
 * */
fun String.capitalize(locale: Locale ?= null) = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(locale ?: Locale.getDefault()) else it.toString()
}

/**
 * @return String by Capital first character
 * */
fun String.capitalizeSentence() = this.capitalize(Locale.getDefault())

/**
 * @return String by Capital first character of each word
 * */
fun String.capitalizeFirstLetter() = this.split(" ").joinToString(" ")
{ it.capitalize(Locale.getDefault()) }.trimEnd()

/**
 * @return String by Capital only first character of each word (other will convert to lowercase)
 * */
fun String.capitalizeFirstLetterOnly() = this.split(" ").joinToString(" ")
{ it.lowerCase().capitalize(Locale.getDefault()) }.trimEnd()

/**
 * @return String by replacing UNDERSCORE with SPACE and Capital first character of each word
 * */
fun String.formatSpaceUnderScore() = this.replace("_", " ").capitalizeFirstLetterOnly()

/**
 * @return Last Characters of String
 *
 * @param count Number of Last Characters
 * */
fun String.getLastCharacters(count: Int) =
    if (this.isNotEmpty()) {
        if (this.length <= count) {
            this
        } else {
            this.substring(this.length - count)
        }
    } else ""

/**
 * @return Parse String Color to Int
 * */
fun String.parseColor(): Int = try {
    Color.parseColor(this)
} catch (e: Exception) {
    if (this.isNotEmpty())
        logger(javaClass.simpleName, "parseColor: ${e.message}", LogType.ERROR)
    Color.DKGRAY
}

/**
 * Reset StringBuilder Value
 * */
fun StringBuilder.setValue(value: String) {
    this.clear()
    this.append(value)
}

/**
 * Convert CurrencyCode to Symbol
 * Note: INR will return INR
 *
 * @param "USD"
 *
 * @return "$"
 * */
fun String.getCurrencySymbol(): String {
    val currencyLocaleMap: SortedMap<Currency?, Locale?> = TreeMap { c1, c2 ->
        c1?.currencyCode?.compareTo(
            c2?.currencyCode ?: ""
        ) ?: 0
    }
    for (locale in Locale.getAvailableLocales()) {
        try {
            val currency = Currency.getInstance(locale)
            currencyLocaleMap[currency] = locale
        } catch (e: java.lang.Exception) {
            logger(javaClass.simpleName, "getCurrencySymbol: ${e.message}", LogType.ERROR)
        }
    }

    val currency = Currency.getInstance(this.trim())
    logger(
        javaClass.simpleName,
        this.trim() + ":-" + currency.getSymbol(currencyLocaleMap[currency])
    )
    return currency.getSymbol(currencyLocaleMap[currency])
}

/**
 * @return TRUE if String contains any Hindi Character else FALSE
 * */
fun String.isContainHindi(): Boolean {

    var isHindi = false
    for (c in toCharArray()) {
        if (Character.UnicodeBlock.of(c) === Character.UnicodeBlock.DEVANAGARI) {
            isHindi = true
            break
        }
    }
    return isHindi
}

/**
 * @return TRUE if String contains any English Character else FALSE
 * */
fun String.isContainEnglish(): Boolean {

    var isEnglish = false
    for (c in toCharArray()) {
        if (Character.isLetter(c)) {
            isEnglish = true
            break
        }
    }
    return isEnglish
}

/**
 * @return TRUE if String contains any Alphabet or Number else FALSE
 * */
fun String.haveAnyAlphabetNumber(): Boolean {
    val pattern = Pattern.compile("[a-zA-Z0-9]")
    val matcher: Matcher = pattern.matcher(this)
    return matcher.find()
}

/**
 * @return TRUE if STRING is contain any number
 * */
fun String.isContainNumber(): Boolean {
    val CONTAIN_NUMBER = ".*\\d.*"
    return Pattern.compile(CONTAIN_NUMBER).matcher(this.trim()).matches()
}