/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.UserRepository.java
 * Created On: Jan 9, 2012
 */
package org.mkcl.els.repository;

import org.mkcl.els.domain.User;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class UserRepository.
 */
@Repository
public class UserRepository extends BaseRepository<User, Long> {

    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    public User findByUsername(final String username) {
        return this.searchUnique(new Search().addFilterEqual("username", username));
    }

    /**
     * Find by email.
     *
     * @param email the email
     * @return the user
     */
    public User findByEmail(final String email) {
        return this.searchUnique(new Search().addFilterEqual("email", email));
    }

    /**
     * Find by first name.
     *
     * @param firstName the first name
     * @return the user
     */
    public User findByFirstName(final String firstName) {
        return this.searchUnique(new Search().addFilterEqual("firstName", firstName));
    }

    /**
     * Find user by last name.
     *
     * @param lastName the last name
     * @return the user
     */
    public User findByLastName(final String lastName) {
        return this.searchUnique(new Search().addFilterEqual("lastName", lastName));
    }
}
