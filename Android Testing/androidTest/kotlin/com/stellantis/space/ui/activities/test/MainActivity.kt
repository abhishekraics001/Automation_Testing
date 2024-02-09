package com.stellantis.space.ui.activities.test

import android.R.id
import androidx.test.core.graphics.writeToTestStorage
import com.stellantis.space.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.screenshot.captureToBitmap
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.stellantis.space.ui.activities.MainActivity
import com.stellantis.space.ui.activities.matcher.MyMatchers.ifIdVisibleThenClickOnView
import com.stellantis.space.ui.activities.matcher.MyMatchers.withIdAndText
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivity {

    @get:Rule
    var nameRule = TestName()


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test1(){
        Thread.sleep(15000)
        onView(withIdAndText(`is`(R.id.textSelectLanguageTitle as Int?), `is`("Choose the region & language" as
                String)))
            .check(matches(withText("Choose the region & language")))


       val isSVissibleOrNot =  ifIdVisibleThenClickOnView(R.id.textSelectLanguageTitle )
        if(isSVissibleOrNot){
            //do this
        }else{
           // do thers thing
        }

        onView(isRoot())
            .captureToBitmap()
            .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

}
