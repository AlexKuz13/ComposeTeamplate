package com.alexkuz.data.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import com.alexkuz.corecommon.Completable
import com.alexkuz.corecommon.Effect
import com.alexkuz.corecommon.R
import com.alexkuz.corecommon.tryRun
import com.alexkuz.data.resources.ResourceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.size
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

private const val MAX_FILE_SIZE = 5 * 1024 * 1024L
private const val PDF_MEDIA_TYPE = "application/pdf"
private const val FILE_PROVIDER_PATH = ".fileprovider"

class FileHelper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val resourceManager: ResourceManager,
) {

    suspend fun compressFile(file: File): File {
        return Compressor.compress(context, file) {
            size(maxFileSize = MAX_FILE_SIZE)
        }
    }

    fun getMimeType(url: String): MediaType? {
        return MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url))
            .orEmpty()
            .toMediaTypeOrNull()
    }

    fun getFileFromUri(uri: Uri): Effect<File> {
        val fileName = uri.getName()
        if (fileName.isNullOrEmpty()) {
            return Effect.error(IllegalArgumentException(resourceManager.getString(R.string.error_no_file_uri)))
        } else {
            val tempFile = File(context.cacheDir, fileName)
            try {
                tempFile.createNewFile()
            } catch (e: Exception) {
                return Effect.error(e)
            }
            var inputStream: InputStream? = null
            var outputStream: FileOutputStream? = null

            try {
                outputStream = FileOutputStream(tempFile)
                inputStream = context.contentResolver.openInputStream(uri)
                inputStream?.copyTo(outputStream)
                outputStream.flush()
            } catch (e: Exception) {
                return Effect.error(e)
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
            return Effect.success(tempFile)
        }
    }

    private fun Uri.getName(): String? {
        return context.contentResolver
            .query(this, null, null, null, null)
            ?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                return cursor.getString(nameIndex)
            }
    }

    fun openFile(file: File, mediaType: String?): Effect<Completable> {
        val uri = FileProvider.getUriForFile(
            context,
            resourceManager.getString(
                R.string.text_file_provider_path,
                context.packageName,
                FILE_PROVIDER_PATH
            ),
            file
        )
        return tryRun {
            Intent().run {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                setDataAndType(uri, mediaType ?: PDF_MEDIA_TYPE)
                context.startActivity(this)
            }
        }.toCompletable()
    }
}

val File.size get() = if (!exists()) 0.0 else length().toDouble()
val File.sizeInKb get() = size / 1024
val File.sizeInMb get() = sizeInKb / 1024
val File.sizeInGb get() = sizeInMb / 1024
val File.sizeInTb get() = sizeInGb / 1024