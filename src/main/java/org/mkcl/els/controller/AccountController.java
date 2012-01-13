/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.AccountController.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els.controller;

import org.mkcl.els.common.vo.Password;
import org.mkcl.els.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class AccountController.
 *
 * @author amitb
 * @version v1.0.0
 */
@Controller
@RequestMapping("/acct")
public class AccountController extends BaseController {

    /**
     * Gets the.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(method = RequestMethod.GET)
    public String get(final ModelMap model) {
        return "account/acct";
    }

    /**
     * Gets the password form.
     *
     * @param model the model
     * @return the password form
     */
    @RequestMapping(value = "changepwd", method = RequestMethod.GET)
    public String getPasswordForm(final ModelMap model) {
        final Password password = new Password();
        model.addAttribute("password", password);
        return "account/change_pwd";
    }

    /**
     * Gets the about details.
     *
     * @param model the model
     * @return the about details
     */
    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String getAboutDetails(final ModelMap model) {
        return "account/about_me";
    }

    /**
     * Change password.
     *
     * @param password the password
     * @param result the result
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "changepwd", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute final Password password,
            final BindingResult result, final ModelMap model) {
        this.validatePassword(password, result);
        if (result.hasErrors()) {
            model.addAttribute("isvalid", false);
            return "redirect:changepwd?type=error&msg=update_failed";
        }
        User.changePassword(this.getCurrentUser().getUsername(),
                password.getNewPassword());
        model.addAttribute("password", password);
        model.addAttribute("updateflag", true);
        return "redirect:changepwd?type=success&msg=update_success";
    }

    /**
     * Validate password.
     *
     * @param password the password
     * @param errors the errors
     */
    private void validatePassword(final Password password, final Errors errors) {
        if (!password.getOldPassword().equals(
                this.getCurrentUser().getPassword())) {
            errors.rejectValue("oldPassword", "Mismatch");
        }
    }
}
