package com.freenow.android_demo.auth

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.freenow.android_demo.R.string
import com.freenow.android_demo.activities.AuthenticationActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class LoginTest {
    private lateinit var loginPage: LoginPage
    private lateinit var context: Context

    @get:Rule val activityRule = ActivityTestRule(AuthenticationActivity::class.java, false, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        activityRule.launchActivity(null)

        loginPage = LoginPage()
        loginPage.verifyPage()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }

    @Test
    @Throws(Exception::class)
    fun testLayout() {
        with(loginPage) {
            onContentParent().check(matches(isDisplayed()))
            onUsernameEditText().check(matches(isDisplayed()))
            onPasswordEditText().check(matches(isDisplayed()))
            onLoginButton().check(matches(withText(string.button_login)))
        }
    }
}
