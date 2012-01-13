/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.PartyController.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.controller;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.Document;
import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.Party;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class PartyController.
 *
 * @author meenalw
 * @since v1.0.0
 */
@Controller
@RequestMapping("/party")
public class PartyController extends BaseController {

    /**
     * List.
     *
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        Grid grid = Grid.findByName("PARTY_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/parties/list";
    }

    /**
     * New form.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model, final Locale locale) {
        Party party = new Party();
        party.setLocale(locale.toString());
        model.addAttribute(party);
        model.addAttribute(
                "photoExt", CustomParameter.findByName("PARTY_FLAG_EXTENSION")
                        .getValue());
        model.addAttribute("photoSize", Long.parseLong(CustomParameter
                .findByName("PARTY_FLAG_SIZE").getValue()) * 1024 * 1024);
        return "masters/parties/new";
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable final Long id, final ModelMap model) {
        Party party = Party.findById(id);
        model.addAttribute(party);
        model.addAttribute(
                "photoExt", CustomParameter.findByName("PHOTO_EXTENSION")
                        .getValue());
        model.addAttribute("photoSize", Long.parseLong(CustomParameter
                .findByName("PHOTO_SIZE").getValue()) * 1024 * 1024);
        if (party.getPhoto() != null && (!party.getPhoto().isEmpty())) {
                final Document document = Document.findByTag(party.getPhoto());
                if (document != null) {
                    model.addAttribute(
                            "photoOriginalName", document.getOriginalFileName());
                }
        }
        return "masters/parties/edit";
    }

    /**
     * Creates the.
     *
     * @param party the party
     * @param result the result
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("party") final Party party,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(party, result);

        if (result.hasErrors()) {
            model.addAttribute("party", party);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/parties/new";
        }

        party.persist();
        return "redirect:party/" + party.getId()
                + "/edit?type=success&msg=create_success";
    }

    /**
     * Update.
     *
     * @param party the party
     * @param result the result
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("party") final Party party,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(party, result);
        if (result.hasErrors()) {
            model.addAttribute("party", party);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/parties/edit";
        }

        party.update();
        return "redirect:party/" + party.getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        Party.findById(id).remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Validate.
     *
     * @param party the party
     * @param errors the errors
     * @author meenalw
     * @since v1.0.0
     */
    private void validate(final Party party, final Errors errors) {
        if (party.getName() != null) {
            if (party.getLocale().equals("en")) {
                String name = party.getName();
                Pattern pattern = Pattern.compile("[A-Za-z//(//) ]{1,100}");
                Matcher matcher = pattern.matcher(name);
                if (!matcher.matches()) {
                    errors.rejectValue("name", "Pattern");
                }
                if (name.length() > 100 || name.length() < 1) {
                    errors.rejectValue("name", "Size");

                }
            }
        }
        if (party.getAbbreviation() != null) {
            if (party.getLocale().equals("en")) {
                String abbreviation = party.getAbbreviation();
                Pattern pattern = Pattern.compile("[A-Za-z ]{1,30}");
                Matcher matcher = pattern.matcher(abbreviation);
                if (!matcher.matches()) {
                    errors.rejectValue("abbreviation", "Pattern");
                }
                if (abbreviation.length() > 30 || abbreviation.length() < 1) {
                    errors.rejectValue("abbreviation", "Size");

                }
            }
        }
        Party duplicateParty = Party.findByName(party.getName());
        if (duplicateParty != null) {
            if (!duplicateParty.getId().equals(party.getId())) {
                errors.rejectValue("name", "NonUnique");
            }
        }

        // Check if the version matches
        if (party.getId() != null) {
            if (!party.getVersion().equals(
                    Party.findById(party.getId()).getVersion())) {
                errors.rejectValue("name", "Version_Mismatch");
            }
        }
    }
}
