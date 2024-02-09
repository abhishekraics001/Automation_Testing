package com.stellantis.space.ui.step

import com.stellantis.space.ui.data.TestData
import com.stellantis.space.ui.testscript.StellantisAndroidJunit
import org.junit.Test

class LoginScenario: StellantisAndroidJunit() {
    private val environmentPage = EnvironmentPage()
    private val languageSelectionPage = LanguageSelectionPage()
    private val loginPage = LoginPage()
    private val homePageSteps = HomePageSteps()

    @Test
    fun myClassMethod_ReturnsTrue() {
        environmentPage.selectEnvironmentAndClientTpe(TestData.ENV, TestData.BRAND_RESION)
        environmentPage.selectBrand()

        languageSelectionPage.selectLanguage(activityRule)

        loginPage.clickOnLoginWithEmailButton()

        loginPage.enterLoginCredentils(activityRule, TestData.EMAIL_ID, TestData.PASSWORD)
        loginPage.clickLoginButton()


        homePageSteps.allowAccessLocation(activityRule)
        //homePageSteps.verifyPendingActionsIsDisplayed()

       Thread.sleep(20000)
        /*  homePageSteps.bottomNavigationTripsTap()
         Thread.sleep(5000)*/
    }
}
