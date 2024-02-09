package com.stellantis.space.ui.activities.matcher

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.textfield.TextInputLayout
import com.inetpsa.space.framework.design.avatar.SFAvatar

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import com.stellantis.space.R
import com.stellantis.space.R.id
import com.stellantis.space.ui.activities.MainActivity
import org.hamcrest.Matchers

object MyMatchers {


    /** TextInputLayoutWithItemHint
        Verifies if a text matches the text of a TextInputLayout hint

        #How to use
        onView(withId(R.id.price_min_til)).check(matches(textInputLayoutwithItemHint(HINT_TO_MATCH_MIN_PRICE)));
    */
    fun textInputLayoutwithItemHint(matcherText: String): Matcher<View?>? {
        return object : BoundedMatcher<View?, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with item hint: $matcherText")
            }

            override fun matchesSafely(editTextField: TextInputLayout): Boolean {
                return matcherText.equals(editTextField.hint.toString(), ignoreCase = true)
            }
        }
    }





    /**t EditText: EditTextWithItemHint
        Verifies if a text matches the text of an EditText hint

        #How to use
        onView(withId(R.id.search_text)).check(matches(editTextwithItemHint(HINT_TO_MATCH_SEARCH)));
     */
    fun editTextwithItemHint(matcherText: String): Matcher<View?>? {
        return object : BoundedMatcher<View?, EditText>(EditText::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with item hint: $matcherText")
            }

            override fun matchesSafely(editTextField: EditText): Boolean {
                return matcherText.equals(editTextField.hint.toString(), ignoreCase = true)
            }
        }
    }




    /** TextView: ColorMatcher
        Verifies that a TextView has an specific color

        #How to use
        onView(withId(R.id.search_action_button)).check(matches(textViewTextColorMatcher(TEXT_BTN_COLOR_DISABLED)));
     */
    fun textViewTextColorMatcher(matcherColor: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with text color: $matcherColor")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return matcherColor == textView.currentTextColor
            }
        }
    }



    /** TextView: With ID & Text
        #How to use
        onView(withIdAndText(`is`(R.id.textSelectLanguageTitle as Int?), `is`("Choose language"))).check(matches(withText("Choose language")))
     */
    fun withIdAndText(integerMatcher: Matcher<Int?>, stringToMatch: Matcher<String?>): BoundedMatcher<View?, TextView> {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("error text: ")
                stringToMatch.describeTo(description)
                integerMatcher.describeTo(description)
            }

            public override fun matchesSafely(textView: TextView): Boolean {
                return stringToMatch.matches(textView.text.toString()) &&
                        integerMatcher.matches(textView.id)
            }
        }
    }




    /** WithSeekBarProgress
        Matcher to check the value/progress of a SeekBar

        #How to use:
        onView(withId(R.id.my_seekbar)).check(matches(withSeekbarProgress(100)));
     */
    fun withSeekbarProgress(expectedProgress: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, AppCompatSeekBar>(AppCompatSeekBar::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("expected: ")
                description.appendText("" + expectedProgress)
            }

            override fun matchesSafely(seekBar: AppCompatSeekBar): Boolean {
                return seekBar.progress == expectedProgress
            }
        }
    }




    /**     ListView : WhitListSize
            Custom Espresso Matcher to check the size of a ListView

            #How to use:
            onView(withId(R.id.my_list_view)).check(matches(withListSize(1)))
     */

    fun withListSize(size: Int): Any {
        return object : TypeSafeMatcher<View>() {
            public override fun matchesSafely(view: View): Boolean {
                return (view as ListView).count == size
            }
            override fun describeTo(description: Description) {
                description.appendText("ListView should have $size items")
            }
        }
    }





    /** RecyclerView : RecyclerViewSizeMatcher
        check the size of a RecyclerView

      #How to use:
       onView(withId(R.id.my_list_recycler)).check(matches(recyclerViewSizeMatcher(4)));
     */
    fun recyclerViewSizeMatcher(matcherSize: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with list size: $matcherSize")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return matcherSize == recyclerView.adapter!!.itemCount
            }
        }
    }



    /** RecyclerView : RecyclerViewAtPositionOnView
        Goes to an specific position in a RecyclerView, then checks if that item has specific information (i.e an
        specific text in the holder)

        #How to use:
        onView(withId(R.id.my_recycler_view)).check(matches(recyclerViewAtPositionOnView(2, withText(ITEM_TEXT_TO_CHECK), R.id.pickup_location_desc)));
     */
    fun recyclerViewAtPositionOnView(position: Int, itemMatcher: Matcher<View?>, targetViewId: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                val targetView = viewHolder!!.itemView.findViewById<View>(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }




    /**
        Displayed: Check view visible / Displayed by using view/widget id
     */
    fun isIdVisibleOrNot(viewId: Int): Boolean {
        var isDisplay = false
        try {
            // Check if the view with the given ID is displayed
            onView(withId(viewId)).check(matches(isDisplayed()))
            isDisplay = true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return isDisplay
        }
    }



    /**
            OnClick: Perform on click event on view/widget if view/widget id visible / display
     */
    fun ifIdVisibleThenClickOnView(viewId: Int): Boolean {
        var isDisplay = false
        try {
            // Check if the view with the given ID is displayed
            isDisplay = isIdVisibleOrNot(viewId)
            if(isDisplay){
                onView(withId(viewId)).perform(click())
            }else{
                isDisplay = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return isDisplay
        }
    }



    /**
            Click on Recycle View Items Index : Perform on click event on particular index if recycleView id visible /
           display
     */
    fun ifRecycleViewVisibleThenClickOnIndex(viewId: Int, itemIndex: Int): Boolean {
        var isDisplay = false
        try {
            // Check if the view with the given ID is displayed
            isDisplay = isIdVisibleOrNot(viewId)
            if(isDisplay){
                onView(withId(viewId)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(itemIndex)).perform(
                    click()
                )
            }else{
                isDisplay = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return isDisplay
        }
    }




    /**
        Click on  Recycle View Items Index  child items : Perform on click event on particular index if recycleView id
        visible / display
     */
    fun ifRecycleViewVisibleThenClickOnIndexWithChildItemsID(viewId: Int, itemIndex: Int, childItemID: Int): Boolean {
        var isDisplay = false
        try {
            // Check if the view with the given ID is displayed
            isDisplay = isIdVisibleOrNot(viewId)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if(isDisplay){
                onView(withId(viewId)).perform(click())
            }else{
                isDisplay = false
            }
            return isDisplay
        }
    }




    /**
        Bottom Navigation:
        check the visibility of bottom Navigation with child items id
        If visible then perform the click Event
     */
    fun bottomNavigationClickOnItemIndex(navigationViewID: Int, navigationChildItemsViewId: Int, tabIndex: Int) :
             Boolean{
         var isDisplay = false
         try {
             val bottomNavigationItemView = onView(
                 Matchers.allOf(
                     withId(navigationChildItemsViewId),
                     bottomNavigationChildAtPosition(
                         bottomNavigationChildAtPosition(
                             withId(navigationViewID),
                             0
                         ),
                         tabIndex
                     ),
                     isDisplayed()
                 )
             )
             if(bottomNavigationItemView != null) {
                 isDisplay = true
                 bottomNavigationItemView?.perform(click())
             }
         }catch (e: Exception){
             isDisplay = false
             e.printStackTrace()
         }
         finally {
             return isDisplay
         }
    }




    /**
        Bottom Navigation:
        check the visibility of bottom Navigation with child items id and child Content Description
        If visible then perform the click Event
     */
    fun bottomNavigationClickOnItemIndexWithContentDescription(navigationViewID: Int, navigationChildItemsViewId: Int,
        tabIndex: Int, tabContentDescription: String) :
            Boolean{
        var isDisplay = false
        try {
            val bottomNavigationItemView = onView(
                Matchers.allOf(
                    withId(navigationChildItemsViewId), ViewMatchers.withContentDescription(tabContentDescription),
                    bottomNavigationChildAtPosition(
                        bottomNavigationChildAtPosition(
                            withId(navigationViewID),
                            0
                        ),
                        tabIndex
                    ),
                    isDisplayed()
                )
            )
            if(bottomNavigationItemView != null) {
                isDisplay = true
                bottomNavigationItemView?.perform(click())
            }
        }catch (e: Exception){
            isDisplay = false
            e.printStackTrace()
        }
        finally {
            return isDisplay
        }
    }


    private fun bottomNavigationChildAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {
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

    /**
        Perform Action  Click on Activity Scenario with child Items
     */
     fun performClickActionOnViewOnActivityScenario(activityRule: ActivityScenarioRule<Activity>, viewId: Int): Boolean{
        var isDisplay = false
        try {
            activityRule.scenario.onActivity { activity ->
                val profileIcon = activity.findViewById<SFAvatar>(viewId)
                if(profileIcon != null) {
                    profileIcon.performClick()
                    isDisplay = true
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            return isDisplay
        }
    }



    /**
    Perform Action  Click on Activity Scenario with child Items
     */
    fun performClickActionOnPopupViewOnActivityScenario(activityRule: ActivityScenarioRule<Activity>, viewId: Int):
            Boolean {
         var decorView: View? = null
        var isDisplay = false
        try {
            activityRule.scenario.onActivity { activity: Activity ->
                decorView = activity.window.decorView
                val hasWindowFocus = activity.hasWindowFocus()
                Log.e("Language hasWindowFocus", "hasWindowFocus: $hasWindowFocus    decorView:${decorView}")
            }

            onView(withId(viewId)).inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed())).perform(click())
            isDisplay = true
        }catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            return isDisplay
        }
    }




}
