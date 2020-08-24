package id.ac.ui.cs.tugasakhir.mohamadmahendra.nat_compare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_tweet.view.*

class ListAdapter(private val myData: MutableList<Tweet>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.rv_tweet, parent, false)
        return ListViewHolder(inflatedView)
    }
    override fun getItemCount(): Int {
        return myData.size
    }
    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        val itemTweet : Tweet = myData[position]
        holder.bind(itemTweet)
    }
    class ListViewHolder(val inflView:View) : RecyclerView.ViewHolder(inflView) {
        private var view : View = inflView
        fun bind(tweet: Tweet){
            view.tv_fname.text = tweet.fullname
            view.tv_uname.text = tweet.username
            view.tv_message.text = tweet.message
            view.tv_minutes.text = tweet.minutes
        }
    }
}