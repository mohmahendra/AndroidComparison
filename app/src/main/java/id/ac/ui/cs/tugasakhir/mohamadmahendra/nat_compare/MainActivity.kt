package id.ac.ui.cs.tugasakhir.mohamadmahendra.nat_compare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListAdapter

    private val mHardcode = mutableListOf<Tweet>(
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"),
        Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        adapter = ListAdapter(mHardcode)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(OnScrollListener(linearLayoutManager, adapter, mHardcode))

        fab.setOnClickListener {
            val intent = Intent (this, CameraActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class OnScrollListener(val layoutManager: LinearLayoutManager, val adapter: ListAdapter, val data: MutableList<Tweet>) :
            RecyclerView.OnScrollListener() {
        var previousTotal = 0
        var loading = true
        val visibleThreshold = 9
        var firstVisibleItem = 0
        var visibleItemCount = 0
        var totalItemCount = 0

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                val initialSize = data.size
                updateData(data = data)
                val updatedSize = data.size
                recyclerView.post { adapter.notifyItemRangeInserted(initialSize, updatedSize) }
                loading = true
            }
        }

        fun updateData(data: MutableList<Tweet>) : List<Tweet> {
            kotlin.repeat(20) {
                data.add(Tweet("Fadhlan Hazmi", "@fadhlanhazmi", "Aku lagi item hari ini", "9m"))
            }
            return data
        }
    }
}
