package com.example.pintu.zupermart.Models;

public class CategoryModel{

    private String CategoryIconLink;
    private String categoryNameLink;


    public CategoryModel(String categoryIconLink, String categoryNameLink) {
        CategoryIconLink = categoryIconLink;
        this.categoryNameLink = categoryNameLink;
    }

    public String getCategoryIconLink() {
        return CategoryIconLink;
    }

    public void setCategoryIconLink(String categoryIconLink) {
        CategoryIconLink = categoryIconLink;
    }

    public String getCategoryNameLink() {
        return categoryNameLink;
    }

    public void setCategoryNameLink(String categoryNameLink) {
        this.categoryNameLink = categoryNameLink;
    }
}
