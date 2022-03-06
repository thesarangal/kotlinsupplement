package `in`.sarangal.kotlinsupplement

import android.app.Activity
import android.app.ActivityManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.WindowInsetsController
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import kotlin.system.exitProcess


/**
 * Activity Extension Function
 * */

// Static Data Members
const val GOOGLE_MAP_PACKAGE = "com.google.android.apps.maps"

// Exception Message
const val ACTIVITY_NOT_FOUND_EXCEPTION = "You may not have a proper app for viewing this content"

/**
 * Set Background of Activity Theme
 *
 * @param resId Resource ID
 * */
fun Activity.setWindowBackground(@DrawableRes resId: Int? = null) = run {
    this.window?.let {
        if (resId == null)
            it.setBackgroundDrawable(null)
        else
            it.setBackgroundDrawableResource(resId)
    }
}

/**
 * Hide Keyboard
 * */
fun Activity.hideKeyboard() {
    val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE)
            as InputMethodManager

    /* Find the currently focused view,
     * so we can grab the correct window token from it. */
    var view = currentFocus

    /* If no view currently has focus, create a new one,
     * just so we can grab a window token from it */
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Finish Activity by Killing All Running Process
 * */
fun Activity.killTheApp() {
    moveTaskToBack(true)
    exitProcess(-1)
}

/**
 * Restart Activity Without Animation
 * */
fun Activity.restart() {
    finish()
    overridePendingTransition(0, 0)
    startActivity(intent)
    overridePendingTransition(0, 0)
}

/**
 * Restart Application
 * */
fun Activity.restartApplication() {
    val packageManager: PackageManager = packageManager
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    if (intent == null) {
        restart()
        return
    }
    val componentName = intent.component
    val mainIntent = Intent.makeRestartActivityTask(componentName)
    startActivity(mainIntent)
    overridePendingTransition(0, 0)
    Runtime.getRuntime().exit(0)
}

/**
 * Open Dialer for Call
 *
 * @param number Phone Number
 * @param exceptionMsg Custom Message for Exception
 * */
fun Activity.openDialerForCall(
    number: String,
    exceptionMsg: String = ACTIVITY_NOT_FOUND_EXCEPTION
) {
    val intent = Intent(Intent.ACTION_DIAL)

    /* Send phone number to intent as data */
    intent.data = Uri.parse("tel:$number")

    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        toast(exceptionMsg)
    }
}

/**
 * Open Email
 *
 * @param email Email Address
 * @param exceptionMsg Custom Message for Exception
 * */
fun Activity.openEmail(
    email: String,
    exceptionMsg: String = ACTIVITY_NOT_FOUND_EXCEPTION
) {
    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse("mailto:$email")
    startActivity(emailIntent)

    try {
        startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        toast(exceptionMsg)
    }
}

/**
 * Open URL in Browser
 *
 * @param urlString Web URL
 * @param exceptionMsg Custom Message for Exception
 * */
fun Activity.openURLInBrowser(
    urlString: String,
    exceptionMsg: String = ACTIVITY_NOT_FOUND_EXCEPTION
) {
    var url = urlString

    if (!url.startsWith("www.") && !url.startsWith("http")) {
        url = "http://$url"
    }

    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        toast(exceptionMsg)
    }
}

/**
 * Share Plain Text via ShareIntent
 *
 * @param shareText Text To Share
 * @param exceptionMsg Custom Message for Exception
 * */
fun Activity.shareTextMessage(
    shareText: String?,
    exceptionMsg: String = ACTIVITY_NOT_FOUND_EXCEPTION
) {
    val intent2 = Intent()
    intent2.action = Intent.ACTION_SEND
    intent2.type = "text/plain"
    intent2.putExtra(Intent.EXTRA_TEXT, shareText)

    val chooser: Intent = Intent.createChooser(
        intent2,
        "Share Via"
    )

    if (intent2.resolveActivity(packageManager) != null) {
        startActivity(chooser)
    } else {
        toast(exceptionMsg)
    }
}

/**
 * Open Location in Google Map
 *
 * @param latitude Latitude Value
 * @param longitude Longitude Value
 * */
