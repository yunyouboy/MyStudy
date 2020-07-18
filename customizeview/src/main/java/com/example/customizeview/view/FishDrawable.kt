package com.example.customizeview.view

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.cos
import kotlin.math.sin

class FishDrawable() : Drawable() {
    private var mPaint: Paint = Paint()
    private var mPath: Path = Path()

    private val otherAlpha: Int = 110
    private val bodyAlpha: Int = 160

    // 鱼的重心
    private lateinit var middlePoint: PointF

    // 鱼的主要朝向角度
    private var fishMainAngle = 0f

    /**
     * 鱼的长度值
     */
    // 绘制鱼头的半径
    private val headerRadius = 100f

    // 鱼身长度
    private val bodyLength = headerRadius * 3.2f

    // 寻找鱼鳍起始点坐标的线长
    private val findFinsLength = 0.9f * headerRadius

    // 鱼鳍的长度
    private val finsLength = 1.3f * headerRadius

    // 大圆的半径
    private val bigCircleRadius = 0.7f * headerRadius

    // 中圆的半径
    private val middleCircleRadius = 0.6f * bigCircleRadius

    // 小圆半径
    private val smallCircleRadius = 0.4f * middleCircleRadius

    // --寻找尾部中圆圆心的线长
    private val findMiddleCircleLength = bigCircleRadius * (0.6f + 1)

    // --寻找尾部小圆圆心的线长
    private val findSmallCircleLength = middleCircleRadius * (0.4f + 2.7f)

    // --寻找大三角形底边中心点的线长
    private val findTriangleLength = middleCircleRadius * 2.7f

