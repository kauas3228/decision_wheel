package com.ikaroorg.decision_wheel.utils

import com.ikaroorg.decision_wheel.data.Option

fun getSelectedOption(
    rotationAngle: Float,
    options: List<Option>
): Option? {
    if (options.isEmpty()) return null

    val sweepAngle = 360f / options.size
    val pointerAngle = 270f // Pointer angle location

    val normalizedRotation = (rotationAngle % 360f + 360f) % 360f

    val angleOnWheel = (pointerAngle - normalizedRotation + 360f) % 360f

    val selectedOptionIndex = (angleOnWheel / sweepAngle).toInt() % options.size

    return options[selectedOptionIndex]
}