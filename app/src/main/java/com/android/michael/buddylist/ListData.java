package com.android.michael.buddylist;

/**
 * Created by Michael on 5/6/2017.
 */

public class ListData {
    private int ListID;
    private String ListTitle;
    private String CreationDate;

    public ListData(int listID, String listTitle, String creationDate) {
        ListID = listID;
        ListTitle = listTitle;
        CreationDate = creationDate;
    }

    public int getListID() {
        return ListID;
    }

    public void setListID(int listID) {
        ListID = listID;
    }

    public String getListTitle() {
        return ListTitle;
    }

    public void setListTitle(String listTitle) {
        ListTitle = listTitle;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }
}
