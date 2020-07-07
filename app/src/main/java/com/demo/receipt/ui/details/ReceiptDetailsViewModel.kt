package com.demo.receipt.ui.details

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.receipt.data.Receipt
import com.demo.receipt.data.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReceiptDetailsViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {

    var description: String = ""
    var totalAmount: String = ""
    var currency: String = ""
    var date: String = ""
    var imageUri: Uri? = null

    fun saveReceipt() {
        viewModelScope.launch(Dispatchers.IO) {
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
}