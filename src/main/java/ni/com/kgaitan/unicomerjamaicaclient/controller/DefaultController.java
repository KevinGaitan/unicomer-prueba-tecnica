package ni.com.kgaitan.unicomerjamaicaclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {
    // redirect to swagger
    @GetMapping
    public String index() {
        return "redirect:/swagger-ui/";
    }
}
