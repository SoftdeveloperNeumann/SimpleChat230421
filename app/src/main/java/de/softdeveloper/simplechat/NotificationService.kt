package de.softdeveloper.simplechat

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

import com.google.firebase.database.*
import com.google.firebase.database.core.SnapshotHolder

class NotificationService : Service() {

    lateinit var db:FirebaseDatabase
    lateinit var reference1 :DatabaseReference
    lateinit var userName:String
    lateinit var chatPartner:String
    var count = 0L

    override fun onCreate() {
        super.onCreate()
        db = FirebaseDatabase.getInstance()
        userName = MainActivity.userName
        chatPartner = MainActivity.chatPartner
        reference1 = db.getReference("messages/${userName}_$chatPartner")


        reference1.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val typeIndicator = object : GenericTypeIndicator<Map<String,String>>(){}
                val map = snapshot.getValue(typeIndicator)
                val user = map?.get("user")
                val message = map?.get("message")
                Log.e("TAG", "onChildAdded: $count : ${snapshot.childrenCount}  ", )
                if(user != userName && count < snapshot.childrenCount){
                    sendNotification(message)
                }
                count = snapshot.childrenCount

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }



    override fun onBind(intent: Intent): IBinder? {
        return null
    }
    private fun sendNotification(message: String?) {
        val NOTIFICATION_CHANNEL_ID = packageName
        val channelName = "SimpleChatMessage"

        val intent = Intent(this, ChatActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("user",userName)
            putExtra("other",chatPartner)
        }

        val pendingIntent = PendingIntent.getActivity(applicationContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID,channelName,NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val builder = NotificationCompat.Builder(applicationContext,NOTIFICATION_CHANNEL_ID)
            val notification = builder.setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Sie haben eine neue Nachricht")
                .setContentText(message)
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

                .build()

            startForeground(1,notification)


        }else{
            startForeground(2, Notification())
        }

    }
}


















