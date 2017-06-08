package me.floatr.ui.adapters;

/**
 * Created by jason on 10/29/16.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.floatr.R;
import me.floatr.models.LoanRequest;

/**
 * Created by jason on 2/9/16.
 */
public class LoanRequestRecyclerAdapter extends RecyclerView.Adapter<LoanRequestRecyclerAdapter.ViewHolder> {

    List<LoanRequest> loanRequests;
    Context context;
    AppCompatActivity activity;

    public LoanRequestRecyclerAdapter() {
        loanRequests = new ArrayList<>();
    }

    public LoanRequestRecyclerAdapter(List<LoanRequest> loanRequests) {
        this.loanRequests = loanRequests;
    }

    @Override
    public LoanRequestRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View requestView = inflater.inflate(R.layout.pre_loan_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(requestView, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoanRequestRecyclerAdapter.ViewHolder holder, int position) {
        LoanRequest loanRequest = loanRequests.get(position);
        holder.requestItemRange.setText(loanRequest.getMinRequest() + " - " + loanRequest.getMaxRequest());
        holder.requestItemInterest.setText("" + loanRequest.getInterestRate() + "%");
        holder.requestItemPeriod.setText(loanRequest.getPeriod() + " " + loanRequest.getPeriodUnit());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        Context context;
        @BindView(R.id.preLoanItemRange)
        TextView requestItemRange;
        @BindView(R.id.preLoanItemInterest)
        TextView requestItemInterest;
        @BindView(R.id.preLoanItemPeriod)
        TextView requestItemPeriod;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, Context context) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public int getItemCount() {
        if (loanRequests != null) {
            return loanRequests.size();
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}