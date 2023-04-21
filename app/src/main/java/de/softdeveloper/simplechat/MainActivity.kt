package de.softdeveloper.simplechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.softdeveloper.simplechat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToChat.setOnClickListener {
            val intent = Intent(this,ChatActivity::class.java).apply {
                putExtra("user",binding.etUser.text.toString())
                putExtra("other",binding.etOther.text.toString())
            }
            startActivity(intent)
        }
    }
}