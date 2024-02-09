package com.stellantis.space.ui.step

import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.stellantis.space.R.id
import com.stellantis.space.ui.activities.MainActivity
import com.stellantis.space.ui.utility.PageClass
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher

class HomePageSteps : PageClass() {
    companion object {
        const val BOTTOM_NAVIGATION_VIEW = id.mainBottomNavigationView
        const val BOTTOM_NAVIGATION_ITEMS_HOME = id.home
        const val BOTTOM_NAVIGATION_ITEMS_NAVIGATION = id.navigation
        const val BOTTOM_NAVIGATION_ITEMS_TRIPS = id.trips
        const val BOTTOM_NAVIGATION_ITEMS_VEHICLE = id.vehicle
    }

    fun allowAccessLocation(act: ActivityScenario<MainActivity>) {
        grantPermission("android.permission.ACCESS_COARSE_LOCATION",act)
        grantPermission("android.permission.ACCESS_FINE_LOCATION",act)
        grantPermission("android.permission.POST_NOTIFICATIONS",act)
    }
    fun verifyPendingActionsIsDisplayed(){
        onView(withText("3 Pending actions")).check(matches(isDisplayed()))
    }



    fun bottomNavigationTripsHome(){
        buttomNavigationClick(allOf(
            ViewMatchers.withId(BOTTOM_NAVIGATION_ITEMS_HOME), ViewMatchers.withContentDescription("Home"),
            childAtPosition(
                childAtPosition(
                    ViewMatchers.withId(BOTTOM_NAVIGATION_VIEW),
                    0
                ),0
            ),
            isDisplayed()))
    }

    fun bottomNavigationTripsNavigation(){
            buttomNavigationClick(allOf(
                ViewMatchers.withId(BOTTOM_NAVIGATION_ITEMS_NAVIGATION), ViewMatchers.withContentDescription("Navigation"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(BOTTOM_NAVIGATION_VIEW),
                        0
                    ),
                    1
                ),
                isDisplayed()))
        }

    fun bottomNavigationTripsTap(){
        onView(isRoot()).perform(wait(withId(BOTTOM_NAVIGATION_ITEMS_TRIPS)))
        buttomNavigationClick(allOf(
            withId(BOTTOM_NAVIGATION_ITEMS_TRIPS), ViewMatchers.withContentDescription("Trips"),
            childAtPosition(
                childAtPosition(withId(BOTTOM_NAVIGATION_VIEW),
                    0
                ),
                2
            ),
            isDisplayed()))
    }



    fun bottomNavigationTripsVehicle(){
        buttomNavigationClick(allOf(
            ViewMatchers.withId(BOTTOM_NAVIGATION_ITEMS_VEHICLE), ViewMatchers.withContentDescription("Vehicle"),
            childAtPosition(
                childAtPosition(
                    ViewMatchers.withId(BOTTOM_NAVIGATION_VIEW),
                    0
                ),
                3
            ),
            isDisplayed()))
    }




}

