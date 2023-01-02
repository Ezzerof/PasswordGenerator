import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private int passwordLength = 14;
    private boolean insertSymbols;
    private String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private String numbers = "0123456789";
    private String symbols = "!@$%^&*()_+-={}[]?<>";
    private String[] password = new String[passwordLength];
    Random r = new Random();

    PasswordGenerator(boolean insertSymbols) {
        this.insertSymbols = insertSymbols;
    }

    protected void generate() {
        int count = 0;
        while (count != passwordLength) {
            count += createPasswordBase(letters);

            if (insertSymbols) {
                int difference = passwordLength - 2 - count;
                for (int i = count; i < passwordLength - 1; i++) {
                    int firstLetter = r.nextInt(numbers.length());
                    password[i] = numbers.substring(firstLetter, firstLetter + 1);

                }
                count += difference;

                addLetter(count, symbols);
                count += 2;
            } else {
                int difference = passwordLength - count;
                addLetter(count, numbers);
                count += difference;
            }
        }
    }

    private void addLetter(int count, String type) {
        for (int i = count; i < passwordLength; i++) {
            int firstLetter = r.nextInt(type.length());
            password[i] = type.substring(firstLetter, firstLetter + 1);
        }
    }

    private int createPasswordBase(String type) {
        int rNum = r.nextInt(4, passwordLength - 4);
        for (int i = 0; i < rNum; i++) {
            int firstLetter = r.nextInt(type.length());
            password[i] = type.substring(firstLetter, firstLetter + 1);
        }
        return rNum;
    }

    public String getPassword() {
        String pass = "";

        List<String> list = Arrays.asList(password);
        Collections.shuffle(list);

        for (String letter : list) {
            pass += letter;
        }

        return pass;
    }

}
