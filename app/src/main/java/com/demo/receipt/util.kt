package com.demo.receipt

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
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

fun getFileForPhoto(): File? {
// Create the File where the photo should go
    return try {
        createImageFile(globalContext)
    } catch (ex: IOException) {
        // Error occurred while creating the File
        null
    }
}

fun downscaleToMaxAllowedDimension(photoPath: String): Bitmap? {
    val bitmapOptions = BitmapFactory.Options()
    bitmapOptions.inJustDecodeBounds = true
    BitmapFactory.decodeFile(photoPath, bitmapOptions)
    val srcWidth = bitmapOptions.outWidth
    val srcHeight = bitmapOptions.outHeight
    var dstWidth = srcWidth
    val scale = srcWidth.toFloat() / srcHeight
    val MAX_ALLOWED_RESOLUTION = 1024
    if (srcWidth > srcHeight && srcWidth > MAX_ALLOWED_RESOLUTION) {
        dstWidth = MAX_ALLOWED_RESOLUTION
    } else if (srcHeight > srcWidth && srcHeight > MAX_ALLOWED_RESOLUTION) {
        dstWidth = (MAX_ALLOWED_RESOLUTION * scale).toInt()
    }
    bitmapOptions.inJustDecodeBounds = false
    bitmapOptions.inDensity = bitmapOptions.outWidth
    bitmapOptions.inTargetDensity = dstWidth
    return BitmapFactory.decodeFile(photoPath, bitmapOptions)
}
