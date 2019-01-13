/*
 ************************ Assignment #HOMEWORK 02 *******************************************
 *********************** File Name- Source.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */

package com.example.manalighare.hw05;

import java.io.Serializable;

public class Source implements Serializable {
    String id, name;

    public Source() {

    }

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
