package fr.esipe.ing3.pds.eight.bem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fr.esipe.ing3.pds.eight.bem.account_management.app.ApplicationTest;
import fr.esipe.ing3.pds.eight.bem.newsFeed.NewsActivity;

/**
 * @author Warren D'ALMEIDA
 */

@RunWith(RobolectricTestRunner.class)
@Config(application = ApplicationTest.class,constants = BuildConfig.class)
public class NewsActivityTest {


	@Test
	public void onCreate() throws Exception {
		NewsActivity activity = Robolectric.setupActivity(NewsActivity.class);

		Assert.assertTrue(activity.findViewById(R.id.cardList).isEnabled());
	}

}