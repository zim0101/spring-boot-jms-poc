package com.app.jmspoc.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/dashboard")
public class UserDashboardController {
    @GetMapping
    public String dashboard() {
        return "dashboard/user";
    }
}