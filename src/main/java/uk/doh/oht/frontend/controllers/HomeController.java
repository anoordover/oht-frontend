package uk.doh.oht.frontend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by peterwhitehead on 28/04/2017.
 */
@Controller
@Slf4j
public class HomeController {

    @GetMapping("/home")
    public String displayRetrieveNextCase(final Model model) {
        try {
            log.info("Enter displayRetrieveNextCase");
            //add logged in users first name to model
            model.addAttribute("userFirstName",
                    StringUtils.capitalize(
                            ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        } finally {
            log.info("Exit displayRetrieveNextCase");
        }
        return "home";
    }
}
