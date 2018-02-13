package eight.pds.ing3.esipe.fr.account_management;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.model.Transaction;

/**
 * Created by antho on 06/02/2018.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> implements Filterable {


    private List<Transaction> transactionList;
    private List<Transaction> transactionListFiltered;

    public TransactionAdapter(){
        this.transactionList = Collections.emptyList();
        this.transactionListFiltered = Collections.emptyList();
    }

    public TransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        this.transactionListFiltered =transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        this.transactionListFiltered = transactionList;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction,parent,false);
        final TransactionViewHolder viewHolder = new TransactionViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {

        Transaction transaction = transactionListFiltered.get(position);
        //Context
        holder.transaction = transaction;
        if (position % 2 == 1) {
            holder.contentLayout.setBackgroundColor(Color.WHITE);
        } else {
            holder.contentLayout.setBackgroundColor(Color.rgb(234, 233, 236));
        }
        holder.dateTextView.setText(transaction.getFormattedDate());
        holder.descriptionTextView.setText(transaction.getDescription());
        holder.amountTextView.setText(String.valueOf(transaction.getAmount()));
        if(transaction.getAmount() > 0)
            holder.amountTextView.setText("+ "+String.valueOf(transaction.getAmount())+" €");
        else holder.amountTextView.setText(String.valueOf(transaction.getAmount())+" €");

    }

    @Override
    public int getItemCount() {
        return transactionListFiltered.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder{

        public Transaction transaction;
        public View contentLayout;
        public TextView dateTextView;
        public TextView descriptionTextView;
        public TextView amountTextView;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content2);
            dateTextView = itemView.findViewById(R.id.text_transaction_date);
            descriptionTextView = itemView.findViewById(R.id.text_transaction_description);
            amountTextView = itemView.findViewById(R.id.text_transaction_amount);


        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                return new FilterResults();

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    transactionListFiltered = transactionList;
                } else {
                    List<Transaction> filteredList = new ArrayList<>();
                    for (Transaction row : transactionList) {


                        if (row.getDescription().toLowerCase().contains(charString.toLowerCase())) /*|| row.getAmount().contains(charSequence)) */ {
                            filteredList.add(row);
                        }
                    }

                    transactionListFiltered= filteredList;
                }

                notifyDataSetChanged();
            }
        };
    }
}
