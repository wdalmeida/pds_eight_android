package fr.esipe.ing3.pds.eight.bem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author Warren D'ALMEIDA
 */

public class RSSAdapter extends RecyclerView.Adapter<RSSAdapter.RSSViewHolder> {

    private List<RSSFeed> contactList;
    private Context context;
    private String TAG = "RSS ADAPTER";

    public RSSAdapter(List<RSSFeed> contactList, Context context) {
		Log.d(TAG, "RSSAdapter: creating adapter");
		this.contactList = contactList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(RSSViewHolder RSSViewHolder, int i) {
        RSSFeed ci = contactList.get(i);
        RSSViewHolder.vName.setText(ci.title);
        RSSViewHolder.vSurname.setText(ci.link);
        RSSViewHolder.vEmail.setText(ci.description);
        RSSViewHolder.vTitle.setText(ci.title + " " + ci.link);
        Log.d(TAG, "onBindViewHolder: "+ RSSViewHolder.vTitle.getText().toString());
    }

    @Override
    public RSSViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.feedrss_card, viewGroup, false);

        return new RSSViewHolder(itemView);
    }

    public class RSSViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;

        public RSSViewHolder(View v) {
            super(v);
            vName = v.findViewById(R.id.txtName);
            vSurname = v.findViewById(R.id.txtSurname);
            vEmail = v.findViewById(R.id.txtEmail);
            vTitle = v.findViewById(R.id.title);
        }
    }
}
