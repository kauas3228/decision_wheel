package com.ikaroorg.decision_wheel.data.model

sealed class LanguageState{
    data object Loading: LanguageState()
    data object Selected: LanguageState()
    data object NotSelected: LanguageState()
}