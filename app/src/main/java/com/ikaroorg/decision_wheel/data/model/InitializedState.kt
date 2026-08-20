package com.ikaroorg.decision_wheel.data.model

sealed class InitializedState{
    data object Loading: InitializedState()
    data object Selected: InitializedState()
    data object NotSelected: InitializedState()
}