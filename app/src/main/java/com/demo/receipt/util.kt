package com.demo.receipt

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private fun createImageFile(context: Context): File {
    // Create an image file name
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}

fun getPhotoURI(): Uri? {
// Create the File where the photo should go
    val context = GlobalContext.get().koin.rootScope.androidContext()
    val photoFile: File? = try {
        createImageFile(context)
    } catch (ex: IOException) {
        // Error occurred while creating the File
        null
    }

    return photoFile?.let {
        FileProvider.getUriForFile(
            context,
            "com.demo.receipt.fileprovider",
            it
        )
    }

}
