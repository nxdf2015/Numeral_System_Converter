package converter;

import java.util.Scanner;

public class Main {
    public static String convert(int value, int radix) {
        StringBuilder builder = new StringBuilder();
        if (value == 0) {
            return prefix(radix) + "0";
        }
        while(value > 0){
            int remainder = value % radix;

            if(remainder < 10) {
                builder.append(remainder);
            }
            else {
                char digit  =  (char) ('a' + remainder - 10 ) ;
                builder.append(digit);
            }

            value = value / radix;
        }

        return prefix(radix) + builder.reverse().toString();
    }

    public static String prefix(int radix){
        switch (radix){
            case 2:
                return "0b";
            case 8:
                return "0";
            case 16:
                return "0x";
        }
        return "";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input:");
        int number = scanner.nextInt();
        int radix = scanner.nextInt();


        System.out.println("Output:");
        System.out.println(convert(number,radix));


    }
}
