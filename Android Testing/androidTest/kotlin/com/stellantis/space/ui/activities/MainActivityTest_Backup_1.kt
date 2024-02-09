package com.stellantis.space.ui.activities


import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.inetpsa.space.framework.design.cells.SFCell
import com.stellantis.space.R
import com.stellantis.space.features.brand.ui.authentication.GMAAuthenticationViewModel
import com.stellantis.space.features.brand.ui.viewholders.LanguageItemViewHolder
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.any
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest_Backup_1 {


    //private val mCountingIdlingResource = CountingIdlingResource(RESOURCE)
     val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
     val context: Context = ApplicationProvider.getApplicationContext()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    lateinit var viewModel :  GMAAuthenticationViewModel

    @Before
    fun setUp() {
        viewModel = GMAAuthenticationViewModel()
    }

    /*
    @Test()
    fun testEvent() {
        Thread.sleep(1000)
        onView(withId(R.id.navigation_host_activity)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }
*/


  /*  @Test
    fun testEvent2() {
        Thread.sleep(1000)
        onView(withId(R.id.navigation_host_bottom_sheet_behavior)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }


   */

    /*
    @Test
    fun testEvent3() {
        Thread.sleep(1000)
        onView(withId(R.id.overlay_loader)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }

    @Test
    fun testEvent4() {
        Thread.sleep(1000)
        onView(withId(R.id.logoPicto)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }

   */
    /*
       @Test
       fun testEvent5() {
           Thread.sleep(1000)
           onView(withId(R.id.progressIndicator)).check(matches(isDisplayed()))
           Thread.sleep(1000)
       }
       */
    /*
       @Test
       fun testEvent6() {
           Thread.sleep(1000)
           onView(withId(R.id.prodCardView)).check(matches(isDisplayed()))
           Thread.sleep(1000)
       }*/

    /*@Test
    fun testEvent7() {
        Thread.sleep(1000)
        //onData(withText("Jeep")).perform(click())

        // onView(withId(R.id.brandRecyclerView)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(2))
        //onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(10))
        //onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //onView(withId(R.id.recyclerView)).perform( RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))

        Thread.sleep(1000)
    }*/

    @Test
    fun setTheAppEnvironmentWithPreProdEMEAJeep () {
        println("Setting the app environment variable Start: like: Build Variant: PreProd, Resign: EMEA, Framework " +
                "Type: " +
                "App, Brand: Jeep")
        Thread.sleep(4000)
        onView(withId(R.id.logoPicto)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_host_bottom_sheet_behavior)).check(matches(isDisplayed()))

        Thread.sleep(4000)

        onView(withText("PREPROD")).check(matches(isDisplayed())).perform(click())
        onView(withText("EMEA")).check(matches(isDisplayed())).perform(click())
        onView(withText("APP")).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.brandRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.brandRecyclerView)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(10))
        onView(withId(R.id.brandRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(10,click ()))

        println("Setting the app environment variable Start: End")
        Thread.sleep(8000)


       // try {
            // View is displayed
            onView(withId(R.id.languageChangeIcon)).check(matches(isDisplayed()))

            getInstrumentation().waitForIdleSync();
           // try {
                val x = onView(
                    allOf(
                        withId(R.id.languageChangeIcon),
                        isDisplayed()
                    )
                ).check(matches(isDisplayed()))

                x.perform(click())

        Thread.sleep(8000)

                getInstrumentation().waitForIdleSync();

                onView(withId(R.id.languageItems)).check(matches(isDisplayed()))
                onView(withId(R.id.languageItems)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(15))

        //Intents.init();

             /*  onView(withId(R.id.languageItems)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                        15,
                        click()
                    )
                )*/

      /*  onView(withId(R.id.languageItems)).perform(RecyclerViewActions.actionOnItemAtPosition<LanguageItemViewHolder>
            (3, clickOnViewChild(R.id.cell)))
*/


/*      onView(withId(R.id.languageItems))
            .perform(
                actionOnItemAtPosition<LanguageItemViewHolder>(
                    0,
                    recyclerChildAction<SFCell>(R.id.cell) {
                        click()
                    }
                )
            )  */
        onView(withId(R.id.languageItems))
            .perform(
                actionOnItemAtPosition<LanguageItemViewHolder>(
                    1,
                    recyclerChildAction<SFCell>(R.id.cell) {
                        click()
                    }
                )
            )


        onView(withText("Belgique | Français")).perform(click());


        //Intents.release();

                getInstrumentation().waitForIdleSync();
          /*  }catch (e: AssertionFailedError){
                e.printStackTrace()
               // pressBack()
            }
        } catch (e: AssertionFailedError) {
            e.printStackTrace()
            //onView(withId(R.id.authentication_button_signin)).perform(click())
        }
*/




    /*
        try {
            onView(withId(R.id.authentication_button_signin)).perform(click())
        }catch (e: AssertionFailedError){
            e.printStackTrace()
        }



        Thread.sleep(2000)


        getInstrumentation().waitForIdleSync();
        println("Start login functionality")
        onView(withId(R.id.signInLabel))
        onView(withId(R.id.signInLabel)).check(matches(isDisplayed()))


        Thread.sleep(100)

     */






      //  onView(withId(R.id.email)).check(matches(isDisplayed()))

        //onView(withId(R.id.email)).perform(ViewActions.typeText("test@gmail.com"))

        //onView(withId(R.id.email)).perform(typeText("test@gmail.com"))





    /*
         onView(allOf(withId(R.id.email), withClassName(Matchers.`is`("com.inetpsa.space.framework.design.inputs
         .SFTextInput")))).perform(click())

          onView(allOf(withId(R.id.email), withClassName(Matchers.`is`("com.inetpsa.space.framework.design.inputs" +
                ".SFTextInput")))).perform(pressKey(KeyEvent.KEYCODE_A))
    */






      /*
        onView(withId(R.id.email)).perform(click()).perform(pressKey(KeyEvent.KEYCODE_A))
        Thread.sleep(100)

        onView(withId(R.id.email)).perform(click()).perform(typeTextIntoFocusedView("test@gmail.com"))

        //onView(withId(R.id.emailidx)).perform(typeText("test@gmail.com"))
    */






    /*

        onView(withId(R.id.passwordForget)).perform(click()).perform(click())
        Thread.sleep(100)

        onView(withId(R.id.userEmail)).perform(click()).perform(typeText("test@gmail.com"));
    */





      /*  getInstrumentation().waitForIdleSync();
        onView(withId(R.id.email)).check(matches(isDisplayed()))
        activityRule.scenario.onActivity { activity ->
            val emaild = activity.findViewById<SFTextInput>(R.id.email)
            val password = activity.findViewById<SFTextInput>(R.id.password)
            emaild.editText.setText("test254hgfsahfhds@gmail.com")
            password.editText.setText("1234567")

            activity.findViewById<SFButton>(R.id.authentication_button_continue).performClick()
        }

    */

       getInstrumentation().waitForIdleSync();
        Thread.sleep (12000)
    }



    fun ViewInteraction.isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)

    fun ViewInteraction.isVisible() = getViewAssertion(ViewMatchers.Visibility.VISIBLE)

    fun ViewInteraction.isInvisible() = getViewAssertion(ViewMatchers.Visibility.INVISIBLE)

    private fun getViewAssertion(visibility: ViewMatchers.Visibility): ViewAssertion? {
        return ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }


    fun <T : View> recyclerChildAction(@IdRes id: Int, block: T.() -> Unit): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return any(View::class.java)
            }

            override fun getDescription(): String {
                return "Performing action on RecyclerView child item"
            }

            override fun perform(
                uiController: UiController,
                view: View
            ) {
                view.findViewById<T>(id).block()
            }
        }

    }
}

