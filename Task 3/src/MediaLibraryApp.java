import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Media {
    private String title;
    public Media(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    @Override
    public String toString() {
        return "Медіа - Назва: " + title;
    }
}
class Library {
    private List<Media> items = new ArrayList<>();
    public void addItem(Media item) {
        items.add(item);
        System.out.println(item.getTitle() + " додано.");
    }
    public void removeItem(String title) {
        items.removeIf(item -> item.getTitle().equalsIgnoreCase(title));
        System.out.println("Медіа '" + title + "' видалено.");
    }
    public void searchItem(String query) {
        items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(query.toLowerCase()))
                .forEach(System.out::println);
    }
    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
        } else {
            items.forEach(System.out::println);
        }
    }
}
public class MediaLibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати медіа");
            System.out.println("2. Видалити медіа");
            System.out.println("3. Пошук медіа");
            System.out.println("4. Переглянути всі медіа");
            System.out.println("5. Вийти");
            System.out.print("Ваш вибір: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Введіть назву медіа: ");
                    String title = scanner.nextLine();
                    library.addItem(new Media(title));
                }
                case 2 -> {
                    System.out.print("Введіть назву для видалення: ");
                    String title = scanner.nextLine();
                    library.removeItem(title);
                }
                case 3 -> {
                    System.out.print("Введіть запит для пошуку: ");
                    String query = scanner.nextLine();
                    library.searchItem(query);
                }
                case 4 -> library.listItems();
                case 5 -> {
                    System.out.println("Вихід з програми.");
                    return;
                }
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }
}