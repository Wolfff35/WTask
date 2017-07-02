package com.wolff.wtask.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wolff on 24.06.2017.
 */

public class WTask implements Serializable {
    private static final long serialVersionUID = 1163051588057804396L;

    private String mName;
    private int mId;
    private String mDescribe;
    private Date mDateCreation;
    private Date mDateFinish;
    private int mCategory;
    private boolean mIsForget;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getDescribe() {
        return mDescribe;
    }

    public void setDescribe(String mDescribe) {
        this.mDescribe = mDescribe;
    }

    public Date getDateCreation() {
        return mDateCreation;
    }

    public void setDateCreation(Date mDateCreation) {
        this.mDateCreation = mDateCreation;
    }

    public Date getDateFinish() {
        return mDateFinish;
    }

    public void setDateFinish(Date mDateFinish) {
        this.mDateFinish = mDateFinish;
    }

    public int getCategory() {
        return mCategory;
    }

    public void setCategory(int mCategory) {
        this.mCategory = mCategory;
    }

    public boolean isForget() {
        return mIsForget;
    }

    public void setIsForget(boolean mIsForget) {
        this.mIsForget = mIsForget;
    }
}
