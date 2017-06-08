package me.floatr.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.seekBar)
    public SeekBar seekBar;

    @BindView(R.id.sliderMaxRange)
    public TextView sliderMaxRange;

    @BindView(R.id.sliderMinRange)
    public TextView sliderMinRange;

    @BindView(R.id.sliderText)
    public TextView sliderText;

    @BindView(R.id.initiateButton)
    public Button initiateButton;

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
        View view = inflater.inflate(R.layout.offerdetails_fragment,
                container, false);
        ButterKnife.bind(this, view);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoanOffersFragment()).commit();
                    return true;
                }
                return false;
            }
        } );

        mainActivity.getSupportActionBar().setTitle("Loan Offer");

        offerId = getArguments().getString("id");

        Call<LoanOffer> offerCall = mainActivity.apiService.getLoanOffer(offerId);
        offerCall.enqueue(new Callback<LoanOffer>() {
            @Override
            public void onResponse(Call<LoanOffer> call, Response<LoanOffer> response) {
                offer = response.body();
                offerDetailFragMinRangeValue.setText("" + offer.getMinOffer());
                offerDetailFragMaxRangeValue.setText("" + offer.getMaxOffer());
                offerDetailFragInterestRateValue.setText("" + offer.getInterestRate());
                offerDetailFragPeriodValue.setText("" + offer.getPeriod());
                offerDetailFragPeriodUnit.setText("" + offer.getPeriodUnit());
                offerDetailFragNameText.setText("" + offer.getLoaner().getFirstName() + " "
                        + offer.getLoaner().getLastName());

                sliderMaxRange.setText(offer.getMaxOffer() + "");
                sliderMinRange.setText(offer.getMinOffer() + "");
                sliderText.setText(offer.getMinOffer() + "");
            }

            @Override
            public void onFailure(Call<LoanOffer> call, Throwable t) {

            }
        });



        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double percent = (double)progress/100;
                int min = offer.getMinOffer();
                int max = offer.getMaxOffer();
                double prog = min + (percent * (max - min));
                sliderText.setText(Math.round(prog) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        initiateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (offer.getLoaner().getId().equals(mainPref.getString(PreferenceNames.PREF_USER_ID, ""))) {
                    Toast.makeText(getContext(), "Can't request, this is your own offer!", Toast.LENGTH_LONG).show();
                    return;
                }
                mainActivity.apiService.initiate(Integer.parseInt(sliderText.getText().toString()));
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoanOffersFragment()).commit();

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