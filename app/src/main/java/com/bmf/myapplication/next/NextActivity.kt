package com.bmf.myapplication.next

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bmf.myapplication.databinding.ActivityNextBinding
import com.gyf.immersionbar.ktx.immersionBar

class NextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNextBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        immersionBar { }
    }
}