    init {
        mPaint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            isDither = true
            setARGB(otherAlpha, 244, 92, 71)
        }
        middlePoint = PointF(4.19f * headerRadius, 4.19f * headerRadius)
    }

    override fun draw(canvas: Canvas) {
        var fishAngle: Float = fishMainAngle

        // 绘制鱼头
        var headerPoint = calculatePoint(middlePoint, bodyLength / 2, fishAngle)
        canvas.drawCircle(headerPoint.x, headerPoint.y, headerRadius, mPaint)

        //绘制右鱼鳍
        var rightFinsPoint = calculatePoint(headerPoint, findFinsLength, fishAngle - 110)
        drawFins(canvas, rightFinsPoint, fishAngle, true)

        //绘制左鱼鳍
        var leftFinsPoint = calculatePoint(headerPoint, findFinsLength, fishAngle + 110)
        drawFins(canvas, leftFinsPoint, fishAngle, false)

        var bodyBottomCenterPoint = calculatePoint(headerPoint, bodyLength, fishAngle - 180)
        //画节肢1
        val segMentBottomCenterPoint = drawSegment(canvas, bodyBottomCenterPoint, bigCircleRadius, middleCircleRadius, findMiddleCircleLength, fishAngle, true)
        // 画节肢2
        drawSegment(canvas, segMentBottomCenterPoint, middleCircleRadius, smallCircleRadius, findSmallCircleLength, fishAngle, false)
        // 尾巴
        drawTriangle(canvas, segMentBottomCenterPoint, findTriangleLength, bigCircleRadius, fishAngle)
        drawTriangle(canvas, segMentBottomCenterPoint, findTriangleLength - 10, bigCircleRadius - 20, fishAngle)
        // 身体
        drawBody(canvas, headerPoint, bodyBottomCenterPoint, fishAngle)
    }

    private fun drawBody(canvas: Canvas, headerPoint: PointF, bodyBottomCenterPoint: PointF, fishAngle: Float) {
        var topLeftPoint = calculatePoint(headerPoint, headerRadius, fishAngle + 90)
        var topRightPoint = calculatePoint(headerPoint, headerRadius, fishAngle - 90)
        var bottomLeftPoint = calculatePoint(bodyBottomCenterPoint, bigCircleRadius, fishAngle + 90)
        var bottomRightPoint = calculatePoint(bodyBottomCenterPoint, bigCircleRadius, fishAngle - 90)

        // 二阶贝塞尔曲线的控制点 --- 决定鱼的胖瘦
        val controlLeft = calculatePoint(headerPoint, bodyLength * 0.56f,fishAngle + 130)
        val controlRight = calculatePoint(headerPoint, bodyLength * 0.56f,fishAngle - 130)

        // 绘制
        mPath.reset()
        mPath.moveTo(topLeftPoint.x, topLeftPoint.y)
        mPath.quadTo(controlLeft.x, controlLeft.y, bottomLeftPoint.x, bottomLeftPoint.y)
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y)
        mPath.quadTo(controlRight.x, controlRight.y, topRightPoint.x, topRightPoint.y)
        mPaint.alpha = bodyAlpha
        canvas.drawPath(mPath, mPaint)

    }

    private fun drawTriangle(canvas: Canvas, startPoint: PointF, findCenterLength: Float, findEdgeLength: Float, fishAngle: Float) {
        // 三角形底边的中心坐标
        val centerPoint = calculatePoint(startPoint, findCenterLength, fishAngle - 180)
        // 三角形底边两点
        val leftPoint = calculatePoint(centerPoint, findEdgeLength, fishAngle + 90)
        val rightPoint = calculatePoint(centerPoint, findEdgeLength, fishAngle - 90)

        mPath.reset()
        mPath.moveTo(startPoint.x, startPoint.y)
        mPath.lineTo(leftPoint.x, leftPoint.y)
        mPath.lineTo(rightPoint.x, rightPoint.y)
        canvas.drawPath(mPath, mPaint)

    }

    private fun drawSegment(canvas: Canvas, startPoint: PointF, bigRadius: Float, smallRadius: Float, findSmallCircleLength: Float, fishAngle: Float, hasBigCircle: Boolean): PointF {
        var bottomCenterPoint = calculatePoint(startPoint, findSmallCircleLength, fishAngle - 180)
        var upperLeftPoint = calculatePoint(startPoint, bigRadius, fishAngle + 90)
        var upperRightPoint = calculatePoint(startPoint, bigRadius, fishAngle - 90)
        var bottomLeftPoint = calculatePoint(bottomCenterPoint, smallRadius, fishAngle + 90)
        var bottomRightPoint = calculatePoint(bottomCenterPoint, smallRadius, fishAngle - 90)

        if (hasBigCircle) canvas.drawCircle(startPoint.x, startPoint.y, bigRadius, mPaint)
        canvas.drawCircle(bottomCenterPoint.x, bottomCenterPoint.y, smallRadius, mPaint)

        mPath.reset()
        mPath.moveTo(upperLeftPoint.x, upperLeftPoint.y)
        mPath.lineTo(upperRightPoint.x, upperRightPoint.y)
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y)
        mPath.lineTo(bottomLeftPoint.x, bottomLeftPoint.y)
        canvas.drawPath(mPath, mPaint)
        return bottomCenterPoint
    }

    /**
     * 绘制鱼鳍
     */
    private fun drawFins(canvas: Canvas, startPoint: PointF, fishAngle: Float, isRight: Boolean) {
        val controlAngle = 115f

        // 鱼鳍的终点 --- 二阶贝塞尔曲线的终点
        val endPoint = calculatePoint(startPoint, finsLength, fishAngle - 180)
        // 控制点
        val controlPoint = calculatePoint(startPoint, finsLength * 1.8f, if (isRight) fishAngle - controlAngle else fishAngle + controlAngle)
        mPath.reset()
        mPath.moveTo(startPoint.x, startPoint.y)
        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y)
        canvas.drawPath(mPath, mPaint)
    }

    /**
     * @param startPoint 起始点坐标
     * @param length     要求的点到起始点的直线距离 -- 线长
     * @param angle      鱼当前的朝向角度
     * @return
     */
    private fun calculatePoint(startPoint: PointF, length: Float, angle: Float): PointF {
        var deltaX: Float = (cos(Math.toRadians(angle.toDouble())) * length).toFloat()
        var deltaY: Float = (sin(Math.toRadians((angle - 180).toDouble())) * length).toFloat()
        return PointF(startPoint.x + deltaX, startPoint.y + deltaY)
    }


    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getIntrinsicWidth(): Int {
        return (8.38f * headerRadius).toInt()
    }

    override fun getIntrinsicHeight(): Int {
        return (8.38f * headerRadius).toInt()
    }
}