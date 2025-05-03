package al.sda;

import al.sda.domain.user.UserService;
import al.sda.shared.command.CommandParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final CommandParser commandParser = new CommandParser();

        final UserService userService = new UserService();
        final UserService userService2 = new UserService();

        final Application application = new Application();


    }
}
