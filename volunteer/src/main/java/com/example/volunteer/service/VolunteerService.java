package com.example.volunteer.service;

import com.example.volunteer.model.Volunteer;
import com.example.volunteer.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository repo;

    public List<Volunteer> getAllVolunteers() {
        return repo.findAll();
    }

    public List<Volunteer> getAvailableVolunteers() {
        return repo.findByAvailabilityTrue();
    }

    public Volunteer getVolunteerById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void saveVolunteer(Volunteer volunteer) {
        repo.save(volunteer);
    }

    public void deleteVolunteer(Long id) {
        repo.deleteById(id);
    }
}
