package com.demo.receipt.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.receipt.data.model.Receipt

@Dao
interface ReceiptDao {

    @Insert
    fun saveReceipt(receipt: Receipt): Long

    @Query("SELECT * FROM receipts ORDER BY id DESC")
    fun getReceipts(): List<Receipt>
}
