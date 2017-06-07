package me.floatr.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.floatr.R;
import me.floatr.models.Auth;
import me.floatr.models.Login;
import me.floatr.ui.activities.MainActivity;
import me.floatr.util.PreferenceNames;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 10/29/16.
 */

public class CreateAccountFragment extends Fragment implements View.OnClickListener {

    private View view;
    private MainActivity mainActivity;
    private SharedPreferences pref;

    @BindView(R.id.createAccountEmailInput)
    public TextInputLayout createAccountEmailInput;
    @BindView(R.id.createAccountPasswordInput)
    public TextInputLayout createAccountPasswordInput;
    @BindView(R.id.createAccountCreateAccountButton)
    public Button createAccountCreateAccountButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        pref = mainActivity.getSharedPreferences(PreferenceNames.MAIN_PREFS_NAME, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,
                container, false);
        ButterKnife.bind(this, view);
        mainActivity.getSupportActionBar().setTitle("Create Account");

        createAccountCreateAccountButton.setOnClickListener(this);

        this.view = view;
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == createAccountCreateAccountButton) {
            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
        }
    }
}