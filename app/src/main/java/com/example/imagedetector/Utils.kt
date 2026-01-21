package com.example.imagedetector

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.File

//  Helper function to rotate the image captured before rendering on the screen
fun decodeAndRotateBitmap(file: File): Bitmap {

    //  Decoding the original bitmap
    val originalBitmap = BitmapFactory.decodeFile(file.absolutePath)

    val exif = ExifInterface(file.absolutePath)
    val orientation = exif.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL
    )

    val matrix = Matrix()
    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
        else -> return originalBitmap
    }

    // Creating rotated copy
    val rotatedBitmap = Bitmap.createBitmap(
        originalBitmap, 0, 0, originalBitmap.width, originalBitmap.height, matrix, true
    )

    // Freeing the memory of the original bitmap if not required
    if (originalBitmap != rotatedBitmap) {
        originalBitmap.recycle()
    }

    return rotatedBitmap
}