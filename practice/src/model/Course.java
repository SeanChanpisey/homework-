package model;

import exception.CourseNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Course {
    private int id;
    private String title;
    private List<String> instructorNames;
    private List<String> requirements;

    private static final Random random = new Random();
    private static final LocalDate MIN_START_DATE = LocalDate.of(2022, 1, 1);
    private static final LocalDate MAX_START_DATE = LocalDate.of(2024, 12, 31);


    public Course(int id, String title, List<String> instructorNames, List<String> requirements) {
        this.id = id;
        this.title = title;
        this.instructorNames = instructorNames;
        this.requirements = requirements;

    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getInstructorNames() {
        return instructorNames;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructorNames(List<String> instructorNames) {
        this.instructorNames = instructorNames;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }


    public LocalDate generateRandomStartDate() {

            long minDay = Course.MIN_START_DATE.toEpochDay();
            long maxDay = Course.MAX_START_DATE.toEpochDay();
            long randomDay = minDay + random.nextInt((int) (maxDay - minDay));
            return LocalDate.ofEpochDay(randomDay);

    }

    public LocalDate getStartDate() {
        return null;
    }
}



