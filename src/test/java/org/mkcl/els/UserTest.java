/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.UserTest.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class UserTest.
 */
public class UserTest extends AbstractTest {

    /**
     * Test create user with all param.
     */
    @Transactional
    @Test
    public void testCreateUserWithAllParam() {
        User user = new User("abc", "abc", true, "fname", "mname", "lname",
                "user@test.com", new Date());
        user = user.persist();
        Assert.assertNotNull("Saved User Data ", user);
    }

    /**
     * Test find by username where user exists.
     */
    @Transactional
    @Test
    public void testFindByUsernameWhereUserExists() {
        User user = new User("abc", "abc", true, "fname", "mname", "lname",
                "user@test.com", new Date());
        user = user.persist();
        User user1 = User.findByUsername(user.getUsername());
        Assert.assertNotNull("find User Data by username", user1);
    }

    /**
     * Test change password of user.
     */
    @Transactional
    @Test
    public void testChangePasswordOfUser() {
        final String changedPassword = "changeP";
        User user = new User("abc", "abc", true, "fname", "mname", "lname",
                "user@test.com", new Date());
        user = user.persist();
        User user1 = User.findByUsername(user.getUsername());
        user1.setPassword(changedPassword);
        user.update(user1);
        Assert.assertNotNull("change password for User Data ", user);
    }

    /**
     * Test find user by email.
     */
    @Transactional
    @Test
    public void testFindUserByEmail() {
        String expectedResult = "user@test.com";
        User user = new User("abc", "abc", true, "fname", "mname", "lname",
                "user@test.com", new Date());
        user = user.persist();
        User user1 = User.findByEmail(user.getEmail());
        Assert.assertEquals(expectedResult, user1.getEmail());
    }

    /**
     * Test find user by first name.
     */
    @Transactional
    @Test
    public void testFindUserByFirstName() {
        User user = new User("abc", "abc", true, "fname", "mname", "lname",
                "user@test.com", new Date());
        user = user.persist();
        User user1 = User.getUserRepository().findByFirstName(user.getFirstName());
        Assert.assertNotNull("find user by first name", user1);
    }

    /**
     * Test find user by last name.
     */
    @Transactional
    @Test
    public void testFindUserByLastName() {
        User user = new User("abc", "abc", true, "fname", "mname", "lname",
                "user@test.com", new Date());
        user = user.persist();
        User user1 = User.getUserRepository().findByLastName(user.getLastName());
        Assert.assertNotNull("find user by last name", user1);

    }
}
