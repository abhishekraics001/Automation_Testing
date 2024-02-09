package com.stellantis.space.ui.activities.assertion

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.stellantis.space.R.id
import com.stellantis.space.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class Mainactivity {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testApp(){
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(id.logoPicto)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(id.navigation_host_bottom_sheet_behavior))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
    }
}
