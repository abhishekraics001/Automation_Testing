package com.stellantis.space.ui.step

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.stellantis.space.R
import com.stellantis.space.R.id
import com.stellantis.space.features.brand.ui.adapters.LanguageAdapter
import com.stellantis.space.ui.activities.MainActivity
import com.stellantis.space.ui.utility.PageClass
import junit.framework.AssertionFailedError
import org.hamcrest.Matchers

class LanguageSelectionPage: PageClass() {

    companion object{
        private var LANGUAGE_CHANGE_ICON: Int = id.languageChangeIcon
        private var LANGUAGE_SEARCH_TEXT_BOX: Int = id.edt_search
        private var LANGUAGE_ITEM: Int = id.languageItems
        private var LANGUAGE_RADIO_BUTTON: Int = id.vRadioButton
    }

    fun selectLanguage(activityRule: ActivityScenario<MainActivity>){
        languageSelectionPage(activityRule)
    }

    fun clickOnButtonToNavigateLanguagePageSelection() {
        Espresso.onView(withId(LANGUAGE_CHANGE_ICON))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())
    }
    fun enterLanguageToBeSelect(languageType: String) {
        Espresso.onView(withId(LANGUAGE_SEARCH_TEXT_BOX)).perform(ViewActions.typeText(languageType))
        hideKeyBoard()
    }

    fun selectLanguage() {
        onView(withId(LANGUAGE_ITEM))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(3, clickOnViewChild(LANGUAGE_RADIO_BUTTON))
            )
    }

    private fun languageSelectionPage(activityRule: ActivityScenario<MainActivity>) {
        var decorView : View? = null
        //onView(withId(R.id.languageItems)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.isRoot()).perform(wait(withId(R.id.languageItems)))
        Thread.sleep(4000)
        try {
            activityRule.onActivity { activity ->
                val recucleview = activity.findViewById<RecyclerView>(R.id.languageItems)
                val adapter = recucleview.adapter as LanguageAdapter
                val itemCount = recucleview.adapter?.itemCount ?: 0
                val selc = adapter.tracker?.selection
                selc?.let { adapter.tracker?.setItemsSelected(it, true) }
                Log.e("Language getItemDetails", "start getItemDetails itemCount: $itemCount")
            }


            activityRule.onActivity { activity: MainActivity ->
                decorView = activity.window.decorView
                val hasWindowFocus = activity.hasWindowFocus()
                Log.e("Language hasWindowFocus", "hasWindowFocus: $hasWindowFocus    decorView:${decorView}")
            }

            Thread.sleep(4000)
            Espresso.onView(withId(id.btn_third)).inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

            // Thread.sleep(1000)
            //pressBack()


        } catch (e: AssertionFailedError) {
            e.printStackTrace()
            Espresso.pressBack()
        }

    }

}


