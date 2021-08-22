package github.sachin2dehury.owlmail.ui.utils

import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.snackbar.Snackbar

fun View.showToast(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun View.showSnackbar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()

fun View.hideKeyBoard(flags: Int = 0) =
    (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, flags)

fun View.showKeyBoard(flags: Int = 0) =
    (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(this, flags)

fun View.setSize(height: Int, width: Int) = layoutParams.let {
    it.height = height
    it.width = width
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun AppCompatImageView.setColorTint(@AttrRes colorAttr: Int) {
    setColorFilter(getThemeColor(colorAttr), android.graphics.PorterDuff.Mode.SRC_IN)
}

@ColorInt
fun View.getThemeColor(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue.data
}

@ColorInt
fun View.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    context.theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

@StyleRes
fun View.getStyleFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    context.theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}