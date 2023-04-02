package `in`.sarangal.kotlinsupplement

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.location.LocationManager
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Context Extensions
 * */

/**
 * Copy String Data to Clipboard
 *
 * @param value The string which you want to copy
 * @param messageShow To show toast message. Pass Null or Nothing to ignore Toast Message
 * */
fun Context.copyTextToClipboard(value: String, messageShow: String? = null) {

    if (messageShow != null) {
        toast(
            if (messageShow.isNotBlank()) {
                messageShow
            } else {
                "Copied to clipboard"
            }
        )
    }

    val clipboard: ClipboardManager? =
        this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText("", value)
    clipboard?.setPrimaryClip(clip)
}

/**
 * Show Toast Message
 *
 * @param message To show toast message.
 * @param isLongLength TRUE if Toast.LENGTH_LONG else FALSE
 * */
fun Context.toast(message: String, isLongLength: Boolean = false) {
    Toast.makeText(
        this, message, if (isLongLength) {
            Toast.LENGTH_LONG
        } else {
            Toast.LENGTH_SHORT
        }
    ).show()
}

/**
 * Show Toast Message
 *
 * @param message String Resource ID.
 * @param isLongLength TRUE if Toast.LENGTH_LONG else FALSE
 * */
fun Context.toast(@StringRes message: Int, isLongLength: Boolean = false) {
    Toast.makeText(
        this, message, if (isLongLength) {
            Toast.LENGTH_LONG
        } else {
            Toast.LENGTH_SHORT
        }
    ).show()
}

/**
 * Check is GPS Enabled
 * */
fun Context.isGPSEnabled(): Boolean {
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    locationManager?.let {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
    return false
}

/**
 * Check is Network Location Enabled
 * */
fun Context.isLocationNetworkProvider(): Boolean {
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    locationManager?.let {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
    return false
}