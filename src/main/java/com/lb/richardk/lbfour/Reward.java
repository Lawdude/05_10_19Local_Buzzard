package com.lb.richardk.lbfour;

public class Reward
{

    public String companyName;
    public String discountCode;
    public String url;
    public String rewardImage;

    public Reward() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Reward(String company, String discount, String url, String rewardImage) {
        this.companyName = company;
        this.discountCode = discount;
        this.url = url;
        this.rewardImage = rewardImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String company) {
        this.companyName = company;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discount) {
        this.discountCode = discount;
    }

    public String getRewardLink() {
        return url;
    }

    public void setRewardLink(String url) {
        this.url = url;
    }

    public String getRewardImage() {
        return rewardImage;
    }

    public void setRewardImage(String rewardImage) {
        this.rewardImage = rewardImage;
    }
}
