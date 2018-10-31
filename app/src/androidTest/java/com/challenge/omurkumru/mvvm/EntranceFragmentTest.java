package com.challenge.omurkumru.mvvm;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EntranceFragmentTest {

    @Rule
    public IntentsTestRule activityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void coolTextDisplayed() {
        onView(withId(R.id.quit_game_button)).perform(click());
        assertTrue(activityRule.getActivity().isFinishing());
    }

}
