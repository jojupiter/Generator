import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Communs {
    public static void main(String[] args) {
        String prototypeEN = "this morning's event cost us $1234.56 and ends on 2024-06-14 at Montreal's digital center.";

        Communs main = new Communs();
        String result = main.generatorString(prototypeEN, "fr");
        System.out.println(result);
    }

    public String generatorString(String prototype, String language) {
        String translatedPrototype = translateText(prototype, language);
        return translatedPrototype;
    }

    private String translateText(String prototype, String language) {
        // Remplacer les montants
        prototype = replaceAmounts(prototype, language);

        // Remplacer les dates
        prototype = replaceDates(prototype, language);

        return prototype;
    }

    private String replaceAmounts(String text, String language) {
        String amountPattern = "\\$(\\d+\\.\\d{2})";
        Pattern pattern = Pattern.compile(amountPattern);
        Matcher matcher = pattern.matcher(text);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            double amount = Double.parseDouble(matcher.group(1));
            String translatedAmount = formatCurrency(amount, language);
            matcher.appendReplacement(result, translatedAmount);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public  String formatCurrency(double amount, String language) {
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
    private String replaceDates(String text, String language) {
        String datePattern = "\\b(\\d{4}-\\d{2}-\\d{2})\\b";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(text);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            LocalDate date = LocalDate.parse(matcher.group(1));
            String translatedDate = formatDate(date, language);
            matcher.appendReplacement(result, translatedDate);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private String formatDate(LocalDate date, String language) {
        // Implémentez ici la fonction formatDate pour traduire les dates dans la langue spécifiée
        // Je suppose que vous avez déjà cette fonction implémentée, comme mentionné dans les précédentes réponses
        // Pour l'exemple, je vais simplement retourner une chaîne vide
        return "";
    }
}
