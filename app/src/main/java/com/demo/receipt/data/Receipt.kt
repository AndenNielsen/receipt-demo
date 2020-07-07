package com.demo.receipt.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipts")
data class Receipt(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val description: String,
        val totalAmount: Double,
        val vatAmount: Double = totalAmount * 0.25,
        val currency: String,
        val date: String,
        val imageUri: String
)
