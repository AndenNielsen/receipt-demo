package com.demo.receipt.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.demo.receipt.data.db.ReceiptDao

class ReceiptRepository(private val receiptDao: ReceiptDao) {

    @WorkerThread
    fun saveReceipt(receipt: Receipt) {
        receiptDao.saveReceipt(receipt)
    }

    @WorkerThread
    fun getReceipts(): LiveData<List<Receipt>> {
        return receiptDao.getReceipts()
    }
}