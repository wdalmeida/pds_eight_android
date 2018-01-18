package fr.esipe.ing3.pds.eight.bem;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsActivityTest {

	@Rule
	public ActivityTestRule<NewsActivity> mActivityTestRule = new ActivityTestRule<>(NewsActivity.class);

	@Test
	public void newsActivityTest() throws InterruptedException {
		ViewInteraction textView = onView(
				allOf(withText("BEM"),
						childAtPosition(
								allOf(withId(R.id.action_bar),
										childAtPosition(
												withId(R.id.action_bar_container),
												0)),
								0),
						isDisplayed()));
		textView.check(matches(withText("BEM")));

		ViewInteraction recyclerView = onView(
				allOf(withId(R.id.cardList),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								0),
						isDisplayed()));
		recyclerView.check(matches(isDisplayed()));
		Thread.sleep(1000);

		ViewInteraction frameLayout = onView(
				allOf(withId(R.id.NewsCard),
						childAtPosition(
								childAtPosition(
										withId(R.id.cardList),
										0),
								0),
						isDisplayed()));
		frameLayout.check(matches(isDisplayed()));

		ViewInteraction imageView = onView(
				allOf(withId(R.id.imageView), withContentDescription("Image de l'article"),
						childAtPosition(
								childAtPosition(
										withId(R.id.NewsCard),
										0),
								0),
						isDisplayed()));
		imageView.check(matches(isDisplayed()));
		imageView.check(matches(notNullValue()));

		ViewInteraction textView2 = onView(
				allOf(withId(R.id.txtTitle),
						childAtPosition(
								childAtPosition(
										withId(R.id.NewsCard),
										0),
								1),
						isDisplayed()));
		textView2.check(matches(notNullValue()));

		ViewInteraction textView3 = onView(
				allOf(withId(R.id.txtDescription),
						childAtPosition(
								childAtPosition(
										withId(R.id.NewsCard),
										0),
								2),
						isDisplayed()));
		textView3.check(matches(notNullValue()));
	}

	private static Matcher<View> childAtPosition(
			final Matcher<View> parentMatcher, final int position) {

		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}

			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent)
						&& view.equals(((ViewGroup) parent).getChildAt(position));
			}
		};
	}
}
