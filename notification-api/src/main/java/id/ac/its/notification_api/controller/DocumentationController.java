package id.ac.its.notification_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping
@ApiIgnore
public class DocumentationController {

    @GetMapping
    public String redirect() {
        return "redirect:/swagger-ui.html";
    }
}
