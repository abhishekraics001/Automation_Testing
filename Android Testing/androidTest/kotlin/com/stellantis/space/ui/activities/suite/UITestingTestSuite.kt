package com.stellantis.space.ui.activities.suite

import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import com.stellantis.space.ui.activities.exception.CustomEspressoFailureHandler
import com.stellantis.space.ui.activities.exception.UiTestExceptionHandler
import com.stellantis.space.ui.activities.test.MainActivity
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(MainActivity::class)
class UITestingTestSuite {

    @Before
    fun setUp() {

        UiTestExceptionHandler.setup()
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.setFailureHandler(CustomEspressoFailureHandler(context))
    }
}
