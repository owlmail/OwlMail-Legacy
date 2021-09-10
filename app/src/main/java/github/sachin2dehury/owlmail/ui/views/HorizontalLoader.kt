package github.sachin2dehury.owlmail.ui.views

import android.content.Context
import android.util.AttributeSet
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import github.sachin2dehury.owlmail.R

class HorizontalLoader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LottieAnimationView(
    context,
    attrs, defStyleAttr
) {

    init {
        setAnimation(R.raw.horizontal_loader)
        repeatCount = LottieDrawable.INFINITE
        scaleType = ScaleType.FIT_CENTER
    }

    fun stopAnimation() {
        cancelAnimation()
        progress = 0F
    }
}