fun Activity.openDirectionInGoogleMap(latitude: Double, longitude: Double) {
    val gmmIntentUri = Uri.parse("http://maps.google.com/maps?daddr=$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage(GOOGLE_MAP_PACKAGE)
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        openURLInBrowser("https://www.google.com/maps/?daddr=$latitude,$longitude")
    }
}

/**
 * Open Location in Google Map
 *
 * @param latitude Latitude Value
 * @param longitude Longitude Value
 * */
fun Activity.openLocationInGoogleMap(latitude: Double, longitude: Double) {
    val gmmIntentUri =
        Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($latitude,$longitude)")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage(GOOGLE_MAP_PACKAGE)
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        openURLInBrowser("https://www.google.com/maps/?q=$latitude,$longitude")
    }
}

/**
 * Open Address in Google Map
 *
 * @param address Address in String format
 * */
fun Activity.openAddressInGoogleMap(address: String) {
    val gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=$address")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage(GOOGLE_MAP_PACKAGE)
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        openURLInBrowser("https://www.google.com/maps/place/$address/")
    }
}

/**
 * Set System Bar Color (StatusBar)
 *
 * @param statusBarColor For StatusBar Color
 * @param view Parent View of Activity
 *
 * Makes status bars become opaque with solid dark background and light foreground.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.setStatusBarColor(statusBarColor: Int, view: View? = null) {
    window.statusBarColor = statusBarColor

    val isColorDark = statusBarColor.isColorDark()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(
            if (isColorDark) 0
            else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
        return
    }

    @Suppress("DEPRECATION")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        (view ?: window.decorView).systemUiVisibility =
            if (isColorDark) 0 else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

/**
 * Set System Bar Color (NavigationBar)
 *
 * @param navigationBarColor For System NavigationBar Color
 * @param view Parent View of Activity
 *
 * Makes status bars become opaque with solid dark background and light foreground.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.setNavigationBarColor(navigationBarColor: Int, view: View? = null) {
    window.navigationBarColor = navigationBarColor

    val isColorDark = navigationBarColor.isColorDark()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(
            if (isColorDark) 0
            else WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        )
        return
    }

    @Suppress("DEPRECATION")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        (view ?: window.decorView).apply {
            systemUiVisibility = if (isColorDark) 0
            else View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }
}

/**
 * Set System Bar Color (StatusBar & NavigationBar)
 *
 * @param statusBarColor For StatusBar Color
 * @param navigationBarColor For System NavigationBar Color
 * @param view Parent View of Activity
 *
 * Makes status bars become opaque with solid dark background and light foreground.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.setSystemBarColor(
    statusBarColor: Int,
    navigationBarColor: Int,
    view: View? = null
) {
    window.statusBarColor = statusBarColor
    window.navigationBarColor = navigationBarColor

    val isColorDarkStatusBar = statusBarColor.isColorDark()
    val isColorDarkNavigationBar = navigationBarColor.isColorDark()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(

            when {
                isColorDarkStatusBar && isColorDarkNavigationBar -> {
                    0
                }

                isColorDarkStatusBar -> {
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                }

                isColorDarkNavigationBar -> {
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                }

                else -> {
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                }
            },
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        )
        return
    }

    @Suppress("DEPRECATION")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        (view ?: window.decorView).systemUiVisibility = when {
            isColorDarkStatusBar && isColorDarkNavigationBar -> {
                0
            }

            isColorDarkStatusBar -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    0
                }
            }

            isColorDarkNavigationBar -> {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
        }
    }
}

/**
 * @return TRUE if Server is running else FALSE
 * */
fun Activity.isServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
    if (manager != null) for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

/**
 * Open Application Setting Screen with Package Name
 *
 * @param applicationId Pass Package Name (BuildConfig.APPLICATION_ID)
 * @param exceptionMsg Custom Message for Exception
 * */
fun Activity.openAppSettings(
    applicationId: String,
    exceptionMsg: String = ACTIVITY_NOT_FOUND_EXCEPTION
) {

    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", applicationId.trim(), null)
    intent.data = uri
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        toast(exceptionMsg)
    }
}