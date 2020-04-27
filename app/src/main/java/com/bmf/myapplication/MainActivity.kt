package com.bmf.myapplication

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.viewbinding.ViewBinding
import com.bmf.myapplication.databinding.ActivityMainBinding
import com.bmf.myapplication.fragment.CameraFragment
import com.bmf.myapplication.fragment.EmailFragment
import com.bmf.myapplication.fragment.MoreFragment
import com.bumptech.glide.Glide
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.hello.setOnClickListener {
            val intent = Intent(this, FragmentContainerActivity::class.java)
            startActivity(intent)
        }


//        startAnimation()


    }

    private fun startAnimation() {
        val animator = ObjectAnimator.ofInt(0, 100)
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            val valut = it.animatedValue as Int
            Log.d("test", "变化值：$valut")
        }
        animator.duration = 5000
        animator.start()

        test

    }

    private val test:()->Int={
        val a=0
        val b=1
         a+b
    }

    private fun  runAction(a:Apis)=a.test()

    interface  Apis {
        fun test()
    }


}
