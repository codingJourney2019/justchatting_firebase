package com.example.justchatting.repository.chattingRoom

import androidx.lifecycle.LiveData
import com.example.justchatting.Message
import com.example.justchatting.UserModel
import com.example.justchatting.data.chattingRoom.ChattingRoomFirebaseSource
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ChattingRoomRepositoryImpl(private val chattingRoomFirebaseSource: ChattingRoomFirebaseSource) :
    ChattingRoomRepository {

    companion object {
        val TAG = "ChattingRoomRepo"
    }
    override fun getChatLogs(): LiveData<ArrayList<Message>> {
        return chattingRoomFirebaseSource.logs
    }
    override fun getNewGroupId(): LiveData<String> {
        return chattingRoomFirebaseSource.newGroupId
    }
    override fun loadGroupNameList(groupMembersMap: HashMap<String, UserModel>) {
        chattingRoomFirebaseSource.loadGroupNameList(groupMembersMap)
    }

//    override fun fetchChatLog(groupId: String) {
//        chattingRoomFirebaseSource.fetchChatLog(groupId)
//    }

    override fun setListener(groupId: String) {
        chattingRoomFirebaseSource.setListener(groupId)
    }

    override fun createGroupId(groupMembersMap: HashMap<String, UserModel>) {
        chattingRoomFirebaseSource.createGroupId(groupMembersMap)
    }

    override fun sendText(text: String, groupId: String) {
        chattingRoomFirebaseSource.sendText(text, groupId)
    }


}