package fr.esipe.ing3.pds.eight.bem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author Warren D'ALMEIDA
 */

public class RSSAdapter extends RecyclerView.Adapter<RSSAdapter.RSSViewHolder> {

	private List<RSSFeed> rssFeeds;
	private Context context;
	private String TAG = "RSS ADAPTER";

	public RSSAdapter(List<RSSFeed> rssFeeds, Context context) {
		Log.d(TAG, "RSSAdapter: creating adapter");
		this.rssFeeds = rssFeeds;
		this.context = context;
	}

	@Override
	public int getItemCount() {
		return rssFeeds.size();
	}

	@Override
	public void onBindViewHolder(RSSViewHolder RSSViewHolder, int i) {
		RSSFeed rss = rssFeeds.get(i);
		RSSViewHolder.title.setText(rss.getTitle());
		RSSViewHolder.link=rss.getLink();
		RSSViewHolder.description.setText(rss.getDescription().replaceAll("[\r\n]+",""));
		Log.d(TAG, "onBindViewHolder: "+rss.toString());
		AsyncTask<String, Void, Bitmap> asyncTask = new DownloadImage(RSSViewHolder.img)
				.execute(rss.getImgLink());
		Log.d(TAG, "onBindViewHolder: "+ RSSViewHolder.title.getText().toString());
		RSSViewHolder.date.setText(rss.getPublishedDate());
	}

	@Override
	public RSSViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View itemView = LayoutInflater.
				from(viewGroup.getContext()).
				inflate(R.layout.rss_card, viewGroup, false);

		return new RSSViewHolder(itemView);
	}

	public class RSSViewHolder extends RecyclerView.ViewHolder {
		protected TextView title;
		protected String link;
		protected TextView description;
		protected ImageView img;
		protected TextView date;



		public RSSViewHolder(View v) {
			super(v);
			title = v.findViewById(R.id.txtTitle);
			description = v.findViewById(R.id.txtDescription);
			img = v.findViewById(R.id.imageView);
			date=v.findViewById(R.id.txtDate);
			v.setOnClickListener(v1 -> {
				Intent intent = new Intent(context, RssWebView.class);
				intent.putExtra("EXTRA_SESSION_ID", link);
				context.startActivity(intent);
			});
		}
	}
}
