import java.util.List;

public class FP01Functional {
    public static void main(String[] args) {

        printAllNumbersInListStructured(List.of(12,3,5,2,43,5,35,9,80));
    }

    private static void print(int number){
        System.out.println(number);
    }

    private static boolean isEven(int number){
        return number%2 == 0;
    }

    private static void printAllNumbersInListStructured(List<Integer> numbers){
        numbers.stream().filter(FP01Functional :: isEven).forEach(FP01Functional::print);
    }
}
