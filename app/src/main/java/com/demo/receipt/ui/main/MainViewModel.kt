package com.demo.receipt.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.demo.receipt.data.ReceiptRepository

class MainViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {
    lateinit var currentPhotoPath: Uri
}