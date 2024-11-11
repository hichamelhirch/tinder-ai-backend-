package com.hicham.profiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.io.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
@Service
public class ProfileCreationService {

    private HttpClient httpClient;

    private HttpRequest.Builder stableDiffusionRequestBuilder;

    private List<Profile> generatedProfiles = new ArrayList<>();

    private static final String PROFILES_FILE_PATH = "profiles.json";

    @Value("${startup-actions.initializeProfiles}")
    private Boolean initializeProfiles;

    @Value("${tinderai.lookingForGender}")
    private String lookingForGender;

    @Value("#{${tinderai.character.user}}")
    private Map<String, String> userProfileProperties;
    @Autowired
    private ProfileRepository profileRepository;
    @Bean
    @Description("Save the Tinder profile information")
    public Function<Profile, Boolean> saveProfile() {
        return (Profile profile) -> {
            System.out.println("This is the function that we expect to be called by Spring AI by looking at the OpenAI response");
            System.out.println(profile);
            this.generatedProfiles.add(profile);
            return true;
        };
    }


    public void saveProfilesToDB() {
        Gson gson = new Gson();
        try {
            List<Profile> existingProfiles = gson.fromJson(
                    new FileReader(PROFILES_FILE_PATH),
                    new TypeToken<ArrayList<Profile>>() {}.getType()
            );
           // profileRepository.deleteAll();
            profileRepository.saveAll(existingProfiles);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Profile profile = new Profile(
                userProfileProperties.get("id"),
                userProfileProperties.get("firstName"),
                userProfileProperties.get("lastName"),
                Integer.parseInt(userProfileProperties.get("age")),
                userProfileProperties.get("ethnicity"),
                Gender.valueOf(userProfileProperties.get("gender")),
                userProfileProperties.get("bio"),
                userProfileProperties.get("imageUrl"),
                userProfileProperties.get("myersBriggsPersonalityType")
        );
        System.out.println(userProfileProperties);
        profileRepository.save(profile);

    }

}
