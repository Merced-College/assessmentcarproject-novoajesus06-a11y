import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Car> cars = CarDataLoader.loadCars("Car_Data.csv");

        ArrayList<Car> working = 
            new ArrayList<>(cars.subList(0, 2000));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Car Data Analyzer ===");
            System.out.println("1. Sort by Mileage");
            System.out.println("2. Sort by Year");
            System.out.println("3. Search by Year");
            System.out.println("4. Search by Brand");
            System.out.println("5. Show Statistics");
            System.out.println("6. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    selectionSortByMileage(working);
                    System.out.println("First 10 after sort:");
                    for (int i = 0; i < 10; i++)
                        System.out.println(working.get(i));
                    break;

                case 3:
                    System.out.print("Enter year to search: ");
                    int year = sc.nextInt();
                    Car result = binarySearchByYear(working, year);
                    if (result != null)
                        System.out.println(result);
                    else
                        System.out.println("Not found.");
                    break;

                case 5:
                    printAverageMileage(working);
                    printFuelCounts(working);
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    break;
            }

        } while (choice != 6);
    }
}