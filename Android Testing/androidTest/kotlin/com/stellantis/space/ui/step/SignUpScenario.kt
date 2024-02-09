package com.stellantis.space.ui.step

import com.stellantis.space.ui.data.TestData
import com.stellantis.space.ui.data.TestData.Companion.NEW_EMAIL_ID
import com.stellantis.space.ui.step.SignUpPage.Companion.EnterYourEmail
import com.stellantis.space.ui.step.SignUpPage.Companion.EnterYourEmailErrorInvalid
import com.stellantis.space.ui.step.SignUpPage.Companion.EnterYourName
import com.stellantis.space.ui.step.SignUpPage.Companion.EnterYourPassword
import com.stellantis.space.ui.testscript.StellantisAndroidJunit
import org.junit.Test

class SignUpScenario : StellantisAndroidJunit() {
    private val environmentPage = EnvironmentPage()
    private val languageSelectionPage = LanguageSelectionPage()
    private val signUpPage = SignUpPage();


    @Test
    fun myClassMethod_ReturnsTrue() {
        environmentPage.selectEnvironmentAndClientTpe(TestData.ENV, TestData.BRAND_RESION)
        environmentPage.selectBrand()

        languageSelectionPage.selectLanguage(activityRule)
        signUpPage.clickOnSignUpLinkToNavigateSignUpActivity()

        signUpPage.isPageUIDisplayOrNot(EnterYourName)
        signUpPage.enterFirstNameAndLastName(activityRule, TestData.FIRST_NAME, TestData.LAST_NAME)
        signUpPage.clickOnSubmitButton()

        signUpPage.isPageUIDisplayOrNot(EnterYourEmail)
      // signUpPage.enterEmail(activityRule, "test", false)

        signUpPage.enterEmail(activityRule, NEW_EMAIL_ID )
        signUpPage.clickOnSubmitButton()

        signUpPage.isPageUIDisplayOrNot(EnterYourPassword)
        signUpPage.enterConfirmPassword(activityRule, TestData.CONFIRM_PASSWORD_ID)
        signUpPage.clickOnRemembererButton()
        signUpPage.clickOnSubmitButton()
        signUpPage.clickOnCreateButton()


        Thread.sleep(20000)
    }

   /* @Test
    fun signupWithValidUserPassword() {
        environmentPage.selectEnvironmentAndClientTpe(TestData.ENV, TestData.BRAND_RESION)
        environmentPage.selectBrand()
        languageSelectionPage.selectLanguage(activityRule)

        signUpPage.clickOnSignUpLinkToNavigateSignUpActivity()


        signUpPage.enterFirstName(activityRule,"Abhishek")
        signUpPage.enterLastName(activityRule,"Rai")


        signUpPage.enterFirstNameAndLastName(activityRule, TestData.FIRST_NAME, TestData.LAST_NAME)

        signUpPage.clickOnSubmitButton()
        signUpPage.enterEmail(activityRule, TestData.NEW_EMAIL_ID, true)
        signUpPage.clickOnSubmitButton()
        signUpPage.enterConfirmPassword(activityRule, TestData.CONFIRM_PASSWORD_ID)
        signUpPage.clickOnRemembererButton()
        signUpPage.clickOnSubmitButton()
        signUpPage.clickOnCreateButton()

        Thread.sleep(20000)
    }*/

}
