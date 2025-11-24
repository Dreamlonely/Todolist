package com.example.todo.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan

class DotSpan(
    private val radius: Float = 6f,
    private val color: Int,
    private val offset: Float = 4f // move the dot lower
) : LineBackgroundSpan {

    override fun drawBackground(
        c: Canvas,
        p: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lnum: Int
    ) {
        val oldColor = p.color
        p.color = color
        // Draw the dot slightly below baseline for better visibility
        c.drawCircle(
            ((left + right) / 2).toFloat(),
            bottom + offset, // <-- lowered by offset
            radius,
            p
        )
        p.color = oldColor
    }
}
