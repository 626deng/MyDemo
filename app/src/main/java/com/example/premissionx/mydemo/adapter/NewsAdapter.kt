package com.example.premissionx.mydemo.adapter

import Data
import NewsResponse
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.Utils
import com.example.premissionx.mydemo.activity.NewsDetailsActivity
import com.example.premissionx.mydemo.database.MyDatabaseHelper
class NewsAdapter(val context:Context,val list:List<Data>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView=itemView.findViewById(R.id.imageView)
        val textView1:TextView=itemView.findViewById(R.id.textView1)
        val textView2:TextView=itemView.findViewById(R.id.textView2)
        val textView3:TextView=itemView.findViewById(R.id.textView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view=LayoutInflater.from(context).inflate(R.layout.news_item,parent,false)
        val holder=ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            val news=list[position]
            val intent=Intent(context,NewsDetailsActivity::class.java).apply {
                putExtra("url",news.url)
                putExtra("text1",news.title)
                putExtra("text2",news.author_name)
                putExtra("text3",news.date)
                putExtra("uniqueKey",news.uniqueke)
//                putExtra("pic_url",news.thumbnail_pic_s)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news=list[position]
        Glide.with(context).load(news.thumbnail_pic_s).into(holder.imageView)
        holder.textView1.text=news.title
        holder.textView2.text=news.author_name
        holder.textView3.text=news.date
    }
}

