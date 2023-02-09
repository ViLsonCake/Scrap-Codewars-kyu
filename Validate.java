package project.files;

public class Validate {

    public static boolean validDifficulty(String[] arrayOfDifficulty) {

        for (String difficulty : arrayOfDifficulty) {
            try {
                int value = Integer.parseInt(difficulty);

                if (value < 1 || value > 8) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    public static boolean validLanguage(String language) {
        switch (language.toLowerCase()) {
            case "cpp", "c", "csharp", "java", "python", "javascript", "kotlin", "php" -> {
                return true;
            }
        }

        return false;
    }

}
