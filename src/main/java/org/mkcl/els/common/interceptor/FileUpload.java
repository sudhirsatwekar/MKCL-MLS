package org.mkcl.els.common.interceptor;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.Document;

public class FileUpload extends HttpServlet {
    private static final Logger log =
            Logger.getLogger(FileUpload.class.getName());

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> files = upload.parseRequest(req);
            FileItem file = files.get(0);
            Document document = new Document();
            document.setCreatedOn(new Date());
            document.setFileData(file.get());
            document.setOriginalFileName(file.getName());
            document.setFileSize(file.getSize());
            document.setType(file.getContentType());
            document.setTag(CustomParameter.findByName("FILE_PREFIX")
                    .getValue()
                    + String.valueOf(System.currentTimeMillis()));

            document = document.persist();
            res.sendRedirect("file/"+document.getTag()+"/info.json");

        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}