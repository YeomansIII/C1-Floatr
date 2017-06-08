package me.floatr.ui.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.floatr.R;
import me.floatr.ui.activities.MainActivity;
import me.floatr.util.PreferenceNames;

/**
 * Created by jason on 10/29/16.
 */

public class RequestsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private MainActivity mainActivity;
    private SharedPreferences mainPref, groupPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mainPref = mainActivity.getSharedPreferences(PreferenceNames.MAIN_PREFS_NAME, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests_fragment,
                container, false);

        this.view = view;
        return view;
    }

    @Override
    public void onClick(View v) {
//        if (v == view.findViewById(R.id.button)) {
//        }
    }
}