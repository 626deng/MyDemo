package com.example.premissionx.mydemo.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.data.Result
import okhttp3.internal.addHeaderLenient

class VideoAdapter(val context:Context,val list:List<Result>):RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val videoView:VideoView=itemView.findViewById(R.id.videoView)
        val text1:TextView=itemView.findViewById(R.id.text1)
        val text2:TextView=itemView.findViewById(R.id.text2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.video_item,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datas=list[position]
        holder.text1.text=datas.title               
        holder.text2.text=datas.author
        holder.videoView.setVideoURI(Uri.parse(datas.share_url))
//        val mediaController=MediaController(context)
//        mediaController.setAnchorView(holder.videoView)
//        holder.videoView.setMediaController(mediaController)
//        holder.videoView.setOnClickListener {
//            holder.videoView.start()
//        }
    }

}


