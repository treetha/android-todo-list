package com.workshop.todo.todo;

import android.support.test.rule.ActivityTestRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.AllOf.allOf;

public class TodoListActivityTest {
    @Rule
    public ActivityTestRule activityTestRule
            = new ActivityTestRule(TodoListActivity.class);

    @Test @Ignore
    public void row_Click() {
        onView(allOf(withText("New 04")))
                .perform(click());
    }

    @Test @Ignore
    public void overScreen() {
        onView(withText("New 20")).check(doesNotExist());
    }

    @Test @Ignore
    public void row_Click_at_last() {
        onData(anything())
                .inAdapterView(withId(R.id.list_todo))
                .atPosition(19)
                .perform(click());

        onView(withId(R.id.task_description))
                .check(matches(withText("New 20")));
    }

}