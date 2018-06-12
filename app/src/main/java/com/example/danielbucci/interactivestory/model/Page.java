package com.example.danielbucci.interactivestory.model;

/**
 * Created by danielbucci on 2017-12-24.
 */

public class Page {
    private int imageId;
    private int textId;
    private Choice choice1;
    private Choice choice2;
    private boolean isFinalpage = false;

    public Page(int inmageId, int textId, Choice choice1, Choice choice2) {
        this.imageId = inmageId;
        this.textId = textId;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public Page(int inmageId, int textId) {
        this.imageId = inmageId;
        this.textId = textId;
        this.isFinalpage = true;

    }

    public boolean isFinalpage() {
        return isFinalpage;
    }

    public void setFinalpage(boolean finalpage) {
        isFinalpage = finalpage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getpChoice1() {
        return choice1;
    }

    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }

    public Choice getpChoice2() {
        return choice2;
    }

    public void setChoice2(Choice choice2) {
        this.choice2 = choice2;
    }


}
