package com.ikaroorg.decision_wheel.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ikaroorg.decision_wheel.data.local.DataStoreManager
import com.ikaroorg.decision_wheel.data.model.Option
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.Options
import java.util.UUID

class ViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel(){
    val options: StateFlow<List<Option>> = dataStoreManager.options.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = emptyList()
    )
    private val _selectedOption = MutableStateFlow<Option?>(null)
    val selectedOption: StateFlow<Option?> = _selectedOption.asStateFlow()
    fun onSpinFinished(result: Option?) {
        _selectedOption.value = result
    }
    fun clearSelectedOption() {
        _selectedOption.value = null
    }

    fun addOption(text: String, color: Color) {
        val currentOptions = options.value.toMutableList()
        val newOption = Option (
            id = UUID.randomUUID().toString(),
            text = text,
            color = color
        )

        currentOptions.add(newOption)
        updateOptions(currentOptions)
    }

    fun updateOptions(options: List<Option>){
        viewModelScope.launch {
            dataStoreManager.saveOptions(options)
        }
    }

    fun deleteOption(optionId: String) {
        val currentOptions = options.value.toMutableList()

        currentOptions.removeAll{ it.id == optionId }
        updateOptions(currentOptions)
    }
    companion object {
        fun providerFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                    ?: throw IllegalStateException("Application context not found")

                ViewModel(
                    dataStoreManager = DataStoreManager(context)
                )
            }
        }
    }
}