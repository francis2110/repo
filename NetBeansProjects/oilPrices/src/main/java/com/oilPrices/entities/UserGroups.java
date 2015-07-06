/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "user_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroups.findAll", query = "SELECT u FROM UserGroups u"),
    @NamedQuery(name = "UserGroups.findByUserID", query = "SELECT u FROM UserGroups u WHERE u.userGroupsPK.userID = :userID"),
    @NamedQuery(name = "UserGroups.findByGroupId", query = "SELECT u FROM UserGroups u WHERE u.userGroupsPK.groupId = :groupId"),
    @NamedQuery(name = "UserGroups.findByGroupsGroupId", query = "SELECT u FROM UserGroups u WHERE u.userGroupsPK.groupsGroupId = :groupsGroupId"),
    @NamedQuery(name = "UserGroups.findByUsersuserID", query = "SELECT u FROM UserGroups u WHERE u.userGroupsPK.usersuserID = :usersuserID")})
public class UserGroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserGroupsPK userGroupsPK;
    @JoinColumn(name = "groups_group_id", referencedColumnName = "group_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groups groups;
    @JoinColumn(name = "users_userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UserGroups() {
    }

    public UserGroups(UserGroupsPK userGroupsPK) {
        this.userGroupsPK = userGroupsPK;
    }

    public UserGroups(int userID, int groupId, int groupsGroupId, int usersuserID) {
        this.userGroupsPK = new UserGroupsPK(userID, groupId, groupsGroupId, usersuserID);
    }

    public UserGroupsPK getUserGroupsPK() {
        return userGroupsPK;
    }

    public void setUserGroupsPK(UserGroupsPK userGroupsPK) {
        this.userGroupsPK = userGroupsPK;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userGroupsPK != null ? userGroupsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroups)) {
            return false;
        }
        UserGroups other = (UserGroups) object;
        if ((this.userGroupsPK == null && other.userGroupsPK != null) || (this.userGroupsPK != null && !this.userGroupsPK.equals(other.userGroupsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oilPrices.entities.UserGroups[ userGroupsPK=" + userGroupsPK + " ]";
    }
    
}
