package com.user.management;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavi on 1/25/14.
 */
public class UserSessionBean implements UserSessionBeanRemote{

    List<String> userShelf;

    public UserSessionBean() {
        this.userShelf = new ArrayList<String>();
    }

    @Override
    public void addUser(String userName) {
        userShelf.add(userName);
    }

    @Override
    public List getUsers() {
        return userShelf;
    }
}
