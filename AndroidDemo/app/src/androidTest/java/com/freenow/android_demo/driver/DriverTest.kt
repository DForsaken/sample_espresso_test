package com.freenow.android_demo.driver

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.freenow.android_demo.activities.DriverProfileActivity
import com.freenow.android_demo.models.Driver
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import java.text.SimpleDateFormat

@RunWith(AndroidJUnit4::class)
class DriverTest {
    private lateinit var driverPage: DriverPage
    private lateinit var context: Context

    companion object {
        private fun createIntent(context: Context): Intent {
            //TODO: Use mockito for mocking the actual call
            var registeredDate = SimpleDateFormat("yyyy-MM-dd").parse("2009-06-11T04:18:50Z")
            val driver = Driver(
                            "bigbear730",
                            "ttttt",
                            "https://randomuser.me/api/portraits/men/90.jpg",
                                    "4209 clyde street",
                            registeredDate)

            return DriverProfileActivity.createContextIntent(context, driver)
        }
    }

    @get:Rule val activityRule = ActivityTestRule(DriverProfileActivity::class.java, false, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        activityRule.launchActivity(createIntent(context))

        driverPage = DriverPage()
        driverPage.verifyPage()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }

    @Test
    @Throws(Exception::class)
    fun testLayout() {
        with(driverPage) {
            onDriverAvatarImage().check(matches(isDisplayed()))
            onDriverNameText().check(matches(isDisplayed()))
            onDriverLocationImage().check(matches(isDisplayed()))
            onDriverLocationText().check(matches(isDisplayed()))
            onDriverDateImage().check(matches(isDisplayed()))
            onDriverDateText().check(matches(isDisplayed()))
            onPhoneCallFloatButton().check(matches(isDisplayed()))
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCallButton_toCallIntent() {
        Intents.init()

        //when
        driverPage.onPhoneCallFloatButton().check(matches(isDisplayed()))
        driverPage.onPhoneCallFloatButton().perform(click())

        //then
        intended(hasAction(Intent.ACTION_DIAL))
        Intents.release()
    }
}
