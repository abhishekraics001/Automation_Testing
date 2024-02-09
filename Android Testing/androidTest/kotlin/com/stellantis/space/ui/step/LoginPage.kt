package com.stellantis.space.ui.step

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.stellantis.space.R
import com.stellantis.space.R.id
import com.stellantis.space.features.brand.ui.adapters.LanguageAdapter
import com.stellantis.space.ui.activities.MainActivity
import com.stellantis.space.ui.utility.PageClass
import junit.framework.AssertionFailedError
import org.hamcrest.Matchers

class LoginPage : PageClass() {

    companion object {
        private var AUTHENTICATION_SIGN_IN: Int = id.authentication_button_signin
        private var SUBMIT_BUTTON: Int = id.authentication_button_continue;
        private var EMAIL_ID: Int = id.email
        private var PASSWORD: Int = id.password
        private var AGREE_BUTTON: Int = id.nfPositive
    }


    fun clickOnLoginWithEmailButton() {
        waitAndClick(withId(AGREE_BUTTON))
        waitAndClick(withId(AUTHENTICATION_SIGN_IN))
    }
    fun enterLoginCredentils(activityRule: ActivityScenario<MainActivity>, email: String, pasWd: String) {
        waitAndSetText(email, EMAIL_ID, activityRule)
        waitAndSetText(pasWd, PASSWORD, activityRule)
    }
    fun clickLoginButton() {
        waitAndClick(withId(SUBMIT_BUTTON))
    }

}
