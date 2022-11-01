package com.alfrsms.and_ch6.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfrsms.and_ch6.data.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val dataStoreManager: DataStoreManager): ViewModel() {
    fun saveAccount(username: String, password: String, email: String) {
        viewModelScope.launch {
            dataStoreManager.setUsername(username)
            dataStoreManager.setPassword(password)
            dataStoreManager.setEmail(email)
        }
    }
}