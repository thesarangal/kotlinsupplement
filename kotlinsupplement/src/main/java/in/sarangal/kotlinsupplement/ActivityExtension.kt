package `in`.sarangal.kotlinsupplement

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import kotlin.system.exitProcess


/**
 * Activity Extension Function
 * */


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
fun Activity.restart(){
    finish()
    overridePendingTransition(0, 0)
    startActivity(intent)
    overridePendingTransition(0, 0)
}

/**
 * Restart Application
 * */
fun Activity.restartApplication(){
    val packageManager: PackageManager = packageManager
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    if(intent == null){
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
 * */
fun Activity.openDialerForCall(number: String) {
    val intent = Intent(Intent.ACTION_DIAL)

    /* Send phone number to intent as data */
    intent.data = Uri.parse("tel:$number")

    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        toast("You may not have a proper app for viewing this content")
    }
}

/**
 * Open Email
 *
 * @param email Email Address
 * */
fun Activity.openEmail(email: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse("mailto:$email")
    startActivity(emailIntent)

    try {
        startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        toast("You may not have a proper app for viewing this content")
    }
}

/**
 * Open URL in Browser
 *
 * @param urlString Web URL
 * */
fun Activity.openURLInBrowser(urlString: String) {
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
        toast("You may not have a proper app for viewing this content")
    }
}

/**
 * Share Plain Text via ShareIntent
 *
 * @param shareText Text To Share
 * */
fun Activity.shareTextMessage(shareText: String?) {
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
        toast("You may not have a proper app for viewing this content")
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
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        openURLInBrowser( "https://www.google.com/maps/?daddr=$latitude,$longitude")
    }
}

/**
 * Open Location in Google Map
 *
 * @param latitude Latitude Value
 * @param longitude Longitude Value
 * */
fun Activity.openLocationInGoogleMap(latitude: Double, longitude: Double) {
    val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($latitude,$longitude)")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        openURLInBrowser( "https://www.google.com/maps/?q=$latitude,$longitude")
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
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        openURLInBrowser( "https://www.google.com/maps/place/$address/")
    }
}