package cs489.entity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MainApp {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1,
                        "Daniel",
                        "Agar",
                        LocalDate.of(2018, 1, 17),
                        105945.50),
                new Employee(2,
                        "Benard",
                        "Shaw",
                        LocalDate.of(2019, 4, 3),
                        197750.00),
                new Employee(3,
                        "Carly",
                        "Agar",
                        LocalDate.of(2014, 5, 16),
                        842000.75),
                new Employee(4,
                        "Wesley",
                        "Schneider",
                        LocalDate.of(2019, 10, 2),
                        74500.00),
                new Employee(5,
                        "Joe",
                        "Dones",
                        LocalDate.of(2019, 10, 31),
                        76500.00)
        );

        Hashtable<Long, PensionPlan> pensions = new Hashtable<>();
        pensions.put(1L, new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100));
        pensions.put(3L, new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50));

        //printEmployees(employees, pensions);

        printUpcomingReport(employees, pensions);
    }

    // Implement a feature to print-out the list of all the Employees in JSON format. The
    //Company requires this list to include the Pension Plan data for each Employee (if it exist)
    //and the list is to be displayed sorted in ascending order of the Employees’ Last Names
    //and descending order of their Yearly salaries.
    private static void printEmployees(List<Employee> employees, Hashtable<Long, PensionPlan> pensions) {

        List<PensionEmployee> pensionEmployees = employees.stream()
                .map(employee -> {
                    PensionEmployee p = new PensionEmployee(employee);
                    if (pensions.containsKey(employee.getEmployeeId())) {
                        p.setPensionPlan(pensions.get(employee.getEmployeeId()));
                    }
                    return p;
                })
                .collect(Collectors.toList());

        pensionEmployees.sort(Comparator.comparing(PensionEmployee::getLastName)
                .thenComparing(Comparator.comparing(PensionEmployee::getYearlySalary).reversed()));

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pensionEmployees);
            System.out.println(jsonOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Implement a feature which print-out the data of the Monthly Upcoming Enrollees report,
    //in JSON format.
    //Any Employee in the system whose period of employment has been at least 5 years, or between
    //the first and the last day of the next month and who have NOT yet been enrolled to a Pension Plan,
    //should have their data presented in the Monthly Upcoming Enrollees report.
    //The Company requires this list to be displayed sorted in ascending order of the Employees’ employment dates.
    private static void printUpcomingReport(List<Employee> employees, Hashtable<Long, PensionPlan> pensions) {
        LocalDate today = LocalDate.now();
        YearMonth thisMonth = YearMonth.from(today);
        YearMonth nextMonth = YearMonth.from(today.plusMonths(1));
        LocalDate startOfNextMonth = nextMonth.atDay(1);
        LocalDate endOfNextMonth = nextMonth.atEndOfMonth();

        List<Employee> upcomingEnrollees = employees.stream()
                .filter(employee -> {
                    LocalDate employmentDate = employee.getEmploymentDate();
                    LocalDate empDateAfter5Years = employmentDate.plusYears(5);
                    return (empDateAfter5Years.isBefore(today) ||
                            YearMonth.from(empDateAfter5Years).equals(thisMonth) ||
                            (employmentDate.isAfter(startOfNextMonth)
                                    && employmentDate.isBefore(endOfNextMonth)))
                            && !pensions.containsKey(employee.getEmployeeId());
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .collect(Collectors.toList());

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(upcomingEnrollees);
            System.out.println(jsonOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
