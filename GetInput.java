package project.files;

import org.springframework.stereotype.Component;

@Component
public class GetInput {
    private String language;

    private String[] difficultiesArray;

    // Getters
    public String getLanguage() {
        return this.language;
    }

    public String[] getDifficulties() {
        return this.difficultiesArray;
    }

    // Setters
    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDifficulties(String [] difficultiesArray) {
        this.difficultiesArray = difficultiesArray;
    }
}
