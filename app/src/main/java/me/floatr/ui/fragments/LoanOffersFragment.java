package me.floatr.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.floatr.R;
import me.floatr.models.LoanOffer;
import me.floatr.ui.activities.MainActivity;
import me.floatr.ui.adapters.LoanOfferRecyclerAdapter;
import me.floatr.util.PreferenceNames;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 10/29/16.
 */

public class LoanOffersFragment extends Fragment implements View.OnClickListener {

    public static String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public LayoutManagerType mCurrentLayoutManagerType;

    public MainActivity mainActivity;
    private View view;
    private SharedPreferences mainPref;
    private LoanOfferRecyclerAdapter loanOfferRecyclerAdapter;
    private List<LoanOffer> offers;

    FloatingActionButton createOffer;


    View loadOverlay;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mainPref = mainActivity.getSharedPreferences(PreferenceNames.MAIN_PREFS_NAME, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_fragment,
                container, false);
        offers = new ArrayList<>();
        mainActivity.getSupportActionBar().setTitle("Loan Offers");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.offerListRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                1);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Call<List<LoanOffer>> loanOffersCall = mainActivity.apiService.getLoanOffers();
        loanOffersCall.enqueue(new Callback<List<LoanOffer>>() {
            @Override
            public void onResponse(Call<List<LoanOffer>> call, Response<List<LoanOffer>> response) {
                Log.d("Offers", response.message());
                Log.d("Offers", call.request().url().toString());
                List<LoanOffer> loanOffers = response.body();
                if (loanOffers != null) {
                    Log.d("Offers", "Number of offers: " + loanOffers.size());
                    for (int i = 0; i < loanOffers.size(); i++) {
                        LoanOffer loanOffer = loanOffers.get(i);
                        offers.add(loanOffer);
                        loanOfferRecyclerAdapter.notifyItemChanged(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LoanOffer>> call, Throwable t) {
                String message = t.getMessage();
                Log.e("tag", message);
                t.printStackTrace();
            }
        });

        loanOfferRecyclerAdapter = new LoanOfferRecyclerAdapter(offers, getContext());
        mRecyclerView.setAdapter(loanOfferRecyclerAdapter);

        createOffer = (FloatingActionButton) view.findViewById(R.id.offerFragCreateFab);
        createOffer.setOnClickListener(this);


        this.view = view;
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == view.findViewById(R.id.offerFragCreateFab)) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateOfferFragment()).addToBackStack(null).commit();
        }

    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

}