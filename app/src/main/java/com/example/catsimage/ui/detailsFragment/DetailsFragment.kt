package com.example.catsimage.ui.detailsFragment

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.catsimage.R
import com.example.catsimage.data.remote.retrofit.responce.CatsPhoto
import com.example.catsimage.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val arg by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val photo = arg.photo

            Glide.with(context)
                .load(photo.url)
                .error(R.drawable.ic_baseline_error_24)
                .into(image_details)

            appCompatButton.setOnClickListener {
                val bitmap: Bitmap = binding.imageDetails.drawable.toBitmap()
                saveImage(photo, bitmap)
            }
        }
    }

    private fun saveImage(catPhoto: CatsPhoto, bitmap: Bitmap) {

        val myImageUri = getUri(catPhoto)
        Log.d("MyTag", "Save image URI $myImageUri")

        if (myImageUri != null) {
            context?.contentResolver?.openOutputStream(myImageUri).use { stream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)) {
                    Toast.makeText(context, "Save failed", Toast.LENGTH_SHORT).show()
                    stream?.close()
                } else {
                    Toast.makeText(context, "Picture saved", Toast.LENGTH_SHORT).show()
                    stream?.close()
                }
            }
        }
    }

    private fun getUri(catPhoto: CatsPhoto): Uri? {
        val resolver = context?.applicationContext?.contentResolver

        val imageCollection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL_PRIMARY
                )
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

        Log.d("MyTag", "Image Collection $imageCollection")

        val newImage = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "${catPhoto.id}.jpeg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return resolver?.insert(imageCollection, newImage)
    }
}