package com.demo.receipt

import com.demo.receipt.data.ReceiptRepository
import com.demo.receipt.ui.details.ReceiptDetailsViewModel
import com.demo.receipt.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModule = module {
    // single instance of ReceiptRepository
    single { ReceiptRepository() }

    // ViewModels
    viewModel { MainViewModel(get()) }
    viewModel { ReceiptDetailsViewModel(get()) }
}