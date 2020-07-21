package com.demo.receipt

import com.demo.receipt.data.ReceiptRepository
import com.demo.receipt.data.db.ReceiptDatabase
import com.demo.receipt.ui.details.ReceiptDetailsViewModel
import com.demo.receipt.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of ReceiptRepository
    single { ReceiptRepository(get()) }
    // single instance of ReceiptDao
    single { ReceiptDatabase.buildDatabase(androidContext()).receiptDao() }

    // ViewModels
    viewModel { MainViewModel(get()) }
    viewModel { ReceiptDetailsViewModel(get()) }
}