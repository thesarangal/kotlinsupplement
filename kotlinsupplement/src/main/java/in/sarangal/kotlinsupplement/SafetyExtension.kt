/*
 * Copyright The Sarangal.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package `in`.sarangal.kotlinsupplement

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * CharSequence & Collection Extensions
 * */

/**
 * @return TRUE if Text is not NULL and contains characters
 * */
@OptIn(ExperimentalContracts::class)
fun CharSequence?.isNotNullAndEmpty(): Boolean {

    contract {
        returns(true) implies (this@isNotNullAndEmpty != null)
    }

    return this != null && this.isNotEmpty()
}

/**
 * @return TRUE if Text is not NULL and contains characters after trim()
 * */
@Deprecated("Use isNotNullAndBlank()function", ReplaceWith("isNotNullAndBlank()"))
@OptIn(ExperimentalContracts::class)
fun CharSequence?.isNotNullAndEmptyTrim(): Boolean {

    contract {
        returns(true) implies (this@isNotNullAndEmptyTrim != null)
    }

    return this != null && this.trim().isNotEmpty()
}

/**
 * @return TRUE if Text is not NULL and contains characters after trim()
 * */
@OptIn(ExperimentalContracts::class)
fun CharSequence?.isNotNullAndBlank(): Boolean {

    contract {
        returns(true) implies (this@isNotNullAndBlank != null)
    }

    return this != null && this.isNotBlank()
}

/**
 * @return TRUE if Text contains characters after trim()
 * */
@Deprecated("Use isNotBlank()function", ReplaceWith("isNotBlank()"))
@OptIn(ExperimentalContracts::class)
fun CharSequence.isNotEmptyTrim(): Boolean {

    contract {
        returns(true)
    }

    return this.trim().isNotEmpty()
}

/**
 * @return TRUE if Array is not null and contains elements
 * */
@OptIn(ExperimentalContracts::class)
fun <T> Array<T>?.isNotNullAndEmpty(): Boolean {

    contract {
        returns(true) implies (this@isNotNullAndEmpty != null)
    }

    return this != null && this.isNotEmpty()
}

/**
 * @return TRUE if Collection is not null and contains elements
 * */
@OptIn(ExperimentalContracts::class)
fun <T> Collection<T>?.isNotNullAndEmpty(): Boolean {

    contract {
        returns(true) implies (this@isNotNullAndEmpty != null)
    }

    return this != null && this.isNotEmpty()
}

/**
 * @return callback if both Parameters are not null
 * */
inline fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
    if (value1 != null && value2 != null) {
        bothNotNull(value1, value2)
    }
}

/**
 * Inline function to check for the presence of null values among the provided objects, and execute a specified callback function
 * if any null values are found.
 * @param objects A variable number of objects to check for null values.
 * @param callback A function to execute if any null values are found.
 */
inline fun ifAnyNullExists(vararg objects: Any?, callback: () -> Unit) {
    if (objects.any { it == null }) {
        callback()
    }
}

/**
 * @return TRUE if Map is not null and contains elements
 * */
@SinceKotlin("1.3")
@OptIn(ExperimentalContracts::class)
fun <K, V> Map<out K, V>?.isNotNullAndEmpty(): Boolean {

    contract {
        returns(true) implies (this@isNotNullAndEmpty != null)
    }

    return this != null && this.isNotEmpty()
}