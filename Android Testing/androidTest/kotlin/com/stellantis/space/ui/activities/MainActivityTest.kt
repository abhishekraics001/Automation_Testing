package com.stellantis.space.ui.activities

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.inetpsa.space.framework.design.avatar.SFAvatar
import com.inetpsa.space.framework.design.buttons.SFButton
import com.inetpsa.space.framework.design.inputs.SFTextInput
import com.stellantis.space.R
import com.stellantis.space.R.id
import com.stellantis.space.features.brand.ui.adapters.LanguageAdapter
import com.stellantis.space.features.brand.ui.authentication.GMAAuthenticationViewModel
import junit.framework.AssertionFailedError
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    protected val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    protected val context: Context = ApplicationProvider.getApplicationContext()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    lateinit var viewModel: GMAAuthenticationViewModel

    private var decorView: View? = null

    @Before
    fun setUp() {
        viewModel = GMAAuthenticationViewModel()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun setTheAppEnvironmentWithPreProdEMEAJeep() {
        println(
            "Setting the app environment variable Start: like: Build Variant: PreProd, Resign: EMEA, Framework " +
                    "Type: " +
                    "App, Brand: Jeep"
        )

        Thread.sleep(2000)
        onView(withId(R.id.logoPicto)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_host_bottom_sheet_behavior)).check(matches(isDisplayed()))
        Thread.sleep(2000)

        //Environment variable selection
        environmentVariableSet()
        Thread.sleep(6000)


        languageSelectionPage()
        Thread.sleep(6000)

        // Login Page Scenario
        //loginPageScenario();
        Thread.sleep(6000)



        //tabClickOnProfileIcon()
        Thread.sleep(600)


        //dashBoardCardClick()
        Thread.sleep(600)


        //buttomNavigationClick()
        Thread.sleep(600)

       // getInstrumentation().waitForIdleSync();
        Thread.sleep(12000)
    }

    private fun environmentVariableSet() {
        onView(withText("PREPROD")).check(matches(isDisplayed())).perform(click())
        onView(withText("EMEA")).check(matches(isDisplayed())).perform(click())
        onView(withText("APP")).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.brandRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.brandRecyclerView)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(6))
        onView(withId(R.id.brandRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                6,
                click()
            )
        )
    }

    private fun languageSelectionPage() {
        try {
            // View is displayed
            //onView(withId(R.id.languageChangeIcon)).check(matches(isDisplayed())).perform(click())
            Thread.sleep(8000)
            onView(withId(R.id.languageItems)).check(matches(isDisplayed()))

            try {

              activityRule.scenario.onActivity { activity ->
                    val recucleview = activity.findViewById<RecyclerView>(R.id.languageItems)
                    val adapter = recucleview.adapter as LanguageAdapter
                    val itemCount = recucleview.adapter?.itemCount ?: 0
                    val selc = adapter.tracker?.selection
                    selc?.let { adapter.tracker?.setItemsSelected(it, true) }
                    Log.e("Language getItemDetails", "start getItemDetails itemCount: $itemCount")

                    Thread.sleep(3000)
                }


                activityRule.scenario.onActivity { activity: MainActivity ->
                    decorView = activity.window.decorView
                    val hasWindowFocus = activity.hasWindowFocus()
                    Log.e("Language hasWindowFocus", "hasWindowFocus: $hasWindowFocus    decorView:${decorView}")
                }

                Thread.sleep(2000)
                onView(withId(R.id.btn_third)).inRoot(RootMatchers.withDecorView(not(decorView)))
                    .check(matches(isDisplayed())).perform(click())


                Thread.sleep(1000)
                //pressBack()

            } catch (e: AssertionFailedError) {
                e.printStackTrace()
                pressBack()
            }

        } catch (e: AssertionFailedError) {
            e.printStackTrace()
            //onView(withId(R.id.authentication_button_signin)).perform(click())
        }
    }


    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null
        override fun getDescription() = "Click on a child view with specified id."
        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById
            (viewId))
    }



    private fun loginPageScenario() {
        try {
            onView(withId(R.id.authentication_button_signin)).perform(click())


            getInstrumentation().waitForIdleSync();
            onView(withId(R.id.email)).check(matches(isDisplayed()))
            onView(withId(R.id.signInLabel))
            onView(withId(R.id.signInLabel)).check(matches(isDisplayed()))

            activityRule.scenario.onActivity { activity ->
                // onView(withId(R.id.email)).perform(click()).perform(pressKey(KeyEvent.KEYCODE_A))
                val emaild = activity.findViewById<SFTextInput>(R.id.email)
                val password = activity.findViewById<SFTextInput>(R.id.password)
                emaild.editText.setText("drm10_15@yopmail.com")
                password.editText.setText("Password04_")

                Thread.sleep(100)

                activity.findViewById<SFButton>(R.id.authentication_button_continue).performClick()

                Thread.sleep(500)
            }
        } catch (e: AssertionFailedError) {
            e.printStackTrace()
        }
    }



    private fun tabClickOnProfileIcon(){
        activityRule.scenario.onActivity { activity ->
            val profileicon = activity.findViewById<SFAvatar>(R.id.tgAvatar)
            profileicon.performClick()
        }
    }


    private fun dashBoardCardClick(){
        onView(withId(R.id.cardsList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                1,
                click()
            )
        )
    }


    private fun buttomNavigationClick(){
        //mainBottomNavigationView
        //onView(withText("Trips")).perform(click())
        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(id.trips), ViewMatchers.withContentDescription("Trips"),
                childAtPosition(
                    childAtPosition(
                        withId(id.mainBottomNavigationView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
