package com.app.jmspoc.controller.web.dashboard;

import com.app.jmspoc.service.business.AuthUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardRedirectController {
    private final AuthUserService authUserService;

    public DashboardRedirectController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping
    public String dashboard() {
        if (authUserService.authUserIsAdminGroup()) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }
}