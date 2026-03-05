//Jesus Novoa Vasquez
//3/5/26
//CPSC-39
import java.util.*;

public class Main {

    //sort by Mileage
    public static void selectionSortByMileage(ArrayList<Car> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getMileageKmpl() < list.get(minIndex).getMileageKmpl()) {
                    minIndex = j;
                }
            }
            Car temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    //sort by Year
    public static void selectionSortByYear(ArrayList<Car> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getYear() < list.get(minIndex).getYear()) {
                    minIndex = j;
                }
            }
            Car temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    //search by year range
    public static ArrayList<Car> searchByYearRange(ArrayList<Car> list, int startYear, int endYear) {
        ArrayList<Car> found = new ArrayList<>();
        for (Car c : list) {
            if (c.getYear() >= startYear && c.getYear() <= endYear) {
                found.add(c);
            }
        }
        return found;
    }

    //search by color
    public static ArrayList<Car> searchByColor(ArrayList<Car> list, String targetColor) {
        ArrayList<Car> found = new ArrayList<>();
        for (Car c : list) {
            if (c.getColor().equalsIgnoreCase(targetColor)) {
                found.add(c);
            }
        }
        return found;
    }

    /* 
    //search by year
    public static Car binarySearchByYear(ArrayList<Car> list, int targetYear) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midYear = list.get(mid).getYear();
            if (midYear == targetYear) return list.get(mid);
            else if (midYear < targetYear) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    //search by brand
    public static ArrayList<Car> linearSearchByBrand(ArrayList<Car> list, String targetBrand) {
        ArrayList<Car> found = new ArrayList<>();
        for (Car c : list) {
            if (c.getBrand().equalsIgnoreCase(targetBrand)) {
                found.add(c);
            }
        }
        return found;
    }
    */

    //stats of the car you searched
    public static void printAverageMileage(ArrayList<Car> list) {
        double total = 0;
        for (Car c : list) total += c.getMileageKmpl();
        System.out.println("Average Mileage: " + (total / list.size()));
    }

    public static void printFuelCounts(ArrayList<Car> list) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (Car c : list) {
            String fuel = c.getFuelType();
            counts.put(fuel, counts.getOrDefault(fuel, 0) + 1);
        }
        for (String fuel : counts.keySet()) {
            System.out.println(fuel + ": " + counts.get(fuel));
        }
    }

    //main method
    public static void main(String[] args) {

        ArrayList<Car> cars = CarDataLoader.loadCars("Car_Data.csv");
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));
        ArrayList<Car> lastFound = new ArrayList<>(); // stores last search results

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Car Data Analyzer ===");
            System.out.println("1. Sort by Mileage");
            System.out.println("2. Sort by Year");
            System.out.println("3. Search by Year Range");
            System.out.println("4. Search by Color");
            System.out.println("5. Show Found Cars");
            System.out.println("6. Show Statistics");
            System.out.println("7. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                //executes sort mileage
                case 1:
                    selectionSortByMileage(working);
                    System.out.println("First 10 after sort:");
                    for (int i = 0; i < 10; i++)
                        System.out.println(working.get(i));
                    break;

                //executes sort year
                case 2:
                    selectionSortByYear(working);
                    System.out.println("First 10 after sort:");
                    for (int i = 0; i < 10; i++)
                        System.out.println(working.get(i));
                    break;

                //executes search by year range (sorts by year first, shows top 10)
                case 3:
                    System.out.print("Enter start year: ");
                    int startYear = sc.nextInt();
                    System.out.print("Enter end year: ");
                    int endYear = sc.nextInt();
                    sc.nextLine();
                    selectionSortByYear(working);
                    lastFound = searchByYearRange(working, startYear, endYear);
                    System.out.println("Found " + lastFound.size() + " car(s). Showing top 10:");
                    for (int i = 0; i < 10 && i < lastFound.size(); i++)
                        System.out.println(lastFound.get(i));
                    break;

                //executes search by color (sorts by year first, shows top 10)
                case 4:
                    System.out.print("Enter color to search: ");
                    String color = sc.nextLine();
                    selectionSortByYear(working);
                    lastFound = searchByColor(working, color);
                    System.out.println("Found " + lastFound.size() + " car(s). Showing top 10:");
                    for (int i = 0; i < 10 && i < lastFound.size(); i++)
                        System.out.println(lastFound.get(i));
                    break;

                //shows last search results (top 10)
                case 5:
                    if (lastFound.size() == 0) {
                        System.out.println("No search results yet. Use option 3 or 4 first.");
                    } else {
                        System.out.println("Found " + lastFound.size() + " car(s). Showing top 10:");
                        for (int i = 0; i < 10 && i < lastFound.size(); i++)
                            System.out.println(lastFound.get(i));
                    }
                    break;

                /* 
                //executes search by year
                case 3:
                    System.out.print("Enter year to search: ");
                    int searchYear = sc.nextInt();
                    selectionSortByYear(working);
                    Car searchResult = binarySearchByYear(working, searchYear);
                    if (searchResult != null)
                        System.out.println(searchResult);
                    else
                        System.out.println("Not found.");
                    break;

                //executes search by brand
                case 4:
                    System.out.print("Enter brand to search: ");
                    String brand = sc.nextLine();
                    ArrayList<Car> brandResults = linearSearchByBrand(working, brand);
                    if (brandResults.size() > 0) {
                        System.out.println("Found " + brandResults.size() + " cars:");
                        for (Car c : brandResults)
                            System.out.println(c);
                    } else {
                        System.out.println("No cars found for brand: " + brand);
                    }
                    break;
                */

                //executes stats
                case 6:
                    printAverageMileage(working);
                    printFuelCounts(working);
                    break;

                //end
                case 7:
                    System.out.println("Goodbye!");
                    break;
            }

        } while (choice != 7);
    }
}
