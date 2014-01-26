package com.user.management;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by kavi on 1/25/14.
 */

@Remote
public interface UserSessionBeanRemote {

    void addUser(String userName);

    List getUsers();
}
