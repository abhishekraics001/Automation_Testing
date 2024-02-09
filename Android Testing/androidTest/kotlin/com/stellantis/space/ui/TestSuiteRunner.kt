package com.stellantis.space.ui

import com.stellantis.space.ui.step.LoginScenario
import com.stellantis.space.ui.step.SignUpScenario
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(SignUpScenario::class)
class TestSuiteRunner {

}
