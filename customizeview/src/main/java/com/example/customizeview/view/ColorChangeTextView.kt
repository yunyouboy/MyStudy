package com.example.customizeview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatTextView
import com.example.customizevielib.utils.sp2px
import com.example.customizeview.R


/**
 *Author:qyg
 *DATE:2020/7/15 16:02
 *Description：
 **/
class ColorChangeTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.textViewStyle) :
        AppCompatTextView(context, attrs, defStyleAttr) {

    private var mPaint = Paint()

    private var mText: String = "HelloWorld"
        set(value) {//变量扩展方法
            field = value
            requestLayout()
            invalidate()
        }
    private var mTextSize: Float = sp2px(30f)
        set(value) {
            field = value
            mPaint.textSize = field
            requestLayout()
            invalidate()
        }
    private var mTextColor: Int = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }
    private var mTextColorChange: Int = Color.RED
        set(value) {
            field = value
            invalidate()
        }
    private var mProgress: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    @Directions
    private var mDirection: Int = direction_left

    private var mTextBound = Rect()
    private var mTextWidth = 0
    private var mTextHeight = 0
    private var mTextStartX = 0
    private var mTextStartY = 0

    init {
        initAttr(context, attrs)
        initPaint()
    }

    private fun initAttr(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ColorChangeTextView)
        mText = ta.getString(R.styleable.ColorChangeTextView_text) ?: mText
        mTextSize = ta.getDimensionPixelSize(R.styleable.ColorChangeTextView_text_size, mTextSize.toInt()).toFloat()
        mTextColor = ta.getColor(R.styleable.ColorChangeTextView_text_color, mTextColor)
        mTextColorChange = ta.getColor(R.styleable.ColorChangeTextView_text_color_change, mTextColorChange)
        mProgress = ta.getFloat(R.styleable.ColorChangeTextView_progress, mProgress)
        mDirection = ta.getInt(R.styleable.ColorChangeTextView_direction, mDirection)
    }

    private fun initPaint() {
        mPaint.textSize = mTextSize
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = sp2px(3f)
        mPaint.color = mTextColor
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //1.  先测量文字
        measureText()
        //2.  测量自身
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec)
        //3. 保存测量的尺寸 setMeasuredDimension()
        setMeasuredDimension(width, height)
        mTextStartX = measuredWidth / 2 - mTextWidth / 2
        mTextStartY = measuredHeight / 2 - mTextHeight / 2
    }

    private fun measureText() {
        mPaint.getTextBounds(mText, 0, mText.length, mTextBound)
        mTextWidth = (mPaint.measureText(mText) + .5f).toInt()
        val fontMetrics = Paint.FontMetrics()
        mPaint.getFontMetrics(fontMetrics)
        mTextHeight = (fontMetrics.descent - fontMetrics.ascent + .5f).toInt()
    }

    private fun measureWidth(measureSpec: Int): Int {
        var width = 0
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.EXACTLY -> width = size
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> width = (mTextWidth + .5f).toInt() + paddingLeft + paddingRight
        }
        //如果是AT_MOST,不能超过父布局的尺寸
        width = if (mode == MeasureSpec.AT_MOST) width.coerceAtMost(size) else width
        return width
    }

    private fun measureHeight(measureSpec: Int): Int {
        var height = 0
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.EXACTLY -> height = size
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> height = (mTextBound.height() + .5f).toInt() + paddingTop + paddingBottom
        }
        //如果是AT_MOST,不能超过父布局的尺寸
        height = if (mode == MeasureSpec.AT_MOST) width.coerceAtMost(size) else height
        return height
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCenterLineX(canvas)
        drawCenterLineY(canvas)
        when (mDirection) {
            direction_left -> {
                canvas.apply {
                    //绘制颜色改变的层
                    drawTextHorizontal(canvas, mTextColorChange, mTextStartX, (mTextStartX + mTextWidth * mProgress).toInt())
                    //绘制底色层
                    drawTextHorizontal(canvas, mTextColor, (mTextStartX + mProgress * mTextWidth).toInt(), mTextStartX + mTextWidth)
                }
            }
            direction_right -> {
                //绘制颜色改变的层
                drawTextHorizontal(canvas, mTextColorChange, (mTextStartX + (1 - mProgress) * mTextWidth).toInt(), mTextStartX + mTextWidth)
                //绘制底色层
                drawTextHorizontal(canvas, mTextColor, mTextStartX, (mTextStartX + (1 - mProgress) * mTextWidth).toInt())
            }

            direction_top -> {
                //绘制颜色改变的层
                drawTextVertical(canvas, mTextColorChange, mTextStartY, (mTextStartY + mProgress * mTextHeight).toInt())
                //绘制底色层
                drawTextVertical(canvas, mTextColor, (mTextStartY + mProgress * mTextHeight).toInt(), mTextStartY + mTextHeight)
            }

            direction_bottom -> {
                //绘制颜色改变的层
                drawTextVertical(canvas, mTextColorChange, (mTextStartY + (1 - mProgress) * mTextHeight).toInt(), mTextStartY + mTextHeight);
                //绘制底色层
                drawTextVertical(canvas, mTextColor, mTextStartY, (mTextStartY + (1 - mProgress) * mTextHeight).toInt());
            }
        }
    }

    private fun drawTextHorizontal(canvas: Canvas, color: Int, startX: Int, endX: Int) {
        canvas.apply {
            mPaint.color = color
            save()
            clipRect(startX, 0, endX, measuredHeight)
            drawText(mText, mTextStartX.toFloat(), measuredHeight / 2 - (mPaint.descent() / 2 + mPaint.ascent() / 2), mPaint)
            restore()
        }
    }

    private fun drawTextVertical(canvas: Canvas, color: Int, startY: Int, endY: Int) {
        mPaint.color = color
        canvas.save()
        canvas.clipRect(0, startY, measuredWidth, endY)
        canvas.drawText(mText, mTextStartX.toFloat(), measuredHeight / 2 - (mPaint.descent() + mPaint.ascent()) / 2, mPaint)
        canvas.restore()
    }

    companion object {
        @IntDef(flag = true, value = [direction_left, direction_right, direction_top, direction_bottom])
        @Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Directions

        const val direction_left = 0
        const val direction_right = 1
        const val direction_top = 2
        const val direction_bottom = 3
    }

    private fun drawCenterLineX(canvas: Canvas) {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        paint.strokeWidth = 3f
        canvas.drawLine(width / 2.toFloat(), 0f, width / 2.toFloat(), height.toFloat(), paint)
    }

    private fun drawCenterLineY(canvas: Canvas) {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE
        paint.strokeWidth = 3f
        canvas.drawLine(0f, height / 2.toFloat(), width.toFloat(), height / 2.toFloat(), paint)
    }
}