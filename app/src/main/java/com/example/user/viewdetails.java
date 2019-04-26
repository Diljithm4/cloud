package com.example.user;

import android.content.Intent;

public class viewdetails {

    private String PackageName;
    private String ImageName;
    private String Title;
    private String Rating;
    private String SubTitle;
    private String Price;
    private String Description;

    public String getPackageName() {
        return PackageName;
        Intent in=new Intent(viewdetails.this,PostInstalledApps.class);
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
