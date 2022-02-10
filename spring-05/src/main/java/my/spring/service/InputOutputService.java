package my.spring.service;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputOutputService {

    public void printString(String key, Object[] params) {
        System.out.println();
    }

    public String readString(String key, Object[] params) {
        printString(key, params);

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        else return "";
    }
}
