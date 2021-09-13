package `in`.sarangal.kotlinsupplement

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface

/**
 * Drawable Extension Function
 * */
const val TAG = "GraphicsExtension"

/**
 * @return Get Bitmap from Drawable
 * */
fun Drawable.getBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(
        this.intrinsicWidth,
        this.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    this.setBounds(0, 0, canvas.width, canvas.height)
    this.draw(canvas)
    return bitmap
}

/**
 * Rotate the Bitmap if required
 *
 * @param context Context Reference
 * @param bitmapImage Object of Bitmap
 * @param selectedImage Uri Reference of Image
 *
 * @return Rotated Bitmap
 * */
fun rotateImageIfRequired(context: Context, bitmapImage: Bitmap, selectedImage: Uri): Bitmap? {
    try {
        var secondTry = true
        if (selectedImage.scheme == "content") {
            secondTry = try {
                val projection = arrayOf(MediaStore.Images.ImageColumns.ORIENTATION)
                val c = context.contentResolver.query(
                    selectedImage, projection, null,
                    null, null
                )
                if (c?.moveToFirst() == true) {
                    val rotation = c.getInt(0)
                    c.close()
                    return rotateImage(bitmapImage, rotation)
                }
                return bitmapImage
            } catch (e: Exception) {
                logger(TAG, "rotateImageIfRequired - secondTry: ${e.message}", LogType.ERROR)
                true
            }
        }
        if (secondTry) {
            return modifyOrientation(bitmapImage, selectedImage.path ?: "")
        }
    } catch (e: Exception) {
        logger(TAG, "rotateImageIfRequired: ${e.message}", LogType.ERROR)
        return bitmapImage
    }
    return bitmapImage
}

/**
 * @return Changed Orientation of the Bitmap
 *
 * @param bitmapImage Bitmap Image
 * @param imageAbsolutePath Absolute Path of Image File
 * */
fun modifyOrientation(bitmapImage: Bitmap, imageAbsolutePath: String): Bitmap? {
    val ei = ExifInterface(imageAbsolutePath)
    return when (val orientation = ei.rotationDegrees) {

        /* Dependency Add: implementation "androidx.exifinterface:exifinterface:X.X.X" */
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmapImage, 90)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmapImage, 180)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmapImage, 270)
        ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flip(
            bitmapImage,
            horizontal = true,
            vertical = false
        )
        ExifInterface.ORIENTATION_FLIP_VERTICAL -> flip(
            bitmapImage,
            horizontal = false,
            vertical = true
        )
        else -> rotateImage(bitmapImage, orientation)
    }
}

/**
 * @return Flipped Bitmap
 *
 * @param bitmap Bitmap Image
 * @param horizontal Pass TRUE if want to flip horizontally else FALSE
 * @param vertical Pass TRUE if want to flip vertically else FALSE
 * */
fun flip(bitmap: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap? {
    return try {
        val matrix = Matrix()
        matrix.preScale(if (horizontal) -1f else 1.toFloat(), if (vertical) -1f else 1.toFloat())
        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    } catch (e: Exception) {
        logger(TAG, "flip: ${e.message}", LogType.ERROR)
        bitmap
    }
}

/**
 * @return Rotated Bitmap with Rotation Degree Value
 *
 * @param img Bitmap
 * @param degree Rotation Angle
 * */
fun rotateImage(img: Bitmap, degree: Int): Bitmap? {
    return try {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
    } catch (e: Exception) {
        logger(TAG, "rotateImage: ${e.message}", LogType.ERROR)
        img
    }
}

/**
 * @return Gradient Bitmap Image
 *
 * @param startColor    Start Color
 * @param endColor      End Color
 * */
fun Bitmap.addGradient(startColor: Int, endColor: Int): Bitmap {
    val width = this.width
    val height = this.height
    val updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(updatedBitmap)
    canvas.drawBitmap(this, 0F, 0F, null)
    val paint = Paint()
    if (startColor == endColor) {
        paint.colorFilter = PorterDuffColorFilter(startColor, PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(this, 0F, 0F, paint)
    } else {
        val shader =
            LinearGradient(
                0F, 0F, 0F, height.toFloat(),
                startColor, endColor, Shader.TileMode.CLAMP
            )
        paint.shader = shader
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)
    }

    return updatedBitmap ?: this
}

