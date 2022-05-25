package com.Jetbrains;
import java.text.DecimalFormat;
import java.util.Scanner;

public class NumberToWords {

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private NumberToWords() {
    }

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;


        } else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }

        if (number == 0) return soFar;
        return numNames[number] + " hundred" + "and" + soFar;
    }



    public static String convert(long number) {

        if (number == 0) {
            return "zero";
        }

        String sNumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        sNumber = df.format(number);

        int trillions = Integer.parseInt(sNumber.substring(0, 3));

        int billions = Integer.parseInt(sNumber.substring(3, 6));

        int millions = Integer.parseInt(sNumber.substring(6, 9));

        int hundredThousands = Integer.parseInt(sNumber.substring(9, 12));

        int thousands = Integer.parseInt(sNumber.substring(12, 15));

        String tradTrillions;
        switch (trillions) {
            case 0:
                tradTrillions = "";
                break;
            case 1:
                tradTrillions = convertLessThanOneThousand(trillions)
                        + " Trillion ";
                break;
            default:
                tradTrillions = convertLessThanOneThousand(trillions)
                        + " Trillion,";
        }
        String result = tradTrillions;

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertLessThanOneThousand(millions)
                        + " Billion, ";
                break;
            default:
                tradBillions = convertLessThanOneThousand(millions)
                        + " Billion, ";
        }
        result = result + tradBillions;
        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertLessThanOneThousand(millions)
                        + " million, ";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions)
                        + " million, ";
        }
        result = result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "one thousand,";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " thousand,";
        }

        result = result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;


        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }


    public static void main(String[] args) {

        System.out.println("Enter number in digit form: ");
        Scanner scan = new Scanner(System.in);
        long numb = scan.nextLong();


        System.out.println(numb + ":" +" "+ NumberToWords.convert(numb));
    }
}

