package com.stellantis.space.ui.step

import com.stellantis.space.ui.testscript.StellantisAndroidJunit
import org.junit.Test

class LanguageSelectionScenario: StellantisAndroidJunit() {

    private val languagePage = LanguageSelectionPage()

    @Test
    fun myClassMethod_ReturnsTrue() {
        languagePage.selectLanguage(activityRule)
    }
}
