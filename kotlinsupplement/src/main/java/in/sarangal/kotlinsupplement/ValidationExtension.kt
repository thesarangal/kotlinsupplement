package `in`.sarangal.kotlinsupplement

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

/**
 * Validations Extensions
 * */

/**
 * @return TRUE if String is valid IFSC Code
 * */
fun String.isValidIFSC(): Boolean {
    val passwordREGEX = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$")
    return passwordREGEX.matcher(this).matches()
}

/**
 * @return TRUE if String is valid VPA (UPI)
 * */
fun String.isValidUPI(): Boolean {
    val regex = """^[a-zA-Z0-9.\-_]{2,256}@[a-zA-Z]{2,64}$""".toRegex()
    return regex.matches(this)
}

/**
 * @return TRUE if String is valid PAN Card
 * */
fun String.isValidPANCard(): Boolean {
    val regex = """^[A-Z]{5}[0-9]{4}[A-Z]{1}$""".toRegex()
    return regex.matches(this)
}

/**
 * @return TRUE if String have:
 *
 * at least 1 digit
 * at least 1 lower case letter
 * at least 1 upper case letter
 * any letter
 * at least 1 special character
 * no white spaces
 * at least 8 characters
 * */
fun String.isStrongPassword(): Boolean {
    val passwordREGEX = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +         // At least 1 Digit
                "(?=.*[a-z])" +         // At least 1 Lower Case Letter
                "(?=.*[A-Z])" +         // At least 1 Upper Case Letter
                "(?=.*[a-zA-Z])" +      // Any Letter
                "(?=.*[@#$%^&+=])" +    // At least 1 Special Character
                "(?=\\S+$)" +           // No White Spaces
                ".{8,}" +               // At least 8 Characters
                "$"
    )
    return passwordREGEX.matcher(this).matches()
}

/**
 * @return TRUE if String is valid Phone Number
 * */
fun isPhoneValid(phone: String): Boolean {
    return android.util.Patterns.PHONE.matcher(phone).matches()
}

/**
 * @return TRUE if String is valid Email Address structure
 * */
fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this.trim())
            && Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches()
}

/**
 * @return TRUE if String have character in between 8 and 15
 * */
fun String.isPasswordValid(): Boolean {
    return !TextUtils.isEmpty(this.trim())
            && (this.trim()).length >= 8 && (this.trim()).length <= 15
}

/**
 * @return TRUE if STRING is valid PACKAGE NAME
 * */
fun String.isValidPackageName(): Boolean {
    val VALID_PACKAGE_REG = "^([A-Za-z][A-Za-z\\d_]*\\.)+[A-Za-z][A-Za-z\\d_]*$"
    return Pattern.compile(VALID_PACKAGE_REG).matcher(this.trim()).matches()
}






