package project.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UrlConstructor {

    // Console scanner
    private final Scanner scanner = new Scanner(System.in);

    // User input object
    private GetInput userInput;

    // Constructor
    @Autowired
    public UrlConstructor(GetInput userInput) {
        this.userInput = userInput;
    }

    @Autowired
    @Qualifier("getInput")
    public void setUserInput(GetInput userInput) {
        this.userInput = userInput;
    }

    public boolean getInput() {
        System.out.print("Enter your language: ");

        String language;

        while (((language = scanner.nextLine())).isEmpty()) {
            System.out.println("Field can't be empty!");
        }

        language = language.trim();

        if (language.equalsIgnoreCase("C++"))
            language = "cpp";
        else if (language.equalsIgnoreCase("C#"))
            language = "csharp";

        System.out.print("Enter difficulty or difficulties: ");

        String difficulties;

        while (((difficulties = scanner.nextLine())).isEmpty()) {
            System.out.println("Field can't be empty!");
        }

        difficulties = difficulties.trim();

        // Make array from string difficulties
        String[] difficultiesArray = difficulties.split(" ");

        // Set values
        if (Validate.validLanguage(language) && Validate.validDifficulty(difficultiesArray)) {
            userInput.setLanguage(language);
            userInput.setDifficulties(difficultiesArray);
            return true;
        } else {
            return false;
        }

    }

    public String getUrl() {
        if (!(getInput()))
            return null;

        String url = "https://www.codewars.com/kata/search/" + userInput.getLanguage().toLowerCase() + "?q=";

        for (String kyu : userInput.getDifficulties()) {
            url = url.concat("&r[]=-" + kyu);
        }

        url = url.concat("&beta=false&order_by=sort_date");

        return url;
    }
}
