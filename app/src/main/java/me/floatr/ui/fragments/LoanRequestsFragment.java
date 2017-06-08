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
import me.floatr.models.LoanRequest;
import me.floatr.models.LoanRequest;
import me.floatr.ui.activities.MainActivity;
import me.floatr.ui.adapters.LoanRequestRecyclerAdapter;
import me.floatr.util.PreferenceNames;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 10/29/16.
 */

public class LoanRequestsFragment extends Fragment implements View.OnClickListener {

    public static String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public LoanRequestsFragment.LayoutManagerType mCurrentLayoutManagerType;

    private View view;
    private MainActivity mainActivity;
    private SharedPreferences mainPref;
    private LoanRequestRecyclerAdapter loanRequestRecyclerAdapter;
    private List<LoanRequest> requests;

    FloatingActionButton createRequest;


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
        View view = inflater.inflate(R.layout.request_fragment,
                container, false);

        requests = new ArrayList<>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.requestListRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LoanRequestsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LoanRequestsFragment.LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                1);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Call<List<LoanRequest>> loanRequestsCall = mainActivity.apiService.getLoanRequests();
        loanRequestsCall.enqueue(new Callback<List<LoanRequest>>() {
            @Override
            public void onResponse(Call<List<LoanRequest>> call, Response<List<LoanRequest>> response) {
                Log.d("Requests", response.message());
                Log.d("Requests", call.request().url().toString());
                List<LoanRequest> loanRequests = response.body();
                Log.d("Requests", "Number of requests: " + loanRequests.size());
                for (int i = 0; i < loanRequests.size(); i++) {
                    LoanRequest loanRequest = loanRequests.get(i);
                    requests.add(loanRequest);
                    loanRequestRecyclerAdapter.notifyItemChanged(i);
                }
            }

            @Override
            public void onFailure(Call<List<LoanRequest>> call, Throwable t) {
                String message = t.getMessage();
                Log.e("tag", message);
                t.printStackTrace();
            }
        });

        loanRequestRecyclerAdapter = new LoanRequestRecyclerAdapter(requests);
        mRecyclerView.setAdapter(loanRequestRecyclerAdapter);

        createRequest = (FloatingActionButton) view.findViewById(R.id.requestFragCreateFab);
        createRequest.setOnClickListener(this);

        this.view = view;
        return view;
    }


    @Override
    public void onClick(View v) {
//        if (v == view.findViewById(R.id.requestFragCreateFab)) {
//            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateRequestFragment()).addToBackStack(null).commit();
//        }

    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LoanRequestsFragment.LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LoanRequestsFragment.LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LoanRequestsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LoanRequestsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

}