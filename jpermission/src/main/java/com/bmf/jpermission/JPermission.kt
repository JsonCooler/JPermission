package com.bmf.jpermission

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

object JPermission {
    private const val TAG = "EmptyFragment"
    //申请权限
    fun apply(
        activity: FragmentActivity,
        vararg permission: String,
        callback: PermissionCallback
    ) {
        val manager = activity.supportFragmentManager
        val addedFragment = manager.findFragmentByTag(TAG)
        val permissionFragment = addedFragment?.let {
            it as EmptyFragment
        } ?: addFragment(manager)

        permissionFragment.applyPermission(callback, *permission)
    }

    /**
     * 第一次添加fragment
     */
    private fun addFragment(manager: FragmentManager): EmptyFragment {
        val fragment = EmptyFragment()
        manager.beginTransaction().add(fragment, TAG).commitNow()
        return fragment
    }
}