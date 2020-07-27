package com.demo.receipt.ui.model

import android.net.Uri
import java.text.NumberFormat
import java.util.*

data class Receipt(
    val id: Long,
    val description: String,
    private val totalAmount: Double,
    val currency: String,
    val date: String,
    val imageUri: Uri
) {
    val totalAmountFormatted: String
        get() = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(totalAmount)
}
