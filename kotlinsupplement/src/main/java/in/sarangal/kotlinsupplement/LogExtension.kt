package `in`.sarangal.kotlinsupplement

import android.util.Log

/**
 * Print LOG Extension Functions
 * */

/**
 * LOG Types
 * */
enum class LogType {
    VERBOSE,
    WARNING,
    DEBUG,
    INFO,
    ERROR
}

/**
 * Print LOGs
 *
 * @param tag TAG for LOG
 * @param message To print in LOG
 * @param type Type of LOG
 * @param isDebug Pass BuildConfig.DEBUG
 * */
fun logger(tag: String = "", message: String?, type: LogType = LogType.VERBOSE, isDebug: Boolean = true) {

    if(!isDebug) return

    if(message == null) return

    when (type) {
        LogType.VERBOSE -> Log.v(tag, message)
        LogType.DEBUG -> Log.d(tag, message)
        LogType.WARNING -> Log.w(tag, message)
        LogType.INFO -> Log.i(tag, message)
        LogType.ERROR -> Log.e(tag, message)
    }
}