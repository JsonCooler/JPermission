package com.bmf.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import com.bmf.myapplication.databinding.ActivityFragmentContainerBinding
import com.bmf.myapplication.fragment.CameraFragment
import com.bmf.myapplication.fragment.EmailFragment
import com.bmf.myapplication.fragment.MoreFragment
import com.bmf.myapplication.fragment.MyViewModel
import com.gyf.immersionbar.ktx.immersionBar
import java.lang.RuntimeException
import kotlin.math.log

/**
 * fragment容器activity
 */
class FragmentContainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentContainerBinding

    private var currentTag = ""//当前tag
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        immersionBar {
            titleBar(binding.toolbar)
            statusBarDarkFont(true)
        }
        switchFragment(0, EmailFragment.tag)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.email -> {
                    switchFragment(0, EmailFragment.tag)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.more -> {
                    switchFragment(1, MoreFragment.tag)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.camera -> {
                    switchFragment(2, CameraFragment.tag)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }


    /**
     * 切换fragment
     */
    private fun switchFragment(position: Int, tag: String) {

        val fragmentManager = supportFragmentManager
        val tagFragment = fragmentManager.findFragmentByTag(tag)

        val currentFragment = fragmentManager.findFragmentByTag(currentTag)
        //当前tag就等于要显示的tag
        currentTag = tag

        currentFragment?.let { fragmentManager.beginTransaction().hide(it).commit() }

        if (tagFragment != null) {
            fragmentManager.beginTransaction().show(tagFragment).commit()
        } else {
            val fragment = when (position) {
                0 -> EmailFragment()
                1 -> MoreFragment()
                2 -> CameraFragment()
                else -> throw RuntimeException("没有此fragment")
            }
            fragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit()
        }
    }
}