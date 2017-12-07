package fr.esipe.ing3.pds.eight.bem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import fr.esipe.ing3.pds.eight.bem.rest.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Warren D'ALMEIDA
 */

public class NewsActivity extends AppCompatActivity{

	private static final String TAG = "NEWS ACTIVITY" ;
	private RSSAdapter adapter;
	private List<RSSFeed> rssFeeds = new ArrayList<>();
	private RecyclerView recList;
	private ImageView imgView;


	/**
	 *
	 *
	 * @param savedInstanceState
	 */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_news);
		Log.d(TAG, "onCreate: starting");
		recList = findViewById(R.id.cardList);
		//imgView = findViewById(R.id.imageView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);
		callOneNews();

	}


	/**
	 *
	 */
	private void callOneNews(){
		Log.d(TAG, "CallOneNews");
		Call<RSSFeed> rssFeedCall= API.get().getRetrofitService().getOneNews(1);
		rssFeedCall.enqueue(new Callback<RSSFeed>() {
			@Override
			public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
				RSSFeed rss = response.body();
				Log.d(TAG, "onResponse: "+rss.getLink());
				List<RSSFeed> rssFeedList = new ArrayList<>();
				rssFeedList.add(rss);
				recList.setAdapter(new RSSAdapter(rssFeedList,getApplicationContext()));
			}

			@Override
			public void onFailure(Call<RSSFeed> call, Throwable t) {
				Log.d(TAG, "onFailure: "+t.toString());
			}
		});

	}
}
