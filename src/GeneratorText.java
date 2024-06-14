import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class GeneratorText {

    Double amount;
    LocalTime date;
    String prototypeEN = "this morning's event cost us"+amount+"and ends on "+date+" at montreal's digital center.";
    String prototypeFR = "l'événement de ce matin nous a coûté 25 000 dollars et se termine le 14 juin au centre numérique de montréal.";

    public String generatorString(String prototype) {
        Double amount = this.getAmount(prototype);
        LocalDate date = this.getDate(prototype);

        return "Extracted amount: " + amount + ", Extracted date: " + date;
    }

    private Double getAmount(String prototype) {
        String amountPattern = "\\$(\\d+\\.\\d{2})";
        Pattern pattern = Pattern.compile(amountPattern);
        Matcher matcher = pattern.matcher(prototype);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }

        return null;
    }
    private LocalDateTime getDateTime(String prototype) {
        String datePattern = "(\\d{4}-\\d{2}-\\d{2})";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(prototype);

        if (matcher.find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDateTime.parse(matcher.group(1), formatter);
        }

        return null;
    }

    private LocalDate getDate(String prototype) {
        String datePattern = "(\\d{4}-\\d{2}-\\d{2})";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(prototype);

        if (matcher.find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(matcher.group(1), formatter);
        }

        return null;
    }
}
