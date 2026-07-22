package com.babichdev.moneyflow.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.babichdev.moneyflow.data.repository.TransactionRepository

class AddViewModelFactory(
    private val repository: TransactionRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}