package me.floatr.ui.adapters;

/**
 * Created by jason on 10/29/16.
 */

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
import me.floatr.models.LoanOffer;
import me.floatr.ui.activities.MainActivity;
import me.floatr.ui.fragments.OfferDetailsFragment;


/**
 * Created by jason on 2/9/16.
 */
public class LoanOfferRecyclerAdapter extends RecyclerView.Adapter<LoanOfferRecyclerAdapter.ViewHolder> {

    List<LoanOffer> loanOffers;
    Context context;


    public LoanOfferRecyclerAdapter() {
        loanOffers = new ArrayList<>();
    }

    public LoanOfferRecyclerAdapter(List<LoanOffer> loanOffers, Context context) {
        this.loanOffers = loanOffers;
    }

    @Override
    public LoanOfferRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View offerView = inflater.inflate(R.layout.offer_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(offerView, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoanOfferRecyclerAdapter.ViewHolder holder, int position) {
        LoanOffer loanOffer = loanOffers.get(position);
        holder.offerItemRange.setText(loanOffer.getMinOffer() + " - " + loanOffer.getMaxOffer());
        holder.offerItemInterest.setText("" + loanOffer.getInterestRate() + "%");
        holder.offerItemPeriod.setText(loanOffer.getPeriod() + " " + loanOffer.getPeriodUnit());
        holder.loanID = loanOffer.getId();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        Context context;
        String loanID;

        @BindView(R.id.offerItemRange)
        TextView offerItemRange;
        @BindView(R.id.offerItemInterest)
        TextView offerItemInterest;
        @BindView(R.id.offerItemPeriod)
        TextView offerItemPeriod;

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
            OfferDetailsFragment detailFrag = new OfferDetailsFragment();
            detailFrag.setArguments(bundle);
            ((MainActivity) this.context).getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFrag).commit();
        }
    }

    @Override
    public int getItemCount() {
        if (loanOffers != null) {
            return loanOffers.size();
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}