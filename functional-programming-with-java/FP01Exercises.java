import java.util.List;

public class FP01Exercises {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 4, 6, 3, 23, 87, 12);

        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Kubernetes", "Docker", "Azure");

        numbers.stream().filter(number -> number%2 == 1).forEach(System.out::println);

        courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);
    }
}
