package com.hicham;

import com.hicham.conversations.ConversationRepository;
import com.hicham.profiles.ProfileCreationService;
import com.hicham.profiles.ProfileRepository;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.UUID;
import java.util.List;

@SpringBootApplication
public class BackendTindeAiApplication implements CommandLineRunner {
   /* @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ImageModel openaiImageModel;*/
    @Autowired
    private ProfileCreationService profileCreationService;

    public static void main(String[] args) {
        SpringApplication.run(BackendTindeAiApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        profileCreationService.saveProfilesToDB();
       /* // 1. Générer l'image via OpenAI
        ImageResponse response = openaiImageModel.call(
                new ImagePrompt("A hyper-realistic, photo-quality portrait of a beautiful woman with a blend of Arab and Swedish features. She appears to be in her late 30s to mid-40s. Her complexion is light olive with subtle golden undertones, and she has long, wavy blonde hair with a hint of brown. Her eyes are almond-shaped and light hazel, radiating intelligence and warmth. Her smile is soft and inviting. She is dressed casually yet elegantly, and the natural lighting enhances her facial features, giving the image a realistic, high-resolution photographic feel. Skin texture, facial details, and lighting should be highly realistic.",
                        OpenAiImageOptions.builder()
                                .withQuality("hd")
                                .withN(1)
                                .withHeight(1024)
                                .withWidth(1024).build())
        );



        // Debug : Afficher la réponse complète pour vérifier
        System.out.println("###########################################");
        System.out.println("Response: " + response);
        System.out.println("###########################################");

        // 2. Récupérer l'URL de l'image générée
        String imageUrl = response.getResult().getOutput().getUrl(); // Récupérer l'URL de l'image

        // 3. Générer un nom de fichier aléatoire pour l'image
        String randomFileName = UUID.randomUUID().toString() + ".png"; // Générer un nom unique avec un UUID

        // 4. Créer le chemin où l'image sera enregistrée
        String savePath = "src/main/resources/static/images/" + randomFileName;

        // 5. Télécharger et stocker l'image dans le répertoire static/images
        downloadImage(imageUrl, savePath);

        System.out.println("Image successfully saved to: " + savePath);
    }

    private void downloadImage(String imageUrl, String savePath) throws IOException {
        // Ouvrir l'URL de l'image
        URL url = new URL(imageUrl);
        InputStream in = url.openStream();

        // Créer le chemin pour le fichier
        Path path = Paths.get(savePath);
        Files.createDirectories(path.getParent()); // Créer les répertoires nécessaires si inexistants

        // Sauvegarder l'image sur le disque
        try (OutputStream out = new FileOutputStream(path.toFile())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } finally {
            in.close(); // Fermer le flux d'entrée
        }
    }*/
    }
}
