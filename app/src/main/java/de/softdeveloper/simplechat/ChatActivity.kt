package de.softdeveloper.simplechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.softdeveloper.simplechat.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}