package com.efernandez.rossano.controller.view;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";
    private final static String STATUS_ATTR = "jakarta.servlet.error.status_code";

    /**
     * Supports the HTML Error View
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorHtml(HttpServletRequest request) {
        return request.getAttribute(STATUS_ATTR).toString();
    }

}
