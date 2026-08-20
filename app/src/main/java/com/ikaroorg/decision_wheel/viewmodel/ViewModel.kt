package com.ikaroorg.decision_wheel.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ikaroorg.decision_wheel.data.dao.OptionDao
import com.ikaroorg.decision_wheel.data.local.AppDataBase
import com.ikaroorg.decision_wheel.data.local.DataStoreManager
import com.ikaroorg.decision_wheel.data.model.InitializedState
import com.ikaroorg.decision_wheel.data.model.Option
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class ViewModel(
    private val optionDao: OptionDao,
    private val dataStoreManager: DataStoreManager
) : ViewModel(){
    val options: StateFlow<List<Option>> = optionDao.getAllOptions().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = emptyList()
    )

    val language: StateFlow<String> = dataStoreManager.language.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = "English"
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
        viewModelScope.launch {
            val newOption = Option (
                id = UUID.randomUUID().toString(),
                text = text,
                color = color
            )
            optionDao.insertOption(newOption)
        }
    }

    fun deleteOption(optionId: String) {
        viewModelScope.launch {
            optionDao.deleteOption(optionId)
        }
    }

    fun changeLanguage(language: String){
        viewModelScope.launch {
            dataStoreManager.saveLanguage(language)
        }
    }

    fun saveIsInitialized() {
        viewModelScope.launch {
            dataStoreManager.saveIsInitialized(true)
        }
    }
    val initializedState: StateFlow<InitializedState> = dataStoreManager.isInitialized.map { isSelected ->
        if (isSelected) {
            InitializedState.Selected
        } else {
            InitializedState.NotSelected
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = InitializedState.Loading
    )
    companion object {
        fun providerFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                    ?: throw IllegalStateException("Application context not found")

                val database = AppDataBase.getDatabase(context)
                ViewModel(optionDao = database.optionDao(), dataStoreManager = DataStoreManager(context))
            }
        }
    }
}