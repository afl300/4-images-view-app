package com.example.core2extensionv2

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private var name = 0
    private var mainname = 0
    private var image = 0

    @get:Rule//this is to setup the entire thing
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        image = R.id.carview
        mainname = R.id.itemname1
        name = R.id.namevalue
    }

    @Test
    fun changename() {

        //given open app
        //When: changes name to WaiChan Street View
        //then: updates the mainactivity to WaiChan Street View
        val imagebuttons = onView(withId(image))
        imagebuttons.perform(click())

        val namebutton = onView(withId(name))
        namebutton.perform(replaceText("WaiChan Street View"))
        namebutton.perform(ViewActions.closeSoftKeyboard())

        pressBack()
        val namebox = onView(withId(mainname))
        namebox.check(ViewAssertions.matches(ViewMatchers.withText("WaiChan Street View")))
    }
}