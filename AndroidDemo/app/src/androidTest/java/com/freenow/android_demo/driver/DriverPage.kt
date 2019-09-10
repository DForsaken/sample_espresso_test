package com.freenow.android_demo.driver

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.freenow.android_demo.BasePage
import com.freenow.android_demo.R.id

class DriverPage : BasePage() {
    override fun verifyPage() {

    }

    fun onDriverAvatarImage(): ViewInteraction = onView(withId(id.imageViewDriverAvatar))

    fun onDriverNameText(): ViewInteraction = onView(withId(id.textViewDriverName))

    fun onDriverLocationImage(): ViewInteraction = onView(withId(id.imageViewDriverLocation))

    fun onDriverLocationText(): ViewInteraction = onView(withId(id.textViewDriverLocation))

    fun onDriverDateImage(): ViewInteraction = onView(withId(id.imageViewDriverDate))

    fun onDriverDateText(): ViewInteraction = onView(withId(id.textViewDriverDate))

    fun onPhoneCallFloatButton(): ViewInteraction = onView(withId(id.fab))
}