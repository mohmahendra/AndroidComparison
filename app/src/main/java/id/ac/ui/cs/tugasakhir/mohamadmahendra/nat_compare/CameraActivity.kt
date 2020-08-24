package id.ac.ui.cs.tugasakhir.mohamadmahendra.nat_compare

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity


class CameraActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    @VisibleForTesting
    protected val KEY_IMAGE_DATA = "data"
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_preview)

        imageView = findViewById(R.id.imageView)
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    fun onOpenCamera(view : View) {
        takePicture()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE){
            val extras : Bundle? = data?.extras;
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return
            }
            val imageBitmap = extras[KEY_IMAGE_DATA] as Bitmap?
            imageView.setImageBitmap(imageBitmap)
        }
    }
}