package me.floatr.ui.adapters;

import android.content.Context;
import android.os.Bundle;
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
import me.floatr.models.Loan;
import me.floatr.ui.activities.MainActivity;
import me.floatr.ui.fragments.OfferDetailsFragment;

/**
 * Created by clq678 on 6/8/17.
 */

public class LoanRecyclerAdapter extends RecyclerView.Adapter<LoanRecyclerAdapter.ViewHolder> {

        List<Loan> loans;
        Context context;


public LoanRecyclerAdapter() {
        loans = new ArrayList<>();
        }

public LoanRecyclerAdapter(List<Loan> loans, Context context) {
        this.loans = loans;
        }

@Override
public LoanRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View offerView = inflater.inflate(R.layout.offer_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(offerView, context);

        return viewHolder;
        }

@Override
public void onBindViewHolder(LoanRecyclerAdapter.ViewHolder holder, int position) {
        Loan loan = loans.get(position);
        holder.offerItemRange.setText(loan.getMinOffer() + " - " + loan.getMaxOffer());
        holder.offerItemInterest.setText("" + loan.getInterestRate() + "%");
        holder.offerItemPeriod.setText(loan.getPeriod() + " " + loan.getPeriodUnit());
        holder.loanID = loan.getId();
        holder.status = loan.getStatus();
        if (holder.status.equals("initiated")) {
            holder.loaneeFirstName = loan.getLoanee().getFirstName();
            holder.loaneeLastName = loan.getLoanee().getLastName();
            holder.initiateValue = loan.getInitiateValue();
        }

        if (loan.getStatus().equals("initiated"))
            holder.offerItemRequestText.setVisibility(View.VISIBLE);
        }

public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    Context context;
    String loanID;
    String status;

    String loaneeFirstName = "";
    String loaneeLastName = "";
    int initiateValue = -1;

    @BindView(R.id.offerItemRange)
    TextView offerItemRange;
    @BindView(R.id.offerItemInterest)
    TextView offerItemInterest;
    @BindView(R.id.offerItemPeriod)
    TextView offerItemPeriod;
    @BindView(R.id.offerItemRequestText)
    TextView offerItemRequestText;

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
        Bundle bundle = new Bundle();
        bundle.putString("id", loanID);
        bundle.putString("firstname", loaneeFirstName);
        bundle.putString("lastname", loaneeLastName);
        bundle.putInt("initiateValue", initiateValue);

        OfferDetailsFragment detailFrag = new OfferDetailsFragment();
        detailFrag.setArguments(bundle);
        ((MainActivity) this.context).getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFrag).commit();
    }
}

    @Override
    public int getItemCount() {
        if (loans != null) {
            return loans.size();
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

