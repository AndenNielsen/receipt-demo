package com.demo.receipt.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Receipt::class],
    version = 1
)
abstract class ReceiptDatabase : RoomDatabase() {
    abstract fun receiptDao(): ReceiptDao

    companion object {
        private const val databaseName = "receipts-db"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, ReceiptDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
    }
}