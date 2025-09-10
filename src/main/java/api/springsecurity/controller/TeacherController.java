package api.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping
    public String getTeacher() {
        return "Teacher";
    }
}