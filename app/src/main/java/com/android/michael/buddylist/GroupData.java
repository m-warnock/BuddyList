package com.android.michael.buddylist;

/**
 * Created by Michael on 5/4/2017.
 */

public class GroupData {
    private int GroupID;
    private String GroupName;
    private String GroupDescription;
    private String GroupLeader;
    private String CreationDate;

    public GroupData(int groupID, String groupName, String groupDescription, String groupLeader, String creationDate) {
        GroupID = groupID;
        GroupName = groupName;
        GroupDescription = groupDescription;
        GroupLeader = groupLeader;
        CreationDate = creationDate;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getGroupDescription() {
        return GroupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        GroupDescription = groupDescription;
    }

    public String getGroupLeader() {
        return GroupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        GroupLeader = groupLeader;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }
}
