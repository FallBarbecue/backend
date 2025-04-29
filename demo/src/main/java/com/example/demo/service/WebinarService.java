package com.example.demo.service;

import com.example.demo.entity.Webinar;
import com.example.demo.repository.WebinarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WebinarService {

    @Autowired
    private WebinarRepository webinarRepository;

    public List<Webinar> getAllWebinars() {
        return webinarRepository.findAll();
    }

    public Optional<Webinar> getWebinarById(Long id) {
        return webinarRepository.findById(id);
    }

    public Webinar saveWebinar(Webinar webinar) {
        return webinarRepository.save(webinar);
    }

    public void deleteWebinarById(Long id) {
        webinarRepository.deleteById(id);
    }
}
