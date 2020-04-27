package com.bmf.myapplication.fragment

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bmf.jpermission.JPermission
import com.bmf.jpermission.PermissionCallback
import com.bmf.myapplication.R
import com.bmf.myapplication.databinding.FragmentEmailBinding
import com.bmf.myapplication.databinding.PopContentBinding
import com.bmf.myapplication.next.NextActivity
import com.bmf.myapplication.viewmodel.IViewModel


class EmailFragment : Fragment() {
    private val vm: IViewModel by viewModels()

    companion object {
        val tag: String = EmailFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = vm
        //请求banner
        vm.getBanner()
        binding.button.setOnClickListener {
            //            showPop()
            /*  val intent = Intent(context, NextActivity::class.java)
              startActivity(
                  intent,
                  ActivityOptionsCompat.makeSceneTransitionAnimation(
                      requireActivity(),
                      binding.button,
                      "animation123"
                  ).toBundle()
              )*/

            JPermission.apply(
                requireActivity(),
                Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA
            ) { isAllGranted, defined ->
                if (isAllGranted) {//权限被集成
                    Toast.makeText(requireContext(), "权限申请成功", Toast.LENGTH_SHORT).show()
                } else {
                    //doSomething
                    Toast.makeText(requireContext(), "权限被拒绝$defined", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

    /**
     * 显示pop
     */
    private fun showPop() {
        val popWindow =
            PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popWindow.isFocusable = true
        val content = DataBindingUtil.inflate<PopContentBinding>(
            layoutInflater,
            R.layout.pop_content,
            null,
            false
        )

        content.root.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        popWindow.contentView = content.root
        if (popWindow.isShowing) {
            popWindow.dismiss()
        } else {
            activity?.let {
                if (!it.isFinishing) {
                    val location = IntArray(2)
                    binding.button.getLocationOnScreen(location)
                    val popHeight = popWindow.contentView.measuredHeight
                    Log.d("test", "location[1] ${location[1]}")
                    Log.d("test", "top ${binding.button.top}")
                    popWindow.animationStyle = R.style.pop_animation
                    popWindow.showAsDropDown(
                        binding.button,
                        0,
                        -popHeight - binding.button.measuredHeight, Gravity.TOP
                    )

                }
            }
        }
    }
}