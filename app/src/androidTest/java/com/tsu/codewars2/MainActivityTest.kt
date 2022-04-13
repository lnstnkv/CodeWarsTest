package com.tsu.codewars2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest : TestCase(){

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun checkStringInEditText_ExpectedRightString() {
        val strings = "0 1 2 4 5"
        onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).perform(typeText(strings))
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.textView2)).check(matches((withText("12, 12, 11, 9, 5, 0"))))

    }

    @Test
    fun checkEmptyStringInEditText_ExpectedErrorMessage() {
        val strings = ""
        onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).perform(typeText(strings))
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.textView2)).check(matches((withText("Введите данные"))))

    }



}



