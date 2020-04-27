package com.bmf.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bmf.myapplication.databinding.FragmentCameraBinding


class CameraFragment : Fragment() {
    companion object {
        val tag: String = CameraFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentCameraBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.web1.loadUrl("https://www.baidu.com")
        binding.web2.loadUrl("https://www.baidu.com")
        binding.web3.loadUrl("https://www.baidu.com")
    }

}