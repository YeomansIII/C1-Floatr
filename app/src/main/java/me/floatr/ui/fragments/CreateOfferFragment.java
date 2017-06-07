package me.floatr.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class CreateOfferFragment extends Fragment implements View.OnClickListener {

    private View view;
    private MainActivity mainActivity;
    private SharedPreferences mainPref, groupPref;
    @BindView(R.id.createOfferSpinner)
    public Spinner dropdown;
    @BindView(R.id.createOfferFragMin)
    public TextInputLayout createOfferFragMin;
    @BindView(R.id.createOfferFragMax)
    public TextInputLayout createOfferFragMax;
    @BindView(R.id.createOfferFragInterestRate)
    public TextInputLayout createOfferFragInterestRate;
    @BindView(R.id.createOfferFragPeriod)
    public TextInputLayout createOfferFragPeriod;
    @BindView(R.id.createOfferFragCreateButton)
    public Button createOfferFragCreateButton;

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
        mainActivity.getSupportActionBar().setTitle("Create Loan Offer");
        dropdown = (Spinner) view.findViewById(R.id.createOfferSpinner);
        String[] items = new String[]{"Days", "Months", "Years"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        createOfferFragCreateButton.setOnClickListener(this);

        this.view = view;
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == createOfferFragCreateButton) {
            LoanOffer newOffer = new LoanOffer();
            newOffer.setMinOffer(Integer.parseInt(createOfferFragMin.getEditText().getText().toString()));
            newOffer.setMaxOffer(Integer.parseInt(createOfferFragMax.getEditText().getText().toString()));
            newOffer.setInterestRate(Integer.parseInt(createOfferFragInterestRate.getEditText().getText().toString()));
            newOffer.setPeriod(Integer.parseInt(createOfferFragPeriod.getEditText().getText().toString()));
            newOffer.setPeriodUnit(dropdown.getSelectedItem().toString());
            Call<LoanOffer> call = mainActivity.apiService.createLoadOffer(newOffer);
            call.enqueue(new Callback<LoanOffer>() {
                @Override
                public void onResponse(Call<LoanOffer> call, Response<LoanOffer> response) {
                    Log.d("CreateOffer", response.message());
                    Bundle bundle = new Bundle();
                    bundle.putString("id", response.body().getId());
                    OfferDetailsFragment detailFrag = new OfferDetailsFragment();
                    detailFrag.setArguments(bundle);
                }

                @Override
                public void onFailure(Call<LoanOffer> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}