package com.demo.receipt.ui.details

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.receipt.data.Receipt
import com.demo.receipt.data.ReceiptRepository
import com.demo.receipt.getPhotoURI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ReceiptDetailsViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {

    var description: String = ""
    var totalAmount: String = ""
    var currency: String = ""
    var date: String = ""
    var imageUri: ObservableField<Uri> = ObservableField()

    var saveToDb: MutableLiveData<Long> = MutableLiveData()

    val photoPath: Flow<Uri?> = flow {
        emit(getPhotoURI())
    }

    fun saveReceipt() {
        viewModelScope.launch(Dispatchers.IO) {
            val receipt = Receipt(
                description = description,
                totalAmount = totalAmount.toDouble(),
                currency = currency,
                date = date,
                imageUri = imageUri.get().toString()
            )
            saveToDb.postValue(receiptRepository.saveReceipt(receipt))
        }
    }
}