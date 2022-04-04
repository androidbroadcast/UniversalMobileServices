package dev.androidbroadcast.hms.ktx

import com.huawei.hmf.tasks.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

public suspend fun <T> Task<T>.await(): T {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) {
                throw CancellationException("Task $this was cancelled normally.")
            } else {
                result as T
            }
        } else {
            throw e
        }
    }

    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener { task ->
            val e = task.exception
            if (e == null) {
                if (task.isCanceled) cont.cancel() else cont.resume(task.result as T)
            } else {
                cont.resumeWithException(e)
            }
        }
    }
}