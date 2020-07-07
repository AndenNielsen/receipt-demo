package com.demo.receipt.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ReceiptRepository(private val receiptDao: ReceiptDao) {

    @WorkerThread
    fun saveReceipt(receipt: Receipt) {
        receiptDao.saveReceipt(receipt)
    }

    fun getReceipts(): List<Receipt> {
        return listOf()
    }
}