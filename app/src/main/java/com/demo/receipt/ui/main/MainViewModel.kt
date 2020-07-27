package com.demo.receipt.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.demo.receipt.data.ReceiptRepository
import com.demo.receipt.ui.model.Receipt
import kotlinx.coroutines.flow.map


class MainViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {

    val receipts: LiveData<List<Receipt>>
        get() =
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
                .asLiveData()

}