package input;

import java.util.Scanner;

public class GameInput {

    public String getHumanPlayerName() {
        System.out.println("Please type in the name of your player:");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        // replace newlines with spaces
        input = input.replace("\r\n", " ").replace("\n", " ");

        return input;
    }

    public String getBotName() {
        System.out.println("Please type in the name of your bot:");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        // replace newlines with spaces
        input = input.replace("\r\n", " ").replace("\n", " ");

        return input;
    }
}
