package com.freenow.android_demo.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.support.test.InstrumentationRegistry

const val VERIFICATION_TIMEOUT = 2000L
const val VERIFICATION_INTERVAL = 100L

inline fun <reified T : Activity> isActivityVisible(): Boolean {;
    val manager =
            InstrumentationRegistry.getContext().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return manager.getRunningTasks(1)[0].topActivity.className == T::class.java.name
}

// Will use implicit waits since idle resources is overkill for the project
inline fun <reified T : Activity> waitActivityVisible() {
    val startTime = System.currentTimeMillis()

    while (!isActivityVisible<T>()) {
        Thread.sleep(VERIFICATION_INTERVAL)

        if (System.currentTimeMillis() - startTime >= VERIFICATION_TIMEOUT) {
            throw AssertionError(
                    "Activity ${T::class.java.simpleName} not visible after $VERIFICATION_TIMEOUT milliseconds")
        }
    }
}

