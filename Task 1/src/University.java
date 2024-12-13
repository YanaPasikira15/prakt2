import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class University {
    private String name;
    private List<Department> departments = new ArrayList<>();
    public University(String name) {
        this.name = name;
    }
    public class Department {
        private String name;
        private List<String> professors = new ArrayList<>();
        public Department(String name) {
            this.name = name;
        }
        public void addProfessor(String professor) {
            professors.add(professor);
        }
        public void showProfessors() {
            System.out.println("Кафедра: " + name + ", Викладачі: " + professors);
        }
        @Override
        public String toString() {
            return name + " (" + professors.size() + " викладачів)";
        }
    }
    public static class Helper {
        public static double calculateAverageScore(List<Integer> scores) {
            return scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }
    }
    public void manageEvents(String event) {
        new EventHandler() {
            @Override
            public void handleEvent() {
                System.out.println("Подія: " + event + " організована для університету " + name + ".");
            }
        }.handleEvent();
    }
    interface EventHandler {
        void handleEvent();
    }
    public Department addDepartment(String name) {
        Department department = new Department(name);
        departments.add(department);
        return department;
    }
    public void showInfo() {
        System.out.println("Університет: " + name);
        departments.forEach(System.out::println);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University university = new University("Університет Києва");
        while (true) {
            System.out.println("\nМеню: 1. Додати кафедру 2. Додати викладача 3. Інформація 4. Середній бал 5. Подія 6. Вийти");
            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.print("Назва кафедри: ");
                    university.addDepartment(scanner.next());
                }
                case 2 -> {
                    if (university.departments.isEmpty()) {
                        System.out.println("Немає кафедр.");
                    } else {
                        System.out.print("Виберіть номер кафедри: ");
                        int dept = scanner.nextInt() - 1;
                        System.out.print("Ім'я викладача: ");
                        university.departments.get(dept).addProfessor(scanner.next());
                    }
                }
                case 3 -> university.showInfo();
                case 4 -> {
                    System.out.print("Оцінки через пробіл: ");
                    scanner.nextLine();
                    List<Integer> scores = List.of(scanner.nextLine().split(" "))
                            .stream().map(Integer::parseInt).toList();
                    System.out.println("Середній бал: " + Helper.calculateAverageScore(scores));
                }
                case 5 -> {
                    System.out.print("Введіть подію: ");
                    university.manageEvents(scanner.next());
                }
                case 6 -> {
                    System.out.println("Вихід.");
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }
}