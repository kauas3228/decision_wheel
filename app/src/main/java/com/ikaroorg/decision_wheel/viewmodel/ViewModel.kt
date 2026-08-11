package com.ikaroorg.decision_wheel.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.ikaroorg.decision_wheel.data.Model.Option
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel() : ViewModel(){
    private val _options = MutableStateFlow<List<Option>>(
        listOf<Option>(
            // Using an illustrator colors and texts
            Option("1", "Option 1", Color(0xFF1E40AF)),
            Option("2", "Option 2", Color(0xFF0D9488)),
            Option("3", "Option 3", Color(0xFF14B8A6)),
            Option("4", "Option 4", Color(0xFF3B82F6)),
        )
    )
    val options: StateFlow<List<Option>> = _options.asStateFlow()

    private val _selectedOption = MutableStateFlow<Option?>(null)
    val selectedOption: StateFlow<Option?> = _selectedOption.asStateFlow()
    fun onSpinFinished(result: Option?) {
        _selectedOption.value = result
    }
    fun clearSelectedOption() {
        _selectedOption.value = null
    }
}