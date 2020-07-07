package com.demo.receipt.ui.details

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.demo.receipt.data.Receipt
import com.demo.receipt.data.ReceiptRepository

class ReceiptDetailsViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {

    var description: String = ""
    var totalAmount: String = ""
    var currency: String = ""
    var date: String = ""
    var imageUri: Uri? = null

    fun saveReceipt() {
        val receipt = Receipt(
            description = description,
            totalAmount = totalAmount.toDouble(),
            currency = currency,
            date = date,
            imageUri = imageUri.toString()
        )
        receiptRepository.saveReceipt(receipt)
    }
}