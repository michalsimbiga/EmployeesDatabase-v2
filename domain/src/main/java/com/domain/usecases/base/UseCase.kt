package com.domain.usecases.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in Params, out Type> {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        scope: CoroutineScope,
        dispatcher: CoroutineContext,
        params: Params,
        onSuccess: (Type) -> Unit = {},
        onFailure: (Throwable) -> Unit = {}
    ): Job = scope.launch(dispatcher) {
        runCatching { run(params) }
            .onSuccess { onSuccess(it) }
            .onFailure { onFailure(it) }
    }
}