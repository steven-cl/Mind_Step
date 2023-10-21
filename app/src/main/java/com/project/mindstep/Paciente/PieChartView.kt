package com.project.mindstep.Paciente

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PieChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val colors = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA)
    private val percentages = floatArrayOf(30f, 25f, 20f, 10f, 15f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPieChart(canvas)
    }

    private fun drawPieChart(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = Math.min(centerX, centerY)
        var startAngle = 0f

        for (i in percentages.indices) {
            val sweepAngle = 360f * (percentages[i] / 100)
            val paint = Paint()
            paint.color = colors[i]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle,
                sweepAngle,
                true,
                paint
            )
            startAngle += sweepAngle
        }
    }
}
