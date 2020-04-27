package com.bmf.myapplication.net

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmf.myapplication.api.Result
import com.bmf.myapplication.viewmodel.IViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.CoroutineContext


suspend fun <T> fetchData(
    dis: CoroutineContext = Dispatchers.IO,
    remote: suspend () -> Result<T>
): Result<T> = withContext(dis) {
    remote()
}

fun ViewModel.request(
    success: suspend () -> Unit,
    fail: (Exception) -> Unit
) {
    viewModelScope.launch {
        try {
            success()
        } catch (e: Exception) {
            fail(e)
        }
    }
}

fun test() {

}