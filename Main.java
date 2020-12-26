package converter;

import java.util.Scanner;

public class Main {
    /**
     * @apiNote convert a decimal number to radix number
     * @implSpec 1 < radix
     * @param value int
     * @param radix int
     * @return string
     */
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

    /**
     * @implNote  convert a number from sourceRadix to targetRadix
     * @implSpec 1 <= sourceRadix  and 1 <= targetRadix
     * @param number string
     * @param sourceRadix int
     * @param targetRadix int
     * @return
     */
    public static String convert(String number, int sourceRadix, int targetRadix) {
        int value = 0;
        String output = "";

        if (sourceRadix == 1) {
            value   =  number.length();
        } else {
            value = Integer.parseInt(number, sourceRadix);
        }

        if(targetRadix == 1){
            output = "1".repeat(value);
        } else {
            output = Integer.toString(value, targetRadix);
        }

        return output;

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

    public static double fractionToDecimal(String number, int radix) {
        double  result = 0;
        double m = 1.0 / radix;

        for(int i = number.length() - 1 ; i >= 0; i--) {


            int value = 0;
            try {
                value = Integer.parseInt(number.substring(i, i + 1));
            }
            catch (NumberFormatException e){
                value = number.charAt(i) - 'a'  + 10;
            }
            result = m * (value + result);

        }
        return result;
    }

    public static String fraction10ToRadix(double value, int radix) {
        String result = "";
        for(int  i = 0; i < 5; i++) {
            int digit = (int) (value * radix);

            if (digit >= 10) {
                result += "" +(char)('a' + digit - 10);
            } else {
                result += digit;
            }
            value = value * radix - digit;
        }
        return result;
    }

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        System.out.println("Input:");
        int sourceRadix = Integer.parseInt(scanner.nextLine());

        String  number = scanner.nextLine();
        int targetRadix = Integer.parseInt(scanner.nextLine());
        // get integer and fractional part of the number
        String result = "";
        if (sourceRadix > 1 && targetRadix > 1) {
            String integerValue = "";
            String fractionalValue = "";

            try {
                String[] fraction = number.split("\\.");
                integerValue = fraction[0];
                fractionalValue = fraction[1];
            } catch (Exception e) {
                integerValue = number;
                fractionalValue = "";
            }
            //convert integer part from sourceRadix to decimal
            result = convert(integerValue,sourceRadix,targetRadix);

            //convert fractional part
            String fractionalResult = "";
            if (fractionalValue.isEmpty()) {
                fractionalResult  = "";
            } else {
                double fraction10 = fractionToDecimal(fractionalValue, sourceRadix);
                fractionalResult = fraction10ToRadix(fraction10,targetRadix);
            }


            if (!fractionalValue.isEmpty()){
                result += "." + fractionalResult;
            }
        } else {
            result = convert(number,sourceRadix, targetRadix);
        }




        System.out.println("Output:");
        System.out.println(result);


    }
}
