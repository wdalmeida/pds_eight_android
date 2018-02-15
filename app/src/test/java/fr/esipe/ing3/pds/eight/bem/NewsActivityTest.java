package fr.esipe.ing3.pds.eight.bem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * @author Warren D'ALMEIDA
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class NewsActivityTest {


	@Test
	public void onCreate() throws Exception {
		NewsActivity activity = Robolectric.setupActivity(NewsActivity.class);

		Assert.assertTrue(activity.findViewById(R.id.cardList).isEnabled());
	}

}