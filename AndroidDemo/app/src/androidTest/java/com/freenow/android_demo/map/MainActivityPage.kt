package com.freenow.android_demo.map

import android.app.Activity
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.freenow.android_demo.BasePage
import com.freenow.android_demo.R.id
import org.hamcrest.CoreMatchers

class MainActivityPage : BasePage() {
    override fun verifyPage() {
        onDrawerLayout()
                .check(matches(isDisplayed()))
    }

    fun onDrawerLayout(): ViewInteraction = onView(withId(id.drawer_layout))

    fun onTextSearchEditText(): ViewInteraction = onView(withId(id.textSearch))

    fun onLocationFloatButton(): ViewInteraction = onView(withId(id.fab))

    fun onMapView(): ViewInteraction = onView(withId(id.map))

    fun getTextSearchElementByText(text: String, activity: Activity): ViewInteraction {
        return onView(ViewMatchers.withText(text))
                .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activity.window.decorView))))
    }
}
