package com.lb.richardk.lbfour;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RewardsActivity extends AppCompatActivity  {

    private DatabaseReference myRef;
    private RecyclerView rewardList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_view);

        myRef = FirebaseDatabase.getInstance().getReference().child("Rewards");
        myRef.keepSynced(true);

        mLayoutManager = new LinearLayoutManager(RewardsActivity.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        rewardList = (RecyclerView) findViewById(R.id.RewardsRecycleView);
        rewardList.setHasFixedSize(true);
        rewardList.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onBackPressed() {
        Intent startIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(startIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Reward, RewardsActivity.RewardViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Reward, RewardsActivity.RewardViewHolder>
                (Reward.class, R.layout.reward_row, RewardsActivity.RewardViewHolder.class, myRef) {

            @Override
            protected void populateViewHolder(RewardsActivity.RewardViewHolder viewHolder, Reward model, int position) {

                final String rewardLink = model.getRewardLink();

                viewHolder.setCompanyName(model.getCompanyName());
                viewHolder.setDiscountCode(model.getDiscountCode());
                viewHolder.setRewardImage(model.getRewardImage());

                viewHolder.offerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(rewardLink); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            }
        };
        rewardList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class RewardViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button offerButton;

        public RewardViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            offerButton = (Button)mView.findViewById(R.id.rewardButton);
        }

        public void setCompanyName(String reg) {
            TextView companyName = (TextView) mView.findViewById(R.id.companyName);
            companyName.setText(reg);
        }

        public void setDiscountCode(String discount) {
            TextView discountCode = (TextView) mView.findViewById(R.id.discountCode);
            discountCode.setText(discount);
        }

        public void setRewardImage(String rewardImage) {
            ImageView rewImage = (ImageView) mView.findViewById(R.id.rewardImage);
            Picasso.get().load(rewardImage).into(rewImage);
        }
    }
}