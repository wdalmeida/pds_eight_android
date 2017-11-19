package fr.esipe.ing3.pds.eight.bem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Warren D'ALMEIDA
 */

public class NewsActivity extends AppCompatActivity {

	private static final String TAG = "NEWS ACTIVITY" ;
	private RSSAdapter adapter;
	private List<RSSFeed> contacts = new ArrayList<>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
		Log.d(TAG, "onCreate: starting");
		RecyclerView recList = findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        for(int i=1;i<101;i++){
			contacts.add(new RSSFeed("Test"+i,"Test"+i,"Test"+i));
		}
		adapter = new RSSAdapter(contacts, this);
		recList.setAdapter(adapter);

	}
}
