import java.util.List;

public class FP01Structured {
    public static void main(String[] args) {

        printAllNumbersInListStructured(List.of(12,3,5,2,43,5,35,9,80));
    }

    private static void printAllNumbersInListStructured(List<Integer> numbers){
        for(int number: numbers){
            System.out.println(number);
        }
    }
    
}