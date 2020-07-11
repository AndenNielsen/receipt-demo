package com.demo.receipt.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.demo.receipt.data.ReceiptRepository
import com.demo.receipt.ui.Receipt


class MainViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {

    val receipts: LiveData<List<Receipt>>
        get() =
            Transformations.map(receiptRepository.getReceipts()) { receipts ->
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
}