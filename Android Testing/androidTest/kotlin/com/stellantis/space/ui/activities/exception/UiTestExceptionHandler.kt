package com.stellantis.space.ui.activities.exception
import android.content.Context
import android.os.Looper
import android.view.View
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.base.DefaultFailureHandler
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object UiTestExceptionHandler {

    fun setup() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleException(thread, throwable)
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }

    private fun handleException(thread: Thread, throwable: Throwable) {
        // Your custom exception handling logic goes here
        // For UI tests, you might want to report the exception instead of crashing the test
        reportException(throwable)
    }

    private fun reportException(throwable: Throwable) {
        // Your reporting logic (e.g., log the exception, capture a screenshot, etc.)
        // For simplicity, let's just print the stack trace to the console
        throwable.printStackTrace()

        // Signal the test that an exception has occurred
        signalTestFailure()
    }

    private fun signalTestFailure() {
        // Use a CountDownLatch to signal the test that an exception has occurred
        val latch = CountDownLatch(1)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Do any UI-related actions to signal the test failure
            // For simplicity, we'll just print a message to the console
            println("Test failed due to an unhandled exception")
            latch.countDown()
        }

        // Wait for a reasonable amount of time for the test to detect the failure
        latch.await(5, TimeUnit.SECONDS)
    }
}

class CustomEspressoFailureHandler(private val context: Context) : FailureHandler {

    private val defaultHandler = DefaultFailureHandler(context)

    override fun handle(error: Throwable, viewMatcher: Matcher<android.view.View>?) {
        // Your custom handling logic goes here
        // You can log the error, take a screenshot, etc.
        // For simplicity, we'll just print the error message to the console
        error.printStackTrace()

        // Delegate to the default Espresso failure handler
        defaultHandler.handle(error, viewMatcher)
    }
}

