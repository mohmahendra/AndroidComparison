package id.ac.ui.cs.tugasakhir.mohamadmahendra.nat_compare

import android.app.Application
import android.graphics.Color
import android.util.Log
import jp.wasabeef.takt.Seat
import jp.wasabeef.takt.Takt

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Takt.stock(this)
            .seat(Seat.RIGHT_CENTER)
            .color(Color.BLACK)
            .listener {fps ->
                Log.d("frameps", fps.toString() + " fps")
            }
    }
}