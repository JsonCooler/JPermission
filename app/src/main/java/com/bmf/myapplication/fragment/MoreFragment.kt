package com.bmf.myapplication.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bmf.myapplication.BR
import com.bmf.myapplication.R
import com.bmf.myapplication.bean.PojoBean
import com.bmf.myapplication.databinding.FragmentMoreBinding
import com.bmf.myapplication.databinding.ItemLayoutBinding
import com.bmf.myapplication.layoutmanager.MarginLayoutManger
import com.bmf.myapplication.next.HAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sora.iadapter.library.IAdapter
import com.sora.iadapter.library.RecyclerViewBindings
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.log
import kotlin.properties.Delegates

class MoreFragment : Fragment() {

    companion object {
        val tag: String = MoreFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentMoreBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        test()


        gesae()
    }

    private fun test() {
        val charArray = listOf("sdsad", "dsd", "acdd", "cc", "ecdg")
        val (first, second) = charArray.partition {
            it.length > 2
        }
        Log.d("test", "$first")
        Log.d("test", "$second")
        val ise = listOf("13", "323", "111").none {
            it.length > 4
        }
//        Log.d("test", "有没有一个满足条件：$ise")
    }

    private fun gesae() {
        runBlocking {
            launch {
                doWorld()
            }
            println("hello")
        }
    }

    private suspend fun doWorld() {
//        delay(1000)
        println("world")
    }

}
