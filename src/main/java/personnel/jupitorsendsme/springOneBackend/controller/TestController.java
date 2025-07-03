package personnel.jupitorsendsme.springOneBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testApi")
public class TestController {

    @GetMapping("/changeName")
    public String changeName() {
        return "Name changed successfully!";
    }
}