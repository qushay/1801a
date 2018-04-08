package com.solvits.indonesianidol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.solvits.indonesianidol.adapter.ContestantAdapter;
import com.solvits.indonesianidol.customview.ItemOffsetDecoration;
import com.solvits.indonesianidol.fragment.ContestantGridFragment;
import com.solvits.indonesianidol.fragment.JudgesGridFragment;
import com.solvits.indonesianidol.fragment.SmsRecordFragment;
import com.solvits.indonesianidol.model.ContestantModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigation;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore mFireStore;
    private CollectionReference mParticipantsCollection;
    private Fragment mFragment;
    private FragmentManager mFragmentManager;
    private final static String TAG = "Contestant";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_contestant:
                    mFragment = new ContestantGridFragment();
                    break;
                case R.id.navigation_sms:
                    mFragment = new SmsRecordFragment();
                    break;
                case R.id.navigation_judge:
                    mFragment = new JudgesGridFragment();
                    break;
            }
            final FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, mFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant_list);

        mFragmentManager = getSupportFragmentManager();
        mBottomNavigation = findViewById(R.id.navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.navigation_contestant);
    }




}
