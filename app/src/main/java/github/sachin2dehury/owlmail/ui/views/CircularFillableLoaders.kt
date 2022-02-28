package github.sachin2dehury.owlmail.ui.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageView
import github.sachin2dehury.owlmail.R
import timber.log.Timber
import kotlin.math.roundToInt
import kotlin.math.sin

class CircularFillableLoaders @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {
    // Dynamic Properties
    private var canvasSize = 0
    private var amplitudeRatio = 0f
    private var waveColor = 0

    // Properties
    private var waterLevelRatio = 1f
    private var waveShiftRatio: Float = DEFAULT_WAVE_SHIFT_RATIO
    private var defaultWaterLevel = 0f

    // Object used to draw
    private var image: Bitmap? = null
    private var mDrawable: Drawable? = null
    private var paint: Paint? = null
    private var borderPaint: Paint? = null
    private var wavePaint: Paint? = null
    private var waveShader: BitmapShader? = null
    private var waveShaderMatrix: Matrix? = null

    // Animation
    private var animatorSetWave: AnimatorSet? = null
    private var firstLoadBitmap = true
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        // Init paint
        paint = Paint()
        paint!!.isAntiAlias = true

        // Init Wave
        waveShaderMatrix = Matrix()
        wavePaint = Paint()
        wavePaint!!.isAntiAlias = true

        // Init Border
        borderPaint = Paint()
        borderPaint!!.isAntiAlias = true
        borderPaint!!.style = Paint.Style.STROKE

        // Init Animation
        initAnimation()

