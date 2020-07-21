package com.demo.receipt.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.receipt.data.Receipt

@Dao
interface ReceiptDao {

    /**
     * Insert articles into the table
     */
    @Insert
    fun saveReceipt(receipt: Receipt): Long

    @Query("SELECT * FROM receipts")
    fun getReceipts(): LiveData<List<Receipt>>
}
