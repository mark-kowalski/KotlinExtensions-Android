package dev.mko.kotlinsugar.model

/**
 * ViewModel to generate an android AlertDialog.
 *
 * @param header text which should be displayed as title
 * @param message text which should be display as message inside the dialog
 * @param positiveButtonText text which should be display for the positive button
 * @param negativeButtonText text which should be display for the negative button.
 *                           Optional parameter, if not given the dialog will have just the positive button.
 * @param onPositiveButtonClick callback which gets executed when the positive button was clicked
 */
data class AlertDialogViewModel(val header: Int,
                                val message: Int,
                                val positiveButtonText: Int,
                                val negativeButtonText: Int? = null,
                                val onPositiveButtonClick: () -> Unit)