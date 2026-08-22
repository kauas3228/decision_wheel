package com.ikaroorg.decision_wheel.ui.components

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikaroorg.decision_wheel.R
import com.ikaroorg.decision_wheel.data.model.Option
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun DecisionWheel(
    options: List<Option>,
    rotateAngle: Float,
) {
    val textMeasure = rememberTextMeasurer()

    val colorScheme = MaterialTheme.colorScheme

    val outline = colorScheme.outline
    val surface = colorScheme.surface
    val onBackground = colorScheme.onBackground

    val text = stringResource(R.string.no_options_in_wheel)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.fillMaxWidth(0.7f).aspectRatio(1f)
        ) {
            val sweepAngle = 360f / options.size
            val radius = size.width / 2
            val center = Offset(size.width / 2, size.height / 2)

            drawCircle(
                color = surface,
                radius = radius,
                center = center
            )
            drawCircle(
                color = outline,
                radius = radius,
                center = center,
                style = Stroke(width = 5.dp.toPx())
            )

            if (options.size < 2) {
                val measuredText = textMeasure.measure(
                    text = text,
                    style = TextStyle(
                        color = onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    constraints = Constraints(maxWidth = (size.width - (12.dp.toPx() * 2)).toInt().coerceAtLeast(0))
                )

                val topLeftOffSet = Offset(
                    x = center.x - (measuredText.size.width / 2f),
                    y = center.y - (measuredText.size.height / 2f)
                )

                drawText(
                    textLayoutResult = measuredText,
                    topLeft = topLeftOffSet
                )

                return@Canvas
            }

            val innerRadius = radius - 8.dp.toPx()
            val archSize = Size(innerRadius * 2, innerRadius * 2)
            val archTopLeft = Offset(center.x - innerRadius, center.y - innerRadius)

            options.forEachIndexed { index, option ->
                val startAngle = (index * sweepAngle) + rotateAngle

                drawArc(
                    color = option.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = archTopLeft,
                    size = archSize
                )

                drawArc(
                    color = surface,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = archTopLeft,
                    size = archSize,
                    style = Stroke(width = 3.dp.toPx())
                )

                val medianAngle = Math.toRadians((startAngle + sweepAngle / 2).toDouble())
                val textDistance = innerRadius * 0.6f

                val textX = center.x + (textDistance * cos(medianAngle)).toFloat()
                val textY = center.y + (textDistance * sin(medianAngle)).toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    option.text,
                    textX,
                    textY + 12f,
                    Paint().apply {
                        color = android.graphics.Color.WHITE
                        textSize = 16.dp.toPx()
                        textAlign = Paint.Align.CENTER
                        typeface = Typeface.DEFAULT_BOLD
                    }
                )
            }

            val trianglePath = Path().apply {
                val topCenter = Offset(center.x, center.y - radius + 14.dp.toPx())
                moveTo(topCenter.x, topCenter.y + 20.dp.toPx())
                lineTo(topCenter.x - 13.dp.toPx(), topCenter.y - 8.dp.toPx())
                lineTo(topCenter.x + 13.dp.toPx(), topCenter.y - 8.dp.toPx())
                close()
            }
            drawPath(
                path = trianglePath,
                color = onBackground
            )
        }
    }
}