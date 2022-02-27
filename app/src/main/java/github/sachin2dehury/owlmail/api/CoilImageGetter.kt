package github.sachin2dehury.owlmail.api

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.text.Html
import android.util.Base64
import coil.ImageLoader
import coil.request.ImageRequest
import github.sachin2dehury.owlmail.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CoilImageGetter(private val context: Context, private val imageLoader: ImageLoader) :
    Html.ImageGetter {

    override fun getDrawable(source: String?): Drawable {
        val request = ImageRequest.Builder(context)
            .data(source)
            .crossfade(true)
            .placeholder(R.drawable.ic_baseline_cloud_download_24)
            .error(R.drawable.ic_baseline_cloud_download_24)
            .build()

        return runBlocking(Dispatchers.IO) {
            return@runBlocking imageLoader.execute(request).drawable!!
        }
    }

    private fun isDataLatexImage(html: String, source: String): Boolean {
        val imageArray = html.split("<img src=")
        imageArray.forEach { subsequence ->
            if (subsequence.contains(source)) {
                return subsequence.contains("data-latex=\"true\"")
            }
        }
        return false
    }

    private fun getDecodedBitmap(source: String): Bitmap {
        val internalSource = source.removePrefix("data:image/png;base64,")
        val decodedString: ByteArray = Base64.decode(internalSource, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }
}
