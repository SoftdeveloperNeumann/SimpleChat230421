package de.softdeveloper.simplechat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.softdeveloper.simplechat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var userName: String
        lateinit var chatPartner: String
    }

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToChat.setOnClickListener {
            userName = binding.etUser.text.toString()
            chatPartner = binding.etOther.text.toString()
            val intent = Intent(this,ChatActivity::class.java).apply {
                putExtra("user", userName)
                putExtra("other", chatPartner)
            }
//            val serviceIntent = Intent(this, NotificationService::class.java)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                startForegroundService(serviceIntent)
////                applicationContext.startForegroundService(serviceIntent)
//            }

            startActivity(intent)
        }
    }
}