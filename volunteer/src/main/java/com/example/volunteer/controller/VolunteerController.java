package com.example.volunteer.controller;

import com.example.volunteer.model.Volunteer;
import com.example.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService service;

    @GetMapping("/all")
    public String listVolunteers(Model model) {
        model.addAttribute("volunteers", service.getAllVolunteers());
        return "volunteer-list";
    }
    @GetMapping("/{id}")
    public String showVolunteer(@PathVariable Long id, Model model) {
        Volunteer volunteer = service.getVolunteerById(id);
        if (volunteer == null) {
            return "redirect:/volunteer/all?error=notfound";
        }
        model.addAttribute("volunteer", volunteer);
        return "volunteer-detail";
    }



    @GetMapping("/available")
    public String availableVolunteers(Model model) {
        model.addAttribute("volunteers", service.getAvailableVolunteers());
        return "volunteer-list";
    }


    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        return "volunteer-form";
    }

    @PostMapping
    public String saveVolunteer(@ModelAttribute("volunteer") Volunteer volunteer) {
        service.saveVolunteer(volunteer);
        return "redirect:/volunteer/all";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Volunteer volunteer = service.getVolunteerById(id);
        if (volunteer == null) {
            return "redirect:/volunteer/all?error=notfound";
        }
        model.addAttribute("volunteer", volunteer);
        return "volunteer-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteVolunteer(@PathVariable Long id) {
        service.deleteVolunteer(id);
        return "redirect:/volunteer/all";
    }
}
