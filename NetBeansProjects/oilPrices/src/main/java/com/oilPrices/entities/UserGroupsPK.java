/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fran
 */
@Embeddable
public class UserGroupsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "userID")
    private int userID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "group_id")
    private int groupId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "groups_group_id")
    private int groupsGroupId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "users_userID")
    private int usersuserID;

    public UserGroupsPK() {
    }

    public UserGroupsPK(int userID, int groupId, int groupsGroupId, int usersuserID) {
        this.userID = userID;
        this.groupId = groupId;
        this.groupsGroupId = groupsGroupId;
        this.usersuserID = usersuserID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupsGroupId() {
        return groupsGroupId;
    }

    public void setGroupsGroupId(int groupsGroupId) {
        this.groupsGroupId = groupsGroupId;
    }

    public int getUsersuserID() {
        return usersuserID;
    }

    public void setUsersuserID(int usersuserID) {
        this.usersuserID = usersuserID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userID;
        hash += (int) groupId;
        hash += (int) groupsGroupId;
        hash += (int) usersuserID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroupsPK)) {
            return false;
        }
        UserGroupsPK other = (UserGroupsPK) object;
        if (this.userID != other.userID) {
            return false;
        }
        if (this.groupId != other.groupId) {
            return false;
        }
        if (this.groupsGroupId != other.groupsGroupId) {
            return false;
        }
        if (this.usersuserID != other.usersuserID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.UserGroupsPK[ userID=" + userID + ", groupId=" + groupId + ", groupsGroupId=" + groupsGroupId + ", usersuserID=" + usersuserID + " ]";
    }
    
}
