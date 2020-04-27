package com.bmf.jpermission

import android.content.pm.PackageManager
import android.util.Log
import androidx.fragment.app.Fragment

/**
 * 空的fragment 用来申请权限
 */
const val PERMISSION_CODE = 1

typealias PermissionCallback = (isAllGranted: Boolean, defined: List<String>) -> Unit

class EmptyFragment : Fragment() {
    private var callback: PermissionCallback? = null
    /**
     * 权限申请
     */
    fun applyPermission(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CODE) {
            val definedPermissionList = mutableListOf<String>()
            //是否有权限被拒绝了
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    //将拒绝得权限添加到集合
                    definedPermissionList.add(permissions[index])
                }
            }
            val allGranted = definedPermissionList.isEmpty()

            callback?.let { it(allGranted, definedPermissionList) }

        }
    }

}
