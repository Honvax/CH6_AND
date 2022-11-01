package com.alfrsms.and_ch6.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alfrsms.and_ch6.data.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataStoreManager: DataStoreManager): ViewModel() {
    fun statusLogin(isLogin: Boolean) {
        viewModelScope.launch {
            dataStoreManager.statusLogin(isLogin)
        }
    }

    fun getUsername(): LiveData<String> {
        return dataStoreManager.getUsername().asLiveData()
    }

    fun getPassword(): LiveData<String> {
        return dataStoreManager.getPassword().asLiveData()
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return dataStoreManager.getLoginStatus().asLiveData()
    }
}