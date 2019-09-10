package com.freenow.android_demo.auth

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.freenow.android_demo.Page
import com.freenow.android_demo.R.id

class LoginPage : Page {
    override fun verifyPage() {
        onContentParent()
                .check(matches(isDisplayed()))
    }

    fun onContentParent(): ViewInteraction = onView(withId(id.decor_content_parent))

    fun onUsernameEditText(): ViewInteraction = onView(withId(id.edt_username))

    fun onPasswordEditText(): ViewInteraction = onView(withId(id.edt_password))

    fun onLoginButton(): ViewInteraction = onView(withId(id.btn_login))
}
