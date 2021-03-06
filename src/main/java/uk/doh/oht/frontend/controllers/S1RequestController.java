package uk.doh.oht.frontend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.frontend.service.RetrieveRegistrationsDataService;
import uk.doh.oht.frontend.utils.OHTFrontendConstants;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by peterwhitehead on 03/05/2017.
 */
@Controller
@Slf4j
@RequestMapping("/request")
public class S1RequestController {
    private final RetrieveRegistrationsDataService retrieveRegistrationsDataService;

    @Inject
    public S1RequestController(final RetrieveRegistrationsDataService retrieveRegistrationsDataService) {
        this.retrieveRegistrationsDataService = retrieveRegistrationsDataService;
    }

    @GetMapping(value = "/s1-request", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPendingS1Request(final Model model, final HttpSession httpSession) {
        try {
            log.info("Enter getPendingS1Request");
            final List<PendingRegistrationData> pendingRegistrationDataList = retrieveRegistrationsDataService.getPendingS1Requests();
            if (CollectionUtils.isEmpty(pendingRegistrationDataList)) {
                return "redirect:/no-more-cases-in-queue";
            }
            httpSession.setAttribute(OHTFrontendConstants.S1_PENDING_REGISTRATION_REQUEST, pendingRegistrationDataList.get(0));
            model.addAttribute(OHTFrontendConstants.S1_PENDING_REQUEST, pendingRegistrationDataList.get(0));
            return "request/s1-request";
        } finally {
            log.info("Exit getPendingS1Request");
        }
    }
}
