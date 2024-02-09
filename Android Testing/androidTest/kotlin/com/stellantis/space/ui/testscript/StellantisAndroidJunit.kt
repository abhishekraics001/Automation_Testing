package com.stellantis.space.ui.testscript

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stellantis.space.ui.activities.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class StellantisAndroidJunit {

    lateinit var activityRule: ActivityScenario<MainActivity>;

    @Before
    fun launchActivity() {
        activityRule = ActivityScenario.launch(checkNotNull(MainActivity::class.java))
    }
    @After
    fun closeActivity() {
        activityRule.close()
    }

}
