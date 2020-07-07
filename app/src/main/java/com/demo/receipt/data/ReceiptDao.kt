package com.demo.receipt.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptDao {

    /**
     * Insert articles into the table
     */
    @Insert
    fun saveReceipt(receipt: Receipt): Long

    @Query("SELECT * FROM receipts")
    fun getReceipts(): Flow<List<Receipt>>
}
