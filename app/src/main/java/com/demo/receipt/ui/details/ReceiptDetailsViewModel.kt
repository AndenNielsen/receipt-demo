package com.demo.receipt.ui.details

import android.net.Uri
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.receipt.data.ReceiptRepository
import com.demo.receipt.data.model.Receipt
import com.demo.receipt.downscaleToMaxAllowedDimension
import com.demo.receipt.getFileForPhoto
import com.demo.receipt.getUri
import com.demo.receipt.writeBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class ReceiptDetailsViewModel(private val receiptRepository: ReceiptRepository) : ViewModel() {

    private lateinit var photoPath: File
    var description: String = ""
    var totalAmount: String = ""
    var currency: String = ""
    var date: String = ""
    val imageUri: ObservableField<Uri?> = ObservableField()

    val showAddPhotoButton: ObservableBoolean = object : ObservableBoolean(imageUri) {
        override fun get(): Boolean {
            return imageUri.get()?.toString().isNullOrEmpty()
        }
    }

    private val _inputComplete = MutableLiveData<Long>()
    val inputComplete: LiveData<Long>
        get() = _inputComplete

    private val _uriForPhoto = MutableLiveData<Uri>()
    val uriForPhoto: LiveData<Uri>
        get() = _uriForPhoto

    fun processPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            getFileForPhoto()?.apply {
                val bitmap =
                    downscaleToMaxAllowedDimension(photoPath.absolutePath)

                bitmap?.let {
                    writeBitmap(it)
                }
                imageUri.set(getUri())
            }
        }
    }

    fun preparePhotoPath() {
        viewModelScope.launch(Dispatchers.IO) {
            getFileForPhoto()?.let {
                photoPath = it
                showAddPhotoButton.set(false)
                _uriForPhoto.postValue(it.getUri())
            }
        }
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
            _inputComplete.postValue(receiptRepository.saveReceipt(receipt))
        }
    }
}