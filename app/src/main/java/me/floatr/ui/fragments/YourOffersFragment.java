package me.floatr.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.floatr.R;
import me.floatr.models.Loan;
import me.floatr.models.LoanOffer;
import me.floatr.models.Offer;
import me.floatr.ui.activities.MainActivity;
import me.floatr.ui.adapters.LoanOfferRecyclerAdapter;
import me.floatr.ui.adapters.LoanRecyclerAdapter;
import me.floatr.util.PreferenceNames;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 10/29/16.
 */

public class YourOffersFragment extends Fragment{

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public LayoutManagerType mCurrentLayoutManagerType;

    private View view;
    private MainActivity mainActivity;
    private SharedPreferences mainPref;
    private LoanRecyclerAdapter loanRecyclerAdapter;
    private List<Loan> loans;

    @BindView(R.id.seekBar)
    public SeekBar seekBar;

    @BindView(R.id.sliderMaxRange)
    public TextView sliderMaxRange;

    @BindView(R.id.sliderMinRange)
    public TextView sliderMinRange;

    @BindView(R.id.sliderText)
    public TextView sliderText;


    View loadOverlay;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mainPref = mainActivity.getSharedPreferences(PreferenceNames.MAIN_PREFS_NAME, 0);
        loans = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_fragment,
                container, false);
        mainActivity.getSupportActionBar().setTitle("Your Offers");

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


        setOffersForUserFromDB();


        this.view = view;
        return view;
    }

    public void setOffersForUserFromDB() {
        Call<List<Loan>> listLoanCall = mainActivity.apiService.getOfferForUser(mainPref.getString(PreferenceNames.PREF_USER_ID, ""));
        listLoanCall.enqueue(new Callback<List<Loan>>() {
            @Override
            public void onResponse(Call<List<Loan>> call, Response<List<Loan>> response) {
                for (Loan l: response.body()) {
                    loans.add(l);
                }
                loanRecyclerAdapter = new LoanRecyclerAdapter(loans, getContext());
                mRecyclerView.setAdapter(loanRecyclerAdapter);

            }

            @Override
            public void onFailure(Call<List<Loan>> call, Throwable t) {

            }
        });
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