/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.FileController.java
 * Created On: Jan 5, 2012
 */

package org.mkcl.els.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * The Class FileController.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Controller
@RequestMapping("/file")
public class FileController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory
            .getLogger(FileController.class);

    /**
     * Creates the.
     *
     * @param file the file
     * @param modelMap the model map
     * @param request the request
     * @param response the response
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String create(@RequestParam(required=false) final MultipartFile file,
                         final ModelMap modelMap,
                         final HttpServletRequest request,
                         final HttpServletResponse response) throws IOException {
        String fileName = file.getOriginalFilename().toLowerCase();
        String qParam = request.getParameter("ext");
        long sizeAllowed = Long.parseLong(qParam.split("#")[1]);
        String[] extensions = qParam.split("#")[0].split(",");
        Document document = new Document();
        if (file.getSize() <= sizeAllowed) {
            for (String i : extensions) {
                if (fileName.endsWith(i)) {
                    document.setCreatedOn(new Date());
                    document.setFileData(file.getBytes());
                    document.setOriginalFileName(file.getOriginalFilename());
                    document.setFileSize(file.getSize());
                    document.setType(file.getContentType());
                    document.setTag(CustomParameter.findByName("FILE_PREFIX")
                            .getValue()
                            + String.valueOf(UUID.randomUUID().hashCode()));

                    document = document.persist();
                }
            }
        }
        modelMap.addAttribute("file", document);
        return "document";

    }

    /**
     * Gets the.
     *
     * @param tag the tag
     * @param request the request
     * @param response the response
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "{tag}", method = RequestMethod.GET)
    public void get(@PathVariable final String tag,
                    final HttpServletRequest request,
                    final HttpServletResponse response) {
        Document document = Document.findByTag(tag);
        try {
            response.setContentType(document.getType());
            response.setContentLength((int) document.getFileSize());
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + document.getOriginalFileName() + "\"");
            FileCopyUtils.copy(
                    document.getFileData(), response.getOutputStream());
        } catch (IOException e) {
            logger.error("Error occured while downloading file:" + e.toString());
        }
    }
    
    /**
     * Gets the document info.
     *
     * @param tag the tag
     * @param modelMap the model map
     * @param request the request
     * @param response the response
     * @return the document info
     */
    @RequestMapping(value="{tag}/info", method = RequestMethod.GET)
    public @ResponseBody String getDocumentInfo(@PathVariable final String tag,
                    final ModelMap modelMap,
                    final HttpServletRequest request,
                    final HttpServletResponse response) {
        Document document = Document.findByTag(tag);
        return document.getTag();
    }


    /**
     * Gets the image.
     *
     * @param tag the tag
     * @param request the request
     * @param response the response
     * @return the image
     */
    @RequestMapping(value = "photo/{tag}", method = RequestMethod.GET)
    public void getImage(@PathVariable final String tag,
                         final HttpServletRequest request,
                         final HttpServletResponse response) {
        Document document = Document.findByTag(tag);
        try {
            response.setContentType(document.getType());
            response.setContentLength((int) document.getFileSize());
            FileCopyUtils.copy(
                    document.getFileData(), response.getOutputStream());
        } catch (IOException e) {
            logger.error("Error occured while downloading file:" + e.toString());
        }
        return;
    }

    /**
     * Removes the.
     *
     * @param tag the tag
     * @param request the request
     * @return true, if successful
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    @RequestMapping(value = "remove/{tag}", method = RequestMethod.DELETE)
    public @ResponseBody
         boolean remove(@PathVariable("tag") final String tag,
                   final HttpServletRequest request) {
        Document document = Document.findByTag(tag);
        document.remove();
        return true;
    }

}
