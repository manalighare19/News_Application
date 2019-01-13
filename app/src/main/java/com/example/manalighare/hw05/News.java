/*
 ************************ Assignment #HOMEWORK 05 *******************************************
 *********************** File Name- News.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */
package com.example.manalighare.hw05;

import java.io.Serializable;

public class News implements Serializable {
    String title;
    String published_date;
    String author;
    String image_url;
    String url;

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", published_date='" + published_date + '\'' +
                ", author='" + author + '\'' +
                ", image_url='" + image_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
