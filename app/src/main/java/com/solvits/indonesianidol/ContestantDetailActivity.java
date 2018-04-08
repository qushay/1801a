package com.solvits.indonesianidol;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.solvits.indonesianidol.model.ContestantModel;

import java.util.List;

public class ContestantDetailActivity extends BaseActivity {
    RecyclerView recyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private ContestantModel mContenstant;
    private ImageView mIvBanner;
    private ImageView mIvPhotoProfile;
    private TextView mTvName;
    private TextView mTvLocation;
    private TextView mTvDesc;
    private FloatingActionButton mBtVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant_detail);
        mContenstant = (ContestantModel) getIntent().getSerializableExtra("contestant");

        setupToolbar(R.id.toolbar, mContenstant.getName());
        View view = getWindow().getDecorView();
        setLightStatusBar(view, this);

//        showData(mContenstant);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, int verticalOffset) {
                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                //Check if the view is collapsed
                if (scrollRange + verticalOffset == 0) {
                    TextView title = (TextView) toolbar.findViewById(R.id.tv_title);
                    title.setVisibility(View.VISIBLE);
                    toolbar.getContext().setTheme(R.style.Theme_AppCompat_Light);
                }else{
                    TextView title = (TextView) toolbar.findViewById(R.id.tv_title);
                    title.setVisibility(View.GONE);
                    toolbar.getContext().setTheme(R.style.FullWhite);
                }
            }
        });


//        String s = "Nice share @mahisa. Very nice place to go, a lot of beautiful @birds from all over the world";
//
//        Pattern pattern = Pattern.compile("@\\w+");
//
//        Matcher matcher = pattern.matcher(s);
//        List<String> result = new ArrayList<>();
//        List<Integer> wordLength = new ArrayList<>();
//        List<Integer> startPosition = new ArrayList<>();
//        while (matcher.find())
//        {
//            result.add(matcher.group());
//            wordLength.add(matcher.group().length());
//            startPosition.add(matcher.start());
//        }
//        Spannable wordtoSpan = new SpannableString(s);
//
//        for (int i = 0; i < result.size(); i++){
//            wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#FF9FA4")), startPosition.get(i), startPosition.get(i)+wordLength.get(i), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//
//        TextView comment = (TextView) findViewById(R.id.tv_comment);
//        comment.setText(wordtoSpan);
    }

//    private void showData(ContestantModel contestant){
//        mIvBanner = (ImageView) this.findViewById(R.id.image);
//        mIvPhotoProfile = (ImageView) this.findViewById(R.id.user_profile);
//        mTvName = (TextView) this.findViewById(R.id.tv_name);
//        mTvLocation = (TextView) this.findViewById(R.id.tv_location);
//        mTvDesc = (TextView) this.findViewById(R.id.tv_desc);
//        mBtVote = (FloatingActionButton) this.findViewById(R.id.bt_vote);
//
//
//        Gson gson = new Gson();
//
//        if (contestant.getName() != null) {
//            mTvName.setText(contestant.getName());
//        }
//        if (contestant.getFrom() != null) {
//            mTvLocation.setText(contestant.getFrom());
//        }
//        if (contestant.getPhoto() != null) {
//            Glide.with(this).load(contestant.getPhoto()).into(mIvBanner);
//            Glide.with(this).load(contestant.getPhoto()).into(mIvPhotoProfile);
//        }
//        if (contestant.getDesc() != null){
//            mTvDesc.setText(Jsoup.parse(contestant.getDesc()).text());
//        }
//
//        if (contestant.getPhotoOther() !=null){
//            String json = Jsoup.parse(contestant.getPhotoOther()).text();
//            List<String> photo = gson.fromJson(json,new TypeToken<List<String>>(){}.getType());
//
//            recyclerView = (RecyclerView) findViewById(R.id.recycler);
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//            recyclerView.setLayoutManager(linearLayoutManager);
//            SpaceItemDecoration decoration = new SpaceItemDecoration(16);
//            imageAdapter = new ImageAdapter(photo, this);
//            recyclerView.addItemDecoration(decoration);
//            recyclerView.setAdapter(imageAdapter);
//        }
//
//        mBtVote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final AlertDialog.Builder builder = new AlertDialog.Builder(Details.this);
//
//                LayoutInflater inflater = Details.this.getLayoutInflater();
//                final View v = inflater.inflate(R.layout.dialog_sms, null);
//                final DiscreteSeekBar seekbar = (DiscreteSeekBar)v.findViewById(R.id.sb_sms);
//                final TextView tvSms = (TextView)v.findViewById(R.id.tv_sms);
//                final TextView tvName= (TextView)v.findViewById(R.id.tv_name);
//                final TextView tvSmsTo = (TextView)v.findViewById(R.id.tv_sms_to);
//
//                tvName.setText(mContenstant.getName());
//                tvSmsTo.setText("ABDUL ke 9288");
//
//                tvSms.setText("Kirim "+seekbar.getProgress()+" SMS");
//
//                seekbar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
//                    @Override
//                    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
//                        tvSms.setText("Kirim "+value+" SMS");
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
//
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
//
//                    }
//                });
//
//                builder.setView(v);
//                builder.setPositiveButton("Kirim SMS", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked OK button
//                        Toast.makeText(Call.Details.this,seekbar.getProgress()+"", Toast.LENGTH_SHORT).show();
//                        showProgress(seekbar.getProgress());
//                    }
//                });
//                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
