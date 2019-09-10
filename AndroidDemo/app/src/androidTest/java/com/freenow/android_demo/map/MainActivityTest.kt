package com.freenow.android_demo.map

import android.Manifest
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.freenow.android_demo.R.string
import com.freenow.android_demo.activities.MainActivity
import com.freenow.android_demo.models.User
import com.freenow.android_demo.utils.storage.SharedPrefStorage
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import android.support.test.espresso.action.ViewActions.scrollTo
import com.freenow.android_demo.auth.LoginPage
import com.freenow.android_demo.utils.waitActivityVisible

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var mainActivityPage: MainActivityPage
    private lateinit var loginPage: LoginPage
    private lateinit var context: Context
    private lateinit var sharedPrefStorage: SharedPrefStorage

    //test data
    private val username = "crazydog335"
    private val password = "venture"
    private val salt = "wp3zuBv7"
    private val sha = "W9726ae7ce314e547ac606efc5afb1070439097903333de7c5050753d5435d72f"

    @Rule
    @JvmField
    var runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION)

    @get:Rule val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        sharedPrefStorage = SharedPrefStorage(context)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    @Throws(Exception::class)
    fun testLayout() {
        launchMainActivity()

        with(mainActivityPage) {
            onDrawerLayout().check(matches(isDisplayed()))
            onTextSearchEditText().check(matches(withHint(string.text_hint_driver)))
            onLocationFloatButton().check(matches(isDisplayed()))
            onMapView().check(matches(isDisplayed()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun testDriverSearch_driversList() {
        launchMainActivity()

        //given
        val shortWord = "sa"
        val driverName = "Sarah Scott"

        //when
        mainActivityPage.onTextSearchEditText()
                .perform(typeText(shortWord))

        //then
        mainActivityPage.getTextSearchElementByText(driverName, activityRule.activity)
                .perform(scrollTo()).perform(click())
    }


    @Test
    @Throws(Exception::class)
    fun testLogIn() {
        clearSharedPreferences()
        activityRule.launchActivity(null)

        //given
        loginPage = LoginPage()

        //when
        loginPage.onUsernameEditText().perform(ViewActions.typeText(username))
        loginPage.onPasswordEditText().perform(ViewActions.typeText(password))
        loginPage.onLoginButton().perform(click())

        //then
        waitActivityVisible<MainActivity>()
    }

    private fun launchMainActivity() {
        setSharedPreferences()
        activityRule.launchActivity(null)

        mainActivityPage = MainActivityPage()
        mainActivityPage.verifyPage()
    }

    private fun clearSharedPreferences() {
        sharedPrefStorage.resetUser()
    }

    private fun setSharedPreferences() {
        sharedPrefStorage.saveUser(User(username, salt, sha))
    }
}
