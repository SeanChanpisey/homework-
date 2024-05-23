package view;

import exception.CourseNotFoundException;
import model.Course;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Course{


    public static void main(String[] args) throws CourseNotFoundException {
        List<Course> courses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("================================");

            System.out.println("1. Add new course");
            System.out.println("2. List courses");
            System.out.println("3. Find course by ID");
            System.out.println("4. Find course by Name");
            System.out.println("5. Remove course by ID");
            System.out.println("0. Exit");
            System.out.println("================================");
            System.out.print("Please select an option:");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    System.out.print("[+] Enter course ID:");
                    int newId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Check if the ID already exists
                    boolean idExists = courses.stream()
                            .anyMatch(course -> course.getId() == newId);

                    if (idExists) {
                        System.out.println("A course with the same ID already exists.");
                    } else {
                        System.out.print("[+] Enter course title:");
                        String newTitle = scanner.nextLine();

                        System.out.print("[+] Enter instructor names (separated by commas):");
                        String[] instructorNamesArray = scanner.nextLine().split(",");
                        List<String> newInstructorNames = Arrays.stream(instructorNamesArray)
                                .map(String::trim)
                                .collect(Collectors.toList());

                        System.out.print("[+] Enter requirements (separated by commas):");
                        String[] requirementsArray = scanner.nextLine().split(",");
                        List<String> newRequirements = Arrays.stream(requirementsArray)
                                .map(String::trim)
                                .collect(Collectors.toList());

                        // Create the new course
                        Course newCourse = new Course(newId, newTitle, newInstructorNames, newRequirements);
                        courses.add(newCourse);

                        System.out.println("Course added successfully.");
                    }
                    break;
                case 2:
                System.out.println("List of courses:");
                System.out.println("----------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-4s | %-20s | %-30s | %-40s | %-10s |\n", "ID", "Title", "Instructor Names", "Requirements", "Start Date");
                System.out.println("----------------------------------------------------------------------------------------------------------");
                courses.forEach(course -> {
                    LocalDate startDate = course.generateRandomStartDate(); // Retrieve the start date from the course

                    System.out.printf("| %-4d | %-20s | %-30s | %-40s | %-10s |\n",
                            course.getId(),
                            course.getTitle(),
                            String.join(", ", course.getInstructorNames()),
                            String.join(", ", course.getRequirements()),
                            startDate == null ? "Not specified" : course.generateRandomStartDate()); // Use the retrieved start date
                });
                System.out.println("----------------------------------------------------------------------------------------------------------");
                break;
                case 3:
                    System.out.print("[+] Enter course ID:");
                    int searchId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Optional<Course> matchingCourse = courses.stream()
                            .filter(course -> course.getId() == searchId)
                            .findFirst();

                    if (matchingCourse.isPresent()) {
                        Course course = matchingCourse.get();
                        System.out.println("Course found:");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        System.out.printf("| %-4s | %-20s | %-30s | %-40s | %-10s |\n", "ID", "Title", "Instructor Names", "Requirements", "Start Date");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        System.out.printf("| %-4d | %-20s | %-30s | %-40s | %-10s |\n",
                                course.getId(),
                                course.getTitle(),
                                String.join(", ", course.getInstructorNames()),
                                String.join(", ", course.getRequirements()),
                                course.generateRandomStartDate());
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                    } else {
                        throw new CourseNotFoundException("Course not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter course name or part of the name:");
                    String searchName = scanner.nextLine();

                    List<Course> matchingCourses = courses.stream()
                            .filter(course -> course.getTitle().toLowerCase().contains(searchName.toLowerCase()))
                            .collect(Collectors.toList());

                    if (matchingCourses.isEmpty()) {
                        System.out.println("No courses found with the specified name.");
                    } else {
                        System.out.println("Matching courses:");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        System.out.printf("| %-4s | %-20s | %-30s | %-40s | %-10s |\n", "ID", "Title", "Instructor Names", "Requirements", "Start Date");
                        System.out.println("---------------------------------------------------------------------------------------------------------");
                        for (Course course : matchingCourses) {
                            System.out.printf("| %-4d | %-20s | %-30s | %-40s |\n",
                                    course.getId(),
                                    course.getTitle(),
                                    String.join(", ", course.getInstructorNames()),
                                    String.join(", ", course.getRequirements()));
//                                    course.getStartDate() == null ? "Not specified" : course.getStartDate());
                        }
                        System.out.println("---------------------------------------------------------------------------------------------------------");
                    }
                    break;
                case 5:
                    System.out.print("[+] Enter course to remove by ID:");
                    int removeId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    boolean removed = courses.removeIf(course -> course.getId() == removeId);
                    if (removed) {
                        System.out.println("Course removed successfully!");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

    }

    // Custom method to parse a string into a Date object


}
