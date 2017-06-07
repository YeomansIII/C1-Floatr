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

import java.util.ArrayList;
import java.util.List;

import me.floatr.R;
import me.floatr.models.Offer;

/**
 * Created by jason on 2/9/16.
 */
public class OfferRecyclerAdapter extends RecyclerView.Adapter<OfferRecyclerAdapter.ViewHolder> {

    List<Offer> offers;
    Context context;
    AppCompatActivity activity;

    public OfferRecyclerAdapter() {
        offers = new ArrayList<>();
    }

    public OfferRecyclerAdapter(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public OfferRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.offer_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OfferRecyclerAdapter.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        Context context;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, Context context) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public int getItemCount() {
        if (offers != null) {
            return offers.size();
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}