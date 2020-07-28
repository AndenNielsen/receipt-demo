package com.demo.receipt.data

import androidx.annotation.WorkerThread
import com.demo.receipt.data.db.ReceiptDao
import com.demo.receipt.data.model.Receipt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ReceiptRepository(private val receiptDao: ReceiptDao) {

    @WorkerThread
    fun saveReceipt(receipt: Receipt): Long {
        return receiptDao.saveReceipt(receipt)
    }

    fun getReceipts(): Flow<List<Receipt>> =
        receiptDao.getReceipts()
            .flowOn(Dispatchers.IO)
}