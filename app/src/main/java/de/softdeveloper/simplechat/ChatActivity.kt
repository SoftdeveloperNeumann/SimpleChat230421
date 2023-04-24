package de.softdeveloper.simplechat

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import de.softdeveloper.simplechat.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    private lateinit var userName: String
    private lateinit var chatPartner: String

    private var db = Firebase.database
    private lateinit var reference1: DatabaseReference
    private lateinit var reference2: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serviceIntent = Intent(this, NotificationService::class.java)
        stopService(serviceIntent)
        userName = intent.getStringExtra("user") ?: "unbekannt"
        chatPartner = intent.getStringExtra("other") ?: "unbekannt"

        reference1 = db.getReference("messages/${userName}_$chatPartner")
        reference2 = db.getReference("messages/${chatPartner}_$userName")

        binding.include.btnSend.setOnClickListener {
            val messageText = binding.include.editTextTextPersonName.text.toString()
            if (messageText.isNotBlank()) {
                val map = hashMapOf(
                    "message" to messageText,
                    "user" to userName
                )

                reference1.push().setValue(map)
                reference2.push().setValue(map)
                binding.include.editTextTextPersonName.text.clear()
            }
        }

        reference1.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val map = snapshot.value as HashMap<*, *>
                val user = map["user"].toString()
                val message = map["message"].toString()

                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.cancel(1)
                manager.cancelAll()

                if (user == userName) {
                    addMessage("Du: \n$message", 1)
                } else {
                    addMessage("$chatPartner: \n$message", 2)
                }

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

    override fun onDestroy() {
        super.onDestroy()
        val serviceIntent = Intent(this, NotificationService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
//                applicationContext.startForegroundService(serviceIntent)
        }
    }

    private fun addMessage(message: String, type: Int) {
        val textView = TextView(this)
        textView.text = message
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.weight = 1f
        if (type == 1) {
            layoutParams.gravity = Gravity.END
            textView.setBackgroundResource(R.drawable.bubble_out)
        } else {
            layoutParams.gravity = Gravity.START
            textView.setBackgroundResource(R.drawable.bubble_in)
        }
        textView.layoutParams = layoutParams
        binding.chatContainer.addView(textView)
        binding.scrollView.fullScroll(View.FOCUS_DOWN)
    }
}