package com.solvits.indonesianidol.fragment;

/**
 * Created by qushaybagasisworo on 08/04/18.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solvits.indonesianidol.R;
import com.solvits.indonesianidol.adapter.ContestantAdapter;
import com.solvits.indonesianidol.customview.ItemOffsetDecoration;
import com.solvits.indonesianidol.model.ContestantModel;

import java.util.ArrayList;
import java.util.List;

public class ContestantGridFragment extends Fragment {
    private Context mContext;
    private List<ContestantModel> mContestants;
    private RecyclerView mRvContestentGrid;
    private RecyclerView.LayoutManager mRvLayoutManager;
    private RecyclerView.Adapter mRvAdapter;


    public ContestantGridFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contestant_grid, container, false);
        mContext = container.getContext();
        mRvContestentGrid = view.findViewById(R.id.rv_contestant_grid);
        mContestants = new ArrayList<>();
        mContestants.add(new ContestantModel("1", "Abdul", "Bali",
                "http://www.missindonesia.co.id/tahubulets/assets/images/idol/bank_file/person/contestant/1517222574_abdul.jpg",
                "lajkjnda kjsdkj ksjdnkajsdnk kjsnkda kajsdkjsand mkjsndka",
                "false",
                "ABDUL",
                "95151"));
        showGrid(mContestants);
        return view;
    }


    private void showGrid(List<ContestantModel> contestantModels){
        mRvContestentGrid.setHasFixedSize(true);
        // use a linear layout manager
        int numberOfColumns = 3;
        mRvLayoutManager = new GridLayoutManager(mContext, numberOfColumns);
        mRvContestentGrid.addItemDecoration(new ItemOffsetDecoration(-4));
        mRvContestentGrid.setLayoutManager(mRvLayoutManager);
        mRvAdapter = new ContestantAdapter(mContext,contestantModels);
        mRvContestentGrid.setAdapter(mRvAdapter);
    }
}
