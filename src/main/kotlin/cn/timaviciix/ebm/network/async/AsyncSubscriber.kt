package cn.timaviciix.ebm.network.async

data class AsyncSubscriber<E>(
    val startUpload: () -> Unit,
    val onUploading: () -> Unit,
    val onFailed: (result: E?) -> Unit,
    val complete: (result: E?) -> Unit,
    val onTimeout: () -> Unit
) {
    var response: E? = null
}