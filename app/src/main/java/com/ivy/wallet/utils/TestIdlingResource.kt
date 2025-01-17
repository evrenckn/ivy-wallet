package com.ivy.wallet.utils

import androidx.compose.ui.test.IdlingResource
import com.ivy.wallet.BuildConfig
import java.util.concurrent.atomic.AtomicInteger

object TestIdlingResource {
    private val counter = AtomicInteger(0)

    @JvmField
    val idlingResource = object : IdlingResource {
        override val isIdleNow: Boolean
            get() = counter.get() == 0
    }

    fun increment() {
        counter.incrementAndGet()
    }

    fun decrement() {
        if (!idlingResource.isIdleNow) {
            counter.decrementAndGet()
        }

        if (counter.get() < 0 && BuildConfig.DEBUG) {
            throw IllegalStateException("TestIdlingResource counter is corrupted! value = ${counter.get()}")
        }
    }

    fun reset() {
        counter.set(0)
    }

    fun get(): Int {
        return counter.get()
    }
}