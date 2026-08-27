package com.ikaroorg.decision_wheel.data.model

sealed class ApplicationStats{
    data object Loading: ApplicationStats()
    data object Started: ApplicationStats()
    data object NotStarted: ApplicationStats()
}