package com.solvits.indonesianidol.fragment;

/**
 * Created by qushaybagasisworo on 08/04/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solvits.indonesianidol.R;

public class JudgesGridFragment extends Fragment {

    public JudgesGridFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_judges_grid, container, false);
    }
}
