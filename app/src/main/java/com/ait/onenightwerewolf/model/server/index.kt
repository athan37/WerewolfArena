package com.ait.onenightwerewolf.model.server

import com.ait.onenightwerewolf.model.Player
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun createGameRoom(room: GameRoom) {
    val db = Firebase.firestore
    db.collection("gameRooms").document(room.id).set(room)
}

fun joinGameRoom(roomCode: String, player: Player, onSuccess: (GameRoom) -> Unit, onFailure: () -> Unit) {
    val db = Firebase.firestore
    db.collection("gameRooms").whereEqualTo("code", roomCode).get()
        .addOnSuccessListener { snapshot ->
            if (snapshot.isEmpty) {
                onFailure()
            } else {
                val room = snapshot.documents.first().toObject(GameRoom::class.java)
                if (room != null) {
                    room.players = room.players + player
                    db.collection("gameRooms").document(room.id).set(room)
                    onSuccess(room)
                } else {
                    onFailure()
                }
            }
        }
        .addOnFailureListener {
            onFailure()
        }
}

fun listenToGameRoomUpdates(roomId: String, onUpdate: (GameRoom) -> Unit) {
    val db = Firebase.firestore
    db.collection("gameRooms").document(roomId)
        .addSnapshotListener { snapshot, error ->
            if (error == null && snapshot != null) {
                val room = snapshot.toObject(GameRoom::class.java)
                if (room != null) {
                    onUpdate(room)
                }
            }
        }
}

fun sendMessage(roomId: String, message: Message) {
    val db = Firebase.firestore
    db.collection("gameRooms").document(roomId).update("messages", FieldValue.arrayUnion(message))
}
