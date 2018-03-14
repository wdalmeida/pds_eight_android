package fr.esipe.ing3.pds.eight.bem.rest;

import java.util.List;

import fr.esipe.ing3.pds.eight.bem.newsFeed.RSSFeed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by antho on 25/10/2017.
 */

public interface APIService{

	/**
	 *
	 * @param id
	 * @return
	 */
	@GET("/rss/{id}")
    Call<RSSFeed> getOneNews(@Path("id") int id);

	/**
	 *
	 * @return
	 */
	@GET("/rss/bem")
	Call<List<RSSFeed>> getBemNews();

	/**
	 *
	 * @return
	 */
	@GET("/rss/bfm")
	Call<List<RSSFeed>> getBfmNews();

}
