package fr.esipe.ing3.pds.eight.bem.account_management;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import fr.esipe.ing3.pds.eight.bem.R;
import fr.esipe.ing3.pds.eight.bem.account_management.model.Account;

/**
 * Created by antho on 12/11/2017.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>   {

    public interface OnItemClickListener {
        void onItemClick(Account account);
    }

    private List<Account> accountList;
    private final OnItemClickListener listener;

    public AccountAdapter(OnItemClickListener listener){
        this.listener = listener;
        this.accountList = Collections.emptyList();
    }

    public AccountAdapter(List<Account> accountList, OnItemClickListener listener){

        this.accountList = accountList;
        this.listener = listener;
    }

    public void setAccountList(List<Account> accountList) { this.accountList = accountList;};

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account,parent,false);
        final AccountViewHolder viewHolder = new AccountViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {

        holder.bind(accountList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }


    public static class AccountViewHolder extends RecyclerView.ViewHolder {

        public ImageView logo;
        public View contentLayout;
        public TextView accountNumberTextView;
        public TextView accountTypeTextView;
        public TextView balanceTextView;
        public Account account;

        public AccountViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content);
            logo = itemView.findViewById(R.id.imageView_accountType);
            accountNumberTextView = itemView.findViewById(R.id.text_account_number);
            accountTypeTextView = itemView.findViewById(R.id.text_account_type);
            balanceTextView = itemView.findViewById(R.id.text_balance);
        }

        public void bind(final Account account,final OnItemClickListener listener){


            //Context context = accountNumberTextView.getContext();
            this.account = account;
            if(account.getType().equals("CCB")){
                contentLayout.setBackgroundColor(Color.parseColor("#D12566"));
                logo.setImageResource(R.drawable.ic_credit_card_black_24dp);
            }
            else {
                contentLayout.setBackgroundColor(Color.parseColor("#136CBD"));
                logo.setImageResource(R.drawable.ic_euro_symbol_black_24dp);
            }
            Log.d("AccountAdapter", "onBindViewHolder: accoutId = ["+account.getAccountId()+"]");
            accountNumberTextView.setText("N° "+account.getAccountId());
            accountTypeTextView.setText(account.getType());

            if(account.getBalance() > 0)
            balanceTextView.setText("+ "+String.valueOf(account.getBalance())+" €");
            else balanceTextView.setText(String.valueOf(account.getBalance())+" €");


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(account);
                }
            });
        }
    }
}
