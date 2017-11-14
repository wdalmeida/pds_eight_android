package eight.pds.ing3.esipe.fr.account_management;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.model.Account;

/**
 * Created by antho on 12/11/2017.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>   {

    private List<Account> accountList;

    public AccountAdapter(){
        this.accountList = Collections.emptyList();
    }

    public AccountAdapter(List<Account> accountList){
        this.accountList = accountList;
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
        Account account = accountList.get(position);
        Context context = holder.accountNumberTextView.getContext();
        holder.account = account;
        holder.accountNumberTextView.setText(String.valueOf(account.getAccountId()));
        holder.accountTypeTextView.setText(account.getType());
        holder.balanceTextView.setText(String.valueOf(account.getBalance()));

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }


    public static class AccountViewHolder extends RecyclerView.ViewHolder {

        public View contentLayout;
        public TextView accountNumberTextView;
        public TextView accountTypeTextView;
        public TextView balanceTextView;
        public Account account;

        public AccountViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content);
            accountNumberTextView = itemView.findViewById(R.id.text_account_number);
            accountTypeTextView = itemView.findViewById(R.id.text_account_type);
            balanceTextView = itemView.findViewById(R.id.text_balance);
        }
    }
}
