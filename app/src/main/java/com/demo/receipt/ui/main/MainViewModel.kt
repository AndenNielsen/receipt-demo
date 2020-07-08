package com.demo.receipt.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.receipt.data.Receipt
import com.demo.receipt.data.ReceiptRepository

class MainViewModel(receiptRepository: ReceiptRepository) : ViewModel() {
    lateinit var currentPhotoPath: Uri

    val receipts: LiveData<List<Receipt>> = receiptRepository.getReceipts()

}