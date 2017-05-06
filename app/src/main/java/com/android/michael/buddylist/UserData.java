package com.android.michael.buddylist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Michael on 3/5/2017.
 */

public class UserData implements Parcelable {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private List<GroupData> groupDataList;

    public UserData(String id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserData(UserData source){
        this.id = source.getId();
        this.email = source.getEmail();
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
    }

    //Parcelable requirements

    protected UserData(Parcel in) {
        id = in.readString();
        email = in.readString();
        firstName = in.readString();
        lastName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(firstName);
        dest.writeString(lastName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<GroupData> getGroupDataList() {
        return groupDataList;
    }

    public void setGroupDataList(List<GroupData> groupDataList) {
        this.groupDataList = groupDataList;
    }
}
