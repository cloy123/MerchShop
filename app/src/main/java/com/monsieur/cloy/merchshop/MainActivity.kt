package com.monsieur.cloy.merchshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monsieur.cloy.data.api.TestClass
import com.monsieur.cloy.merchshop.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bt.setOnClickListener {
            GlobalScope.launch {
                val result = TestClass().start()
                withContext(Dispatchers.Main){
                    binding.tv.text = result
                }
            }
        }
    }

}