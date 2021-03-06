package dev.mko.kotlinsugar

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.mko.kotlinsugar.model.AlertDialogViewModel

/**
 * Starts an activity from the given class, which must extend [Activity].
 * Optionally you can pass flags and extras, which gets attached to the activity.
 *
 * Think about if you have to finish the calling activity. You have to do this on your own.
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

/**
 * Replace the current with the given fragment on the activity.
 */
fun AppCompatActivity.replaceFragment(@IdRes resourceId: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().replace(resourceId, fragment).commit()
}

/**
 * Iterate through fragments attached to FragmentManager and pop's one after the other.
 */
fun AppCompatActivity.popWholeBackStack() {
    val fragmentManager = this.supportFragmentManager
    for (i in 0 until fragmentManager.fragments.size) {
        fragmentManager.popBackStack()
    }
}

/**
 * Generates an android AlertDialog from the given ViewModel.
 * onPositiveButtonClick will execute the callback passed with the ViewModel.
 * onNegativeButtonClick simply will dismiss the dialog.
 *
 * If [AlertDialogViewModel.negativeButtonText] is null the dialog will be
 * created with just the positive button.
 */
fun Activity.showAlertDialogFromViewModel(viewModel: AlertDialogViewModel) {
    val alertDialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        .setTitle(viewModel.header)
        .setMessage(viewModel.message)
        .setPositiveButton(viewModel.positiveButtonText) { dialog, _ ->
            viewModel.onPositiveButtonClick; dialog.dismiss()
        }

    viewModel.negativeButtonText?.let {
        alertDialogBuilder.setNegativeButton(it) { dialog, _ ->
            dialog.dismiss()
        }
    }

    alertDialogBuilder.create().show()
}