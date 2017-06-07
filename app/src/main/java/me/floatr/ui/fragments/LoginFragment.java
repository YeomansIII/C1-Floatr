package me.floatr.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import me.floatr.models.User;
import me.floatr.ui.activities.MainActivity;
import me.floatr.util.FloatrApiInterface;
import me.floatr.util.PreferenceNames;
import me.floatr.util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 10/29/16.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private View view;
    private MainActivity mainActivity;
    private SharedPreferences pref;

    @BindView(R.id.loginFragEmailInput)
    public TextInputLayout loginFragEmailInput;
    @BindView(R.id.loginFragPasswordInput)
    public TextInputLayout loginFragPasswordInput;
    @BindView(R.id.loginFragLoginButton)
    public Button loginFragLoginButton;
    @BindView(R.id.loginFragCreateAccountButton)
    public Button loginFragCreateAccountButton;

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
        mainActivity.getSupportActionBar().setTitle("Sign in to Floatr");

        loginFragLoginButton.setOnClickListener(this);
        loginFragCreateAccountButton.setOnClickListener(this);

        this.view = view;
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == loginFragLoginButton) {
            Call<Auth> authCall = mainActivity.apiService.login(new Login(loginFragEmailInput.getEditText().getText().toString(), loginFragPasswordInput.getEditText().getText().toString()));
            authCall.enqueue(new Callback<Auth>() {
                @Override
                public void onResponse(Call<Auth> call, Response<Auth> response) {
                    Auth auth = response.body();
                    pref.edit().putString(PreferenceNames.PREF_USER_TOKEN, auth.getToken())
                            .putString(PreferenceNames.PREF_USER_ID, auth.getId())
                            .putString(PreferenceNames.PREF_USER_USERNAME, auth.getUsername())
                            .commit();
                    mainActivity.apiService = ServiceGenerator.createService(FloatrApiInterface.class, auth.getToken());
                    Log.d("Login", auth.getId());
                    Call<User> userCall = mainActivity.apiService.getUser(auth.getId());
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.d("Login", response.message());
                            Log.d("Login", call.request().url().toString());
                            User user = response.body();
                            pref.edit().putString(PreferenceNames.PREF_USER_FIRST_NAME, user.getFirstName())
                                    .putString(PreferenceNames.PREF_USER_LAST_NAME, user.getLastName())
                                    .putString(PreferenceNames.PREF_USER_CUSTOMER_ID, user.getCustomerId())
                                    .putInt(PreferenceNames.PREF_USER_RATING, user.getRating())
                                    .putString(PreferenceNames.PREF_USER_AVATAR_URL, user.getAvatarUrl())
                                    .commit();
                            mainActivity.finishLogin();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<Auth> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (v == loginFragCreateAccountButton) {
            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateAccountFragment()).addToBackStack(null).commit();
        }
    }
}