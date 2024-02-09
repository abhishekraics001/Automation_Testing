package com.stellantis.space.ui.step
import com.stellantis.space.ui.utility.PageClass
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.stellantis.space.R.id
import com.stellantis.space.ui.data.TestData.Companion.BRAND_NAME_INDEX

class EnvironmentPage : PageClass(){

    companion object {
        private var BRAND_RECYCLER_VIEW: Int = id.brandRecyclerView;
    }



    fun selectEnvironmentAndClientTpe(env: String, client: String) {
        waitAndClick(ViewMatchers.withText(env))
        waitAndClick(ViewMatchers.withText(client))
    }

    fun selectBrand() {
        scrollToPositionAndClick(BRAND_NAME_INDEX, withId(BRAND_RECYCLER_VIEW))
    }
}
