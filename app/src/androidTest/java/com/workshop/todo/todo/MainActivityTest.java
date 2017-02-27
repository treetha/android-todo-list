package com.workshop.todo.todo;

import android.content.Intent;
import android.support.test.espresso.core.deps.guava.base.Charsets;
import android.support.test.espresso.core.deps.guava.io.Resources;
import android.support.test.rule.ActivityTestRule;

import com.workshop.todo.todo.interactor.TaskInteractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;


public class MainActivityTest {
    @Rule
    public ActivityTestRule activityTestRule
            = new ActivityTestRule(MainActivity.class, true, false);

    private MockWebServer server;

    @Before
    public void startMockWebServer() throws IOException {
        server = new MockWebServer();
        server.start();
        //TODO
        TaskInteractor.BASE_URL = server.url("/").toString();
    }

    @After
    public void stopServer() throws Exception {
        server.shutdown();
    }

    private String getDataFromFile(String resource) throws IOException {
        return Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
    }

    @Test
    public void login_sucess_should_show_all_task() throws IOException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(getDataFromFile("login_sucess.json")));
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(getDataFromFile("all_task.json")));

        //Launch activity
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        //Press signin button
        onView(withId(R.id.btn_login)).perform(click());

        //Check list of task
        onData(anything()).inAdapterView(withId(R.id.list_todo))
                .atPosition(9).perform(click());
        onView(withId(R.id.task_description))
                .check(matches(withText("Mock Webserver 10")));
    }
}