package CaseConverter;

import java.util.Scanner;

// Convert to snake_case, SCREAMING_SNAKE_CASE, camelCase, and PascalCase
public class CaseConversion {
    public static void main(String[] args) {
        String str = "something.wrong";
        Scanner scan = new Scanner(System.in);
        System.out.println("Convert it to each case: snake, screaming_snake, camel, Pascal");
        String input = scan.nextLine();
        // Clean up string
        input = input.trim();

        // Convert to an array of strings with delimiter regular expression
        // Accounts for special characters, not parenthesis or brackets
        String arr[] = input.split("[, .!@#$%^&*]");
        System.out.println(arr.length);
        for(String s : arr) {
            System.out.println(s);
        }

        // Convert input
        CaseConversion cc = new CaseConversion();
        String snake = cc.snakeConversion(arr, false);
        String screamingSnake = cc.snakeConversion(arr, true);
        String camel = cc.camelPascalConversion(arr, false);
        String pascal = cc.camelPascalConversion(arr, true);

        // Print all cases
        System.out.println("snake_case: " + snake);
        System.out.println("SCREAMING_SNAKE_CASE: " + screamingSnake);
        System.out.println("camelCase: " + camel);
        System.out.println("PascalCase: " + pascal);
    }

    public String snakeConversion(String arr[], boolean caps) {
        if(arr.length == 0) {
            return "";
        }
        String result = "";
        for(String s : arr) {
            if(s.isEmpty()) {
                continue;
            }
            if(caps) {
                result += s.toUpperCase();
            } else {
                result += s.toLowerCase();
            }
            result += "_";
        }
        return result;
    }

    public String camelPascalConversion(String arr[], boolean pascal) {
        if(arr.length == 0) {
            return "";
        }
        String result = "";
        int index = 0;
        for(int i = index; i < arr.length; i++) {
            index++;
            if(arr[i].isEmpty()) {
                continue;
            } else {
                if(pascal) {
                    result += arr[i].substring(0,1).toUpperCase();
                } else {
                    result = arr[i].substring(0,1).toLowerCase();
                }
                if(arr[i].length() > 1) {
                    result += arr[i].substring(1).toLowerCase();
                }
                break;
            }
        }
        for(int i = index; i < arr.length; i++) {
            if(arr[i].isEmpty()) {
                continue;
            } else {
                result += arr[i].substring(0,1).toUpperCase();
                if(arr[i].length() > 1) {
                    result += arr[i].substring(1).toLowerCase();
                }
            }
        }
        return result;
    }
}
