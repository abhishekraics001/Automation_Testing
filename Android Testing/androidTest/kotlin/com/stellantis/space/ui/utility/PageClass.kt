package com.stellantis.space.ui.utility
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException.Builder
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import com.stellantis.space.R.id
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.inetpsa.space.framework.design.inputs.SFTextInput
import com.stellantis.space.ui.activities.MainActivity
import java.util.concurrent.TimeoutException

open class PageClass {

    companion object {
        var executionTimeout: Long = 20000
    }
    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null
        override fun getDescription() = "Click on a child view with specified id."
        override fun perform(uiController: UiController, view: View) = ViewActions.click().perform(
            uiController, view
                .findViewById
                    (viewId)
        )
    }
    fun hideKeyBoard() {
        pressBack()
    }
    /**
     * Perform action of waiting for a specific view match.
     * @param viewMatcher The viewMatcher of the view to wait for.
     */
    open fun wait(viewMatcher: Matcher<View>): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }
            override fun getDescription(): String {
                return "wait for a specific view with id <${viewMatcher}> during $executionTimeout millis."
            }
            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val startTime = System.currentTimeMillis()
                val endTime = startTime + executionTimeout
                do {
                    for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                        if (viewMatcher.matches(child)) {
                            return
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                // timeout happens
                throw Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }





    fun buttomNavigationClick(matcher: Matcher<View>){
        onView(
            matcher
        ).perform(click())


    }
    fun childAtPosition(
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




    public fun waitAndClick(views: Matcher<View>) {
        onView(isRoot()).perform(wait(views))
        onView(views).check(matches(isDisplayed())).perform(click())
    }
    fun isDisplayed(views: Matcher<View>) {
        try {
            onView(isRoot()).perform(wait(views))
            onView(views).check(matches(isDisplayed())).perform(click())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun waitAndSetText(text: String, views: Matcher<View>) {
        onView(isRoot()).perform(wait(views))
        onView(views).check(matches(isDisplayed())).perform(typeText(text))
    }
    fun waitAndSetText(text: String, viewId: Int,activity: ActivityScenario<MainActivity>) {
        onView(isRoot()).perform(wait(withId(viewId)))
        activity.onActivity { activityClass ->
            activityClass.findViewById<SFTextInput>(viewId).editText.setText(text)
        }
    }


    fun scrollToPositionAndClick(item: Int, views: Matcher<View>) {
        onView(isRoot()).perform(wait(views))
        onView(views).check(matches(isDisplayed()))
            .perform(actionOnItemAtPosition<ViewHolder>(item, click()))
    }


    //grantPermission("android.permission.ACCESS_COARSE_LOCATION",act)
    fun grantPermission(permission: String, activity: ActivityScenario<MainActivity>) {
        activity.onActivity { activityClass ->
            run {
                InstrumentationRegistry.getInstrumentation().getUiAutomation()
                    .grantRuntimePermission(activityClass.getPackageName(), permission);
            }
        }
    }
}
