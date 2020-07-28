package com.demo.receipt.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.receipt.data.ReceiptRepository
import com.demo.receipt.ui.model.Receipt
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {

    private val receipts = MutableLiveData<List<Receipt>>()

    init {
        fetchReceipts()
    }

    fun getReceipts(): LiveData<List<Receipt>> {
        return receipts
    }

    private fun fetchReceipts() {
        viewModelScope.launch {
            receiptRepository.getReceipts()
                .map { receipts ->
                    receipts.map {
                        Receipt(
                            it.id,
                            it.description,
                            it.totalAmount,
                            it.currency,
                            it.date,
                            Uri.parse(it.imageUri)
                        )
                    }
                }
                .collect { receipts.value = it }
        }
    }
}