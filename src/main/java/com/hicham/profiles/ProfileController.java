package com.hicham.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/profiles/random")
    public Profile getRandom(){
        return profileRepository.getRandomProfile();
    }
}
