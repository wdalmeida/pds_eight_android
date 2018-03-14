package fr.esipe.ing3.pds.eight.bem;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.FirebaseApp;

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
	private RecyclerView recList;


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
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);
		//callOneNews();
		FirebaseApp.initializeApp(this);
		//NewsService ns = new NewsService();
		//ns.onTokenRefresh();
		callAllNews();
		SwipeRefreshLayout srl = findViewById(R.id.swipeView);

		srl.setOnRefreshListener(
				() -> {
                    Log.i(TAG, "onRefresh");
                    callAllNews();
                    srl.setRefreshing(false);
                }
		);
	}


	/**
	 *
	 */
	protected void callOneNews(){
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


	/**
	 *
	 */
	protected void callAllNews(){
		Log.d(TAG, "CallAllNews");
		Call<List<RSSFeed>> rssFeedCall= API.get().getRetrofitService().getBemNews();
		rssFeedCall.enqueue(new Callback<List<RSSFeed>>() {
			@Override
			public void onResponse(Call<List<RSSFeed>> call, Response<List<RSSFeed>> response) {
				Log.d(TAG, "onResponse: ");
				if (response.code()==200){
					recList.setAdapter(new RSSAdapter(response.body(), getApplicationContext()));
				}
			}

			@Override
			public void onFailure(Call<List<RSSFeed>> call, Throwable t) {
				Log.d(TAG, "onFailure: "+t.toString());
			}
		});

	}

	/**
	 *
	 */
	protected void callAllNewsBFM(){
		Log.d(TAG, "CallAllNewsBFM");
		Call<List<RSSFeed>> rssFeedCall= API.get().getRetrofitService().getBfmNews();
		rssFeedCall.enqueue(new Callback<List<RSSFeed>>() {
			@Override
			public void onResponse(Call<List<RSSFeed>> call, Response<List<RSSFeed>> response) {
				Log.d(TAG, "onResponse: ");
				if (response.code()==200){
					recList.setAdapter(new RSSAdapter(response.body(), getApplicationContext()));
				}
			}

			@Override
			public void onFailure(Call<List<RSSFeed>> call, Throwable t) {
				Log.d(TAG, "onFailure: "+t.toString());
			}
		});

	}
}
