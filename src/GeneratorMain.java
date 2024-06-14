import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.text.NumberFormat;
import java.util.Locale;
import java.text.DecimalFormat;
import java.util.Currency;
import java.text.DecimalFormatSymbols;


public class GeneratorMain {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        // Exemples d'utilisation de la fonction
        System.out.println(formatDate(now, "en"));
        System.out.println(formatDate(now, "fr"));

        double amount = 1234567.89;
        // Exemples d'utilisation de la fonction
        System.out.println(formatCurrency(amount, "en"));
        System.out.println(formatCurrency(amount, "fr"));


        String[][] texts = {
                {"Apple", "Pomme"},
                {"Banana", "Banane"},
                {"Cherry", "Cerise"},
                {"Date", "Datte"},
                {"Elderberry", "Sureau"}
        };

        // Exemples d'utilisation de la fonction
        System.out.println(getText(texts, 0, "en")); // Devrait renvoyer "Apple"
        System.out.println(getText(texts, 0, "fr")); // Devrait renvoyer "Pomme"
        System.out.println(getText(texts, 2, "en")); // Devrait renvoyer "Cherry"
        System.out.println(getText(texts, 2, "fr")); // Devrait renvoyer "Cerise"

        String prototypeEN = "this morning's event cost us $1234.56 and ends on 2024-06-14 at Montreal's digital center.";

        GeneratorText generatorText = new GeneratorText();
        String result = generatorText.generatorString(prototypeEN);
        System.out.println(result);
    }

    public static String formatCurrency(double amount, String language) {
        Locale locale;

        // Définir la locale en fonction de la langue
        switch (language) {
            case "en":
                locale = Locale.US; // Utilisation de Locale.US pour l'anglais
                break;
            case "fr":
                locale = Locale.FRANCE; // Utilisation de Locale.FRANCE pour le français
                break;
            default:
                locale = Locale.US; // Valeur par défaut
                break;
        }

        // Format monétaire en fonction de la locale
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

        // Si la langue est française, remplacer le symbole de l'euro par le symbole du dollar
        if (language.equals("fr")) {
            DecimalFormatSymbols symbols = ((DecimalFormat) formatter).getDecimalFormatSymbols();
            symbols.setCurrencySymbol("$");
            ((DecimalFormat) formatter).setDecimalFormatSymbols(symbols);
        }

        return formatter.format(amount);
    }
    public static String formatDate(LocalDateTime date, String language) {
        Locale locale;

        // Définir la locale en fonction de la langue
        switch (language) {
            case "en":
                locale = Locale.US; // Utilisation de Locale.US pour l'anglais
                break;
            case "fr":
                locale = Locale.FRANCE; // Utilisation de Locale.FRANCE pour le français
                break;
            default:
                locale = Locale.US; // Valeur par défaut
                break;
        }

        // Définir le format de date en fonction de la locale
        DateTimeFormatter formatter;
        if (language.equals("en")) {
            formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy HH:mm:ss a", locale);
        } else if (language.equals("fr")) {
            formatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy HH:mm:ss", locale);
        } else {
            formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy HH:mm:ss a", locale); // Format par défaut
        }

        return date.format(formatter);
    }

    public static String getText(String[][] texts, int position, String language) {
        // Vérifier si la position est valide
        if (position < 0 || position >= texts.length) {
            return "Position invalide";
        }

        // Déterminer la colonne en fonction de la langue
        int column;
        if (language.equals("en")) {
            column = 0; // Colonne pour les textes en anglais
        } else if (language.equals("fr")) {
            column = 1; // Colonne pour les textes en français
        } else {
            return "Langue non supportée";
        }

        return texts[position][column];
    }
}
