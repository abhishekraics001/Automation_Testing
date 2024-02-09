package com.stellantis.space.ui.step

import com.stellantis.space.ui.data.TestData
import com.stellantis.space.ui.testscript.StellantisAndroidJunit
import org.junit.Test

class EnvironmentScenario: StellantisAndroidJunit() {

    private val languagePage = EnvironmentPage()

    @Test
    fun myClassMethod_ReturnsTrue() {
        languagePage.selectEnvironmentAndClientTpe(TestData.ENV, TestData.BRAND_RESION)
        languagePage.selectBrand()
    }
}
