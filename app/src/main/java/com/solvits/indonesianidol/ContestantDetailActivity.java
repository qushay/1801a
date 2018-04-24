package com.solvits.indonesianidol;


import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.solvits.indonesianidol.customview.SquareImageView;
import com.solvits.indonesianidol.model.ContestantModel;

import org.jsoup.Jsoup;

import java.util.List;

public class ContestantDetailActivity extends BaseActivity {


    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    RecyclerView recyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private ContestantModel mContenstant;
    private SquareImageView mIvBanner;
    private TextView mTvName;
    private TextView mTvCity;
    private TextView mTvBiograph;
    private Button mBtVote;
    private Button mBtSendVote;
    private Button mBtCancelVote;
    private LinearLayout mLlVoteDialog;
    private EditText mEtNumberOfVote;

    private String mSMSMessage;
    private String mSMSNumber;
    private int mNumberOfVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant_detail);
        mContenstant = (ContestantModel) getIntent().getSerializableExtra("contestant");

        setupToolbar(R.id.toolbar, mContenstant.getName());
        View view = getWindow().getDecorView();
        setLightStatusBar(view, this);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        mLlVoteDialog = findViewById(R.id.ll_vote_dialog);
        mLlVoteDialog.setVisibility(View.GONE);
        mBtCancelVote = findViewById(R.id.bt_cancel_vote);
        mBtSendVote = findViewById(R.id.bt_send_vote);
        mEtNumberOfVote = findViewById(R.id.et_number_of_sms);
        mEtNumberOfVote.setText("10");

        showData(mContenstant);

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
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

    private void showData(ContestantModel contestant){
        mIvBanner = this.findViewById(R.id.iv_photo);
        mTvName = this.findViewById(R.id.tv_name);
        mTvCity = this.findViewById(R.id.tv_city);
        mTvBiograph = this.findViewById(R.id.tv_biograph);
        mBtVote = this.findViewById(R.id.bt_vote);
//
//
//        Gson gson = new Gson();
//
        if (contestant.getName() != null) {
            mTvName.setText(contestant.getName());
        }
        if (contestant.getCity() != null) {
            mTvCity.setText(contestant.getCity());
        }
        if (contestant.getPhoto() != null) {
            Glide.with(this).load(contestant.getPhoto()).into(mIvBanner);
        }
        if (contestant.getBiograph() != null){
            mTvBiograph.setText(Jsoup.parse(contestant.getBiograph()).text());
        }
        if (contestant.getSmsNumber() != null){
            mSMSNumber = contestant.getSmsNumber();
        }
        if (contestant.getSmsMessage() != null){
            mSMSMessage = contestant.getSmsMessage();
        }

        mBtVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLlVoteDialog.setVisibility(View.VISIBLE);
            }
        });

        mBtSendVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLlVoteDialog.setVisibility(View.GONE);
                mNumberOfVote = Integer.parseInt(mEtNumberOfVote.getText().toString());

                for (int i=0; i<mNumberOfVote;i++) {
                    sendSMS();
                }
            }
        });

        mBtCancelVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLlVoteDialog.setVisibility(View.GONE);
            }
        });
//
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void sendSMS(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mSMSNumber, null, mSMSMessage, null, null);
            Toast.makeText(getApplicationContext(),
                    "Vote terkirim.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mSMSNumber, null, mSMSMessage, null, null);
                    Toast.makeText(getApplicationContext(),
                            "Vote terkirim.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}
