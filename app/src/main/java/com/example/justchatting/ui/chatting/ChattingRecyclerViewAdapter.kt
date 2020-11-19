package com.example.justchatting.ui.chatting

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.justchatting.LatestMessage
import com.example.justchatting.R
import com.example.justchatting.ui.chattingRoom.ChattingRoomActivity
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChattingRecyclerViewAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mChattingList : ArrayList<LatestMessage>? = null

    fun setChattingList(arrayList: ArrayList<LatestMessage>){
        this.mChattingList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_item, parent,false)
        return ChattingViewHolder(view)
    }


    override fun getItemCount(): Int {
        if(mChattingList == null)
            return 0
        return mChattingList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChattingViewHolder).bind(mChattingList!![position])
    }

    inner class ChattingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val members = itemView.findViewById<TextView>(R.id.chatting_members)
        val lastMessage = itemView.findViewById<TextView>(R.id.chatting_last_message)
        val timeStamp  = itemView.findViewById<TextView>(R.id.chatting_timestamp)
        val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.chatting_constraint_layout)

        var chattingModel : LatestMessage? = null

        @SuppressLint("SimpleDateFormat")
        fun bind(latestMessage: LatestMessage){
            this.chattingModel = latestMessage
            this.lastMessage.text = latestMessage.lastMessage
            try {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                timeStamp.text = simpleDateFormat.format(Date(latestMessage.timeStamp)).toString()
            }catch (e: Exception){}

            var title : String =""

            if(latestMessage.membersNameList.size>0) {
                for (i in 0 until latestMessage.membersNameList.size - 1) {
                    title += latestMessage.membersNameList[i] + ", "
                }
                title += latestMessage.membersNameList[latestMessage.membersNameList.size - 1]
            }
            members.text = title
            constraintLayout.setOnClickListener {
                val intent = Intent(itemView.context, ChattingRoomActivity::class.java)
                intent.putExtra("groupId", latestMessage.groupId)
                intent.putExtra("groupMembersMap", latestMessage.membersIdMap)
                itemView.context.startActivity(intent)
            }
        }
    }
}