        // Load the styled attributes and set their properties
        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.CircularFillableLoaders,
            defStyleAttr,
            0
        )

        // Init Wave
        waveColor = attributes.getColor(
            R.styleable.CircularFillableLoaders_wave_color,
            DEFAULT_WAVE_COLOR
        )
        val amplitudeRatioAttr = attributes.getFloat(
            R.styleable.CircularFillableLoaders_wave_amplitude,
            DEFAULT_AMPLITUDE_RATIO
        )
        amplitudeRatio =
            if (amplitudeRatioAttr > DEFAULT_AMPLITUDE_RATIO) DEFAULT_AMPLITUDE_RATIO else amplitudeRatioAttr
        setProgress(attributes.getInteger(R.styleable.CircularFillableLoaders_progress, 0))
        if (attributes.getBoolean(R.styleable.CircularFillableLoaders_border, true)) {
            val defaultBorderSize: Float =
                DEFAULT_BORDER_WIDTH * getContext().resources.displayMetrics.density
            borderPaint!!.strokeWidth = attributes.getDimension(
                R.styleable.CircularFillableLoaders_border_width,
                defaultBorderSize
            )
        } else {
            borderPaint!!.strokeWidth = 0f
        }
        attributes.recycle()
    }

    //endregion
    //region Draw Method
    public override fun onDraw(canvas: Canvas) {
        // Load the bitmap
        loadBitmap()

        // Check if image isn't null
        if (image == null) return
        if (!isInEditMode) {
            canvasSize = width
            if (height < canvasSize) {
                canvasSize = height
            }
        }

        // Draw Image Circular
        val circleCenter = canvasSize / 2
        canvas.drawCircle(
            circleCenter.toFloat(),
            circleCenter.toFloat(),
            circleCenter - borderPaint!!.strokeWidth,
            paint!!
        )

        // Draw Wave
        // modify paint shader according to mShowWave state
        if (waveShader != null) {
            // first call after mShowWave, assign it to our paint
            if (wavePaint!!.shader == null) {
                wavePaint!!.shader = waveShader
            }

            // sacle shader according to waveLengthRatio and amplitudeRatio
            // this decides the size(waveLengthRatio for width, amplitudeRatio for height) of waves
            waveShaderMatrix!!.setScale(
                1f,
                amplitudeRatio / DEFAULT_AMPLITUDE_RATIO,
                0f,
                defaultWaterLevel
            )
            // translate shader according to waveShiftRatio and waterLevelRatio
            // this decides the start position(waveShiftRatio for x, waterLevelRatio for y) of waves
            val width = width
            val height = height
            waveShaderMatrix!!.postTranslate(
                waveShiftRatio * width,
                (DEFAULT_WATER_LEVEL_RATIO - waterLevelRatio) * height
            )

            // assign matrix to invalidate the shader
            waveShader!!.setLocalMatrix(waveShaderMatrix)

            // Draw Border
            borderPaint!!.color = waveColor
            val borderWidth = borderPaint!!.strokeWidth
            if (borderWidth > 0) {
                canvas.drawCircle(
                    width / 2f, height / 2f, (width - borderWidth) / 2f - 1f,
                    borderPaint!!
                )
            }

            // Draw Wave
            val radius = width / 2f - borderWidth
            canvas.drawCircle(width / 2f, height / 2f, radius, wavePaint!!)
        } else {
            wavePaint!!.shader = null
        }
    }

    private fun loadBitmap() {
        if (mDrawable === drawable && !firstLoadBitmap) return
        mDrawable = drawable
        image = drawableToBitmap(mDrawable)
        firstLoadBitmap = false
        updateShader()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasSize = w
        if (h < canvasSize) canvasSize = h
        if (image != null) updateShader()
    }

    private fun updateShader() {
        if (image == null) return

        // Crop Center Image
        image = cropBitmap(image!!)

        // Create Shader
        val shader = BitmapShader(image!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        // Center Image in Shader
        val matrix = Matrix()
        matrix.setScale(
            canvasSize.toFloat() / image!!.width.toFloat(),
            canvasSize.toFloat() / image!!.height
                .toFloat()
        )
        shader.setLocalMatrix(matrix)

        // Set Shader in Paint
        paint!!.shader = shader

        // Update Wave Shader
        updateWaveShader()
    }

    private fun updateWaveShader() {
        val width = width
        val height = height
        val defaultAngularFrequency: Double =
            2.0f * Math.PI / DEFAULT_WAVE_LENGTH_RATIO / width
        val defaultAmplitude: Float =
            height * DEFAULT_AMPLITUDE_RATIO
        defaultWaterLevel = height * DEFAULT_WATER_LEVEL_RATIO
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val wavePaint = Paint()
        wavePaint.strokeWidth = 2f
        wavePaint.isAntiAlias = true

        // Draw default waves into the bitmap
        // y=Asin(ωx+φ)+h
        val endX = width + 1
        val endY = height + 1
        val waveY = FloatArray(endX)
        wavePaint.color = adjustAlpha(waveColor, 0.3f)
        for (beginX in 0 until endX) {
            val wx = beginX * defaultAngularFrequency
            val beginY = (defaultWaterLevel + defaultAmplitude * sin(wx)).toFloat()
            canvas.drawLine(beginX.toFloat(), beginY, beginX.toFloat(), endY.toFloat(), wavePaint)
            waveY[beginX] = beginY
        }
        wavePaint.color = waveColor
        val wave2Shift = width / 4
        for (beginX in 0 until endX) {
            canvas.drawLine(
                beginX.toFloat(),
                waveY[(beginX + wave2Shift) % endX], beginX.toFloat(), endY.toFloat(), wavePaint
            )
        }

        // use the bitamp to create the shader
        waveShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP)
        this.wavePaint!!.shader = waveShader
    }

    private fun cropBitmap(bitmap: Bitmap) = if (bitmap.width >= bitmap.height) {
        Bitmap.createBitmap(
            bitmap,
            bitmap.width / 2 - bitmap.height / 2,
            0,
            bitmap.height, bitmap.height
        )
    } else {
        Bitmap.createBitmap(
            bitmap,
            0,
            bitmap.height / 2 - bitmap.width / 2,
            bitmap.width, bitmap.width
        )
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val intrinsicWidth: Int
        val intrinsicHeight: Int
        if (drawable == null) {
            intrinsicWidth = width
            intrinsicHeight = height
        } else {
            intrinsicWidth = drawable.intrinsicWidth
            intrinsicHeight = drawable.intrinsicHeight
        }
        return if (!(intrinsicWidth > 0 && intrinsicHeight > 0)) null else try {
            // Create Bitmap object out of the drawable
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            if (drawable != null) {
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
            }
            bitmap
        } catch (e: OutOfMemoryError) {
            // Simply return null of failed bitmap creations
            Timber.e(javaClass.toString(), "Encountered OutOfMemoryError while generating bitmap!")
            null
        }
    }

    //endregion
    //region Mesure Method
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec)
        val imageSize = if (width < height) width else height
        setMeasuredDimension(imageSize, imageSize)
    }

    private fun measureWidth(measureSpec: Int): Int {
        val result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        result = when (specMode) {
            MeasureSpec.EXACTLY -> {
                // The parent has determined an exact size for the child.
                specSize
            }
            MeasureSpec.AT_MOST -> {
                // The child can be as large as it wants up to the specified size.
                specSize
            }
            else -> {
                // The parent has not imposed any constraint on the child.
                canvasSize
            }
        }
        return result
    }

    private fun measureHeight(measureSpecHeight: Int): Int {
        val result: Int
        val specMode = MeasureSpec.getMode(measureSpecHeight)
        val specSize = MeasureSpec.getSize(measureSpecHeight)
        result = when (specMode) {
            MeasureSpec.EXACTLY -> {
                // We were told how big to be
                specSize
            }
            MeasureSpec.AT_MOST -> {
                // The child can be as large as it wants up to the specified size.
                specSize
            }
            else -> {
                // Measure the text (beware: ascent is a negative number)
                canvasSize
            }
        }
        return result + 2
    }

    //endregion
    //region Set Attr Method
    fun setColor(color: Int) {
        waveColor = color
        updateWaveShader()
        invalidate()
    }

    fun setBorderWidth(width: Float) {
        borderPaint!!.strokeWidth = width
        invalidate()
    }

    /**
     * Set vertical size of wave according to `amplitudeRatio`
     *
     * @param amplitudeRatio Default to be 0.05. Result of amplitudeRatio + waterLevelRatio should be less than 1.
     */
    fun setAmplitudeRatio(amplitudeRatio: Float) {
        if (this.amplitudeRatio != amplitudeRatio) {
            this.amplitudeRatio = amplitudeRatio
            invalidate()
        }
    }

    fun setProgress(progress: Int) {
        setProgress(progress, 1000)
    }

    fun setProgress(progress: Int, milliseconds: Int) {
        // vertical animation.
        val waterLevelAnim = ObjectAnimator.ofFloat(
            this,
            "waterLevelRatio",
            waterLevelRatio,
            1f - progress.toFloat() / 100
        )
        waterLevelAnim.duration = milliseconds.toLong()
        waterLevelAnim.interpolator = DecelerateInterpolator()
        val animatorSetProgress = AnimatorSet()
        animatorSetProgress.play(waterLevelAnim)
        animatorSetProgress.start()
    }

    //endregion
    //region Animation
    private fun startAnimation() {
        if (animatorSetWave != null) {
            animatorSetWave!!.start()
        }
    }

    private fun initAnimation() {
        // horizontal animation.
        val waveShiftAnim = ObjectAnimator.ofFloat(this, "waveShiftRatio", 0f, 1f)
        waveShiftAnim.repeatCount = ValueAnimator.INFINITE
        waveShiftAnim.duration = 1000
        waveShiftAnim.interpolator = LinearInterpolator()
        animatorSetWave = AnimatorSet()
        animatorSetWave!!.play(waveShiftAnim)
    }

    /**
     * Shift the wave horizontally according to `waveShiftRatio`.
     *
     * @param waveShiftRatio Should be 0 ~ 1. Default to be 0.
     */
    private fun setWaveShiftRatio(waveShiftRatio: Float) {
        if (this.waveShiftRatio != waveShiftRatio) {
            this.waveShiftRatio = waveShiftRatio
            invalidate()
        }
    }

    /**
     * Set water level according to `waterLevelRatio`.
     *
     * @param waterLevelRatio Should be 0 ~ 1. Default to be 0.5.
     */
    private fun setWaterLevelRatio(waterLevelRatio: Float) {
        if (this.waterLevelRatio != waterLevelRatio) {
            this.waterLevelRatio = waterLevelRatio
            invalidate()
        }
    }

    private fun cancel() {
        if (animatorSetWave != null) {
            animatorSetWave!!.end()
        }
    }

    override fun onDetachedFromWindow() {
        cancel()
        super.onDetachedFromWindow()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE) {
            startAnimation()
        } else {
            cancel()
        }
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (getVisibility() != VISIBLE) {
            return
        }
        if (visibility == VISIBLE) {
            startAnimation()
        } else {
            cancel()
        }
    }
    //endregion
    /**
     * Transparent the given color by the factor
     * The more the factor closer to zero the more the color gets transparent
     *
     * @param color  The color to transparent
     * @param factor 1.0f to 0.0f
     * @return int - A transplanted color
     */
    private fun adjustAlpha(color: Int, factor: Float): Int {
        val alpha = (Color.alpha(color) * factor).roundToInt()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    companion object {
        // Default values
        const val DEFAULT_AMPLITUDE_RATIO = 0.05f
        const val DEFAULT_WATER_LEVEL_RATIO = 0.5f
        const val DEFAULT_WAVE_LENGTH_RATIO = 1.0f
        const val DEFAULT_WAVE_SHIFT_RATIO = 0.0f
        const val DEFAULT_WAVE_COLOR = Color.BLACK
        const val DEFAULT_BORDER_WIDTH = 10
    }

    //region Constructor & Init Method
    init {
        init(context, attrs, defStyleAttr)
    }
}
