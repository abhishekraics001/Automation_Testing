package com.stellantis.space.ui.step

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.inetpsa.space.framework.design.inputs.SFTextInput
import com.stellantis.space.R.id
import com.stellantis.space.R

import com.stellantis.space.ui.activities.MainActivity
import com.stellantis.space.ui.utility.PageClass

class SignUpPage : PageClass() {
    companion object {
        var signUpLabel: Int = id.signUpLabel
        var signUpDetailedLabel: Int = id.signUpDetailedLabel
        var  tiProgressTextErrorMsg: Int = id.tiProgressText

        var singUPLink: Int = id.textSignUp

        var FIRST_NAME: Int = id.firstName
        var LAST_NAME: Int = id.lastName

        var EMIL_ID: Int = id.email

        var DEFINE_PASSWORD: Int = id.definePassword
        var CONFIRM_PASSWORD: Int = id.confirmPassword

        var REMEMBER_ME: Int = id.lightCheckbox
        var AGREE_CHECK_BOX: Int = id.checkedBox

        var CREATE_ACCOUNT_BUTTON: Int = id.signUpButtonAgreementContinue
        var AGREE_BUTTON: Int = id.nfPositive


        var EnterYourName = "Enter your name"

        var EnterYourEmail = "Enter your email address"
        var EnterYourEmailErrorInvalid = "Invalid email format"
        var EnterYourEmailErrorValid = "This email address will be your login to access"

        var EnterYourPassword = "Define your password"
        var EnterYourPasswordMsg = "Minimum 8 characters, 1 uppercase and 1 figure"

        var SUBMIT_BUTTON: String = "Continue"
    }
    fun clickOnSignUpLinkToNavigateSignUpActivity() {
        waitAndClick(withId(AGREE_BUTTON))
        waitAndClick(withId(singUPLink))
    }


    fun isPageUIDisplayOrNot(viewText: String){
        onView(withId(signUpLabel)).check(matches(ViewMatchers.isDisplayed())).check(matches(withText("Sign up")))
        onView(withId(signUpDetailedLabel)).check(matches(ViewMatchers.isDisplayed())).check(matches(withText(viewText)))
    }

    fun enterFirstNameAndLastName(activityRule: ActivityScenario<MainActivity>, fName: String, lName: String) {
        waitAndSetText(fName, FIRST_NAME, activityRule)
        waitAndSetText(lName, LAST_NAME, activityRule)
    }

    fun enterFirstName(activityRule: ActivityScenario<MainActivity>, fName: String) {
        waitAndSetText(fName, FIRST_NAME, activityRule)
    }
    fun enterLastName(activityRule: ActivityScenario<MainActivity>,  lName: String) {
        waitAndSetText(lName, LAST_NAME, activityRule)
    }

    fun enterEmail(activityRule: ActivityScenario<MainActivity>, email: String){
        waitAndSetText(email, EMIL_ID, activityRule)
        
    }
    fun enterEmail(activityRule: ActivityScenario<MainActivity>, email: String, isValid: Boolean) {
        Log.e("Abhishek ", "Abhishek Started 0")
        waitAndSetText(email, EMIL_ID, activityRule)
        Log.e("Abhishek ", "Abhishek Started 191")
        activityRule.onActivity{
           val emailView =  it.findViewById<SFTextInput>(EMIL_ID)
            emailView.editText.setText(email)



            Log.e("Abhishek ", "Abhishek Started 1")
            try {
                Log.e("Abhishek ", "Abhishek Started 3")
                val error = it.findViewById<TextInputLayout>(id.textInput_Layout)
                val errmsg = error.error

                val error2 = it.findViewById<TextInputEditText>(id.textInput_Edit)
                val errmsg2 = error2.error

                Log.e("Abhishek ", "Abhishek Started 4")

                Thread.sleep(4000)

                val tiProgressText = it.findViewById<MaterialTextView>(R.id.tiProgressText).text
                val tiMessageText = it.findViewById<MaterialTextView>(R.id.tiMessageText).text
                val tiHelper = it.findViewById<MaterialTextView>(R.id.tiHelper).text
                val tiCounter = it.findViewById<MaterialTextView>(R.id.tiCounter)
                println("tiProgressText:$tiProgressText , tiMessageText:$tiMessageText , tiHelper:$tiHelper , " +
                        "tiCounter: $tiCounter " +
                        "errmsg:$errmsg  , errmsg2:$errmsg2")


                /* val progressMsg = it.findViewById<MaterialTextView>(id.tiHelper).text.toString()
            if(isValid){
                Assert.assertEquals(EnterYourEmailErrorValid, progressMsg)
            }else{
                Assert.assertEquals(EnterYourEmailErrorInvalid, progressMsg)
            }*/
            }catch (e: Exception){
                e.printStackTrace()
                Log.e("Abhishek ", "Abhishek Started 2")
            }
        }
    }
    fun enterConfirmPassword(activityRule: ActivityScenario<MainActivity>, enterPassword: String) {
        waitAndSetText(enterPassword, DEFINE_PASSWORD, activityRule)
        waitAndSetText(enterPassword, CONFIRM_PASSWORD, activityRule)
    }
    fun clickOnRemembererButton() {
        waitAndClick(withId(REMEMBER_ME))
    }
    fun clickOnSubmitButton() {
        waitAndClick(withText(SUBMIT_BUTTON))
    }
    fun clickOnCreateButton() {
        waitAndClick(withId(AGREE_CHECK_BOX))
        waitAndClick(withId(CREATE_ACCOUNT_BUTTON))
    }
}
