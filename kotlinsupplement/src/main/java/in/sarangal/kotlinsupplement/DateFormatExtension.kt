package `in`.sarangal.kotlinsupplement

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Some Date and Time Formats
 * */
object DateTimeFormat {

    /* 1993-12-06T05:15:30.600Z */
    const val MONOGO_DB_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    /* December 06, 1993 23:30 */
    const val MMMM_dd_yyyy_HH_mm = "MMMM dd, yyyy HH:mm"

    /* December 06, 1993 01:30 am */
    const val MMMM_dd_yyyy_hh_mm = "MMMM dd, yyyy hh:mm aa"

    /* December 06, 1993 */
    const val MMMM_dd_yyyy = "MMMM dd, yyyy"

    /* Dec 06, 1993 */
    const val MMM_dd_yyyy = "MMM dd, yyyy"

    /* Friday, December 06 */
    const val EEEE_MMMM_dd = "EEEE, MMMM dd"

    /* December 1993 */
    const val MMMM_yyyy = "MMMM yyyy"

    /* Dec 1993 */
    const val MMM_yyyy = "MMM yyyy"

    /* December 06 */
    const val MMMM_dd = "MMMM dd"

    /* Dec 06 */
    const val MMM_dd = "MMM dd"

    /* 1996 */
    const val yyyy = "yyyy"

    /* December */
    const val MMMM = "MMMM"

    /* Dec */
    const val MMM = "MMM"

    /* 06 */
    const val dd = "dd"

    /* 12:05 AM */
    const val hh_mm_aa = "hh:mm aa"

    /* 13:05 */
    const val HH_MM = "HH:mm"
}

/**
 * Convert TimeStamp to any format
 *
 * @param inputData Which you want to convert
 * @param isOldInUTC if old Time in UTC
 * @param mOldFormat Format of the inputData
 * @param shouldNewInUTC if want to convert output in UTC
 * @param mNewFormat Output data Format
 *
 * */
fun getDateByFormatCustomUTC(
    inputData: String,
    isOldInUTC: Boolean,
    mOldFormat: String,
    shouldNewInUTC: Boolean,
    mNewFormat: String
): String? {
    var mSimpleDateFormat = SimpleDateFormat(mOldFormat, Locale.US)

    /* Enable Following Statement when OldFormat is in UTC */
    if (isOldInUTC) {
        mSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    var mDate: Date? = null
    try {
        mDate = mSimpleDateFormat.parse(inputData)
    } catch (e: ParseException) {
        Log.d("DateFormatExtension", "getDateByFormatCustomUTC: ${e.message}")
    }

    if (mDate != null) {
        mSimpleDateFormat = SimpleDateFormat(mNewFormat, Locale.US)
        if (shouldNewInUTC) {
            mSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        } else {
            mSimpleDateFormat.timeZone = TimeZone.getDefault()
        }
        return mSimpleDateFormat.format(mDate)
    }
    return inputData
}

/**
 * Convert LONG time into FORMAT Date/Time String
 *
 * @param timestamp TimeStamp in LONG
 * @param isUTC TRUE if want to convert result into UTC converted
 * @param newFormat New Date/Time Format
 * */
fun getDateFormatFromLong(timestamp: Long, isUTC: Boolean, newFormat: String?): String {
    val mDate = Date(timestamp)
    val mSimpleDateFormat = SimpleDateFormat(newFormat, Locale.US)
    mSimpleDateFormat.timeZone = TimeZone.getDefault()
    if (isUTC) {
        mSimpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }
    return mSimpleDateFormat.format(mDate)
}