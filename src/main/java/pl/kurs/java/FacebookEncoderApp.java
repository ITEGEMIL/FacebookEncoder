package pl.kurs.java;

import java.io.IOException;
import java.util.Scanner;

public class FacebookEncoderApp {
    private FacebookService facebookService;
    private Scanner scanner;


    public FacebookEncoderApp() throws IOException {
        scanner = new Scanner(System.in);
        initializeService();
        System.out.println("Witam Cię w Facebook encoder");
    }

    private void initializeService() throws IOException {
        //System.out.println("Podaj sceizke do plików");
        //String path = getPathFromUser();
        facebookService = new FacebookService("message_1.json");
        System.out.println("\nPlik gotowy do pracy" + "\n");
    }

    public void start() throws IOException {
        showMenu();
        int userChoice = scanner.nextInt();
        if (userChoice != 0) {
            doUserChoice(userChoice);
            start();
        }
    }

    private void doUserChoice(int userChoice) throws IOException {
        switch (userChoice) {
            case 2:
                System.out.println(facebookService.countMessagePerPerson());
                break;
            case 3:
                System.out.println(facebookService.countWordsPerPerson());
                break;
        }

    }

    private String getPathFromUser() {
        return scanner.next();
    }

    private void showMenu() {
        System.out.println("2. Statystya wiadomości per osoba");
        System.out.println("3. Najczęstrze słowo per osoba");
        System.out.println("4. Najczęstrza emotka per osoba");
        System.out.println("5. Aktywoność per dzień tygodnia");
        System.out.println("6. Aktywoność per miesiąc");
        System.out.println("7. Aktywoność per rok");
    }
}
