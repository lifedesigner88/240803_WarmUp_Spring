package org.example.hello2.controller.fortest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/delete-api")
public class DeleteController {

    @DeleteMapping("{variable}")
    public String DeleteVariable(@PathVariable String variable) {
        return variable;
    }

    @DeleteMapping("delete")
    public String DeleteByEmail(@RequestParam String email){
        return "email" +" : " + email;
    }
}
