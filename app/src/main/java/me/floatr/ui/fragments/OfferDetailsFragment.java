package me.floatr.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.floatr.R;
import me.floatr.models.LoanOffer;
import me.floatr.ui.activities.MainActivity;
import me.floatr.util.PreferenceNames;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 10/29/16.
 */

public class OfferDetailsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private MainActivity mainActivity;
    private SharedPreferences mainPref;
    @BindView(R.id.offerDetailFragNameText)
    public TextView offerDetailFragNameText;
    @BindView(R.id.offerDetailFragMinRangeValue)
    public TextView offerDetailFragMinRangeValue;
    @BindView(R.id.offerDetailFragMaxRangeValue)
    public TextView offerDetailFragMaxRangeValue;
    @BindView(R.id.offerDetailFragInterestRateValue)
    public TextView offerDetailFragInterestRateValue;
    @BindView(R.id.offerDetailFragPeriodValue)
    public TextView offerDetailFragPeriodValue;
    @BindView(R.id.offerDetailFragPeriodUnit)
    public TextView offerDetailFragPeriodUnit;

    String offerId;
    LoanOffer offer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mainPref = mainActivity.getSharedPreferences(PreferenceNames.MAIN_PREFS_NAME, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.createoffer_fragment,
                container, false);
        ButterKnife.bind(this, view);

        mainActivity.getSupportActionBar().setTitle("Loan Offer");

        offerId = getArguments().getString("id");

        Call<LoanOffer> offerCall = mainActivity.apiService.getLoadOffer(offerId);
        offerCall.enqueue(new Callback<LoanOffer>() {
            @Override
            public void onResponse(Call<LoanOffer> call, Response<LoanOffer> response) {
                offer = response.body();
                offerDetailFragMinRangeValue.setText(offer.getMinOffer());
                offerDetailFragMaxRangeValue.setText(offer.getMaxOffer());
                offerDetailFragMaxRangeValue.setText(offer.getMaxOffer());
                offerDetailFragInterestRateValue.setText(offer.getInterestRate());
                offerDetailFragPeriodValue.setText(offer.getPeriod());
                offerDetailFragPeriodUnit.setText(offer.getPeriodUnit());
            }

            @Override
            public void onFailure(Call<LoanOffer> call, Throwable t) {

            }
        });

        this.view = view;
        return view;
    }

    @Override
    public void onClick(View v) {
//        if (v == createOfferFragCreateButton) {
//
//        }
    }
}