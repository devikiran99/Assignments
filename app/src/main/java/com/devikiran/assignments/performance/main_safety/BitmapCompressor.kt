package com.devikiran.assignments.performance.main_safety

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

class BitmapCompressor(
    private val context: Context
) {

    suspend fun compressImage(
        contentUri: Uri,
        compressionThreshold: Long
    ): Bitmap? {
        val inputBytes = context
            .contentResolver
            .openInputStream(contentUri)?.use { inputStream ->
                inputStream.readBytes()
            } ?: return null

        val bitmap = BitmapFactory.decodeByteArray(inputBytes, 0, inputBytes.size)

        var outputBytes: ByteArray
        var quality = 100
        do {
            ByteArrayOutputStream().use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                outputBytes = outputStream.toByteArray()
                quality -= (quality * 0.1).roundToInt()
            }
        } while (outputBytes.size > compressionThreshold && quality > 5)


        return BitmapFactory.decodeByteArray(outputBytes, 0, outputBytes.size)
    }
}