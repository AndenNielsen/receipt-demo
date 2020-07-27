package com.demo.receipt

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.FileProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import java.io.File

fun View.hideSoftKeyboard() {
    clearFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}

fun File.getUri(): Uri? {
    return FileProvider.getUriForFile(
        globalContext,
        "com.demo.receipt.fileprovider",
        this
    )
}

val globalContext: Context
    get() = GlobalContext.get().koin.rootScope.androidContext()
