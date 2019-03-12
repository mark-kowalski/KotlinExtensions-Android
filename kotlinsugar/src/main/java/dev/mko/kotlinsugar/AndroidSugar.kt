package dev.mko.kotlinsugar

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Starts an activity from the given class, which must extend [Activity].
 * Optionally you can pass flags and extras, which gets attached to the activity.
 *
 * Think about if you have to finish your calling activity. You have to do this on your own.
 */
inline fun <reified T: Activity> Context.startActivity(flags: List<Int>? = null,
                                                       stringExtras: Map<String, String>? = null,
                                                       booleanExtras: Map<String, Boolean>? = null) {
    val intent = Intent(this, T::class.java)

    flags?.forEach { intent.addFlags(it) }
    stringExtras?.forEach { intent.putExtra(it.key, it.value) }
    booleanExtras?.forEach { intent.putExtra(it.key, it.value) }

    this.startActivity(intent)
}