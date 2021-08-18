package dev.evanhalley.googleprep;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeImportance {

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.id = 1;
        e1.importance = 2;
        e1.subordinates = Arrays.asList(2,3);
        Employee e2 = new Employee();
        e2.id = 2;
        e2.importance = 2;
        Employee e3 = new Employee();
        e3.id = 3;
        e3.importance = 2;
        getImportance(Arrays.asList(e1,e2,e3), 1);
    }

    static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeeMap = toEmployeeMap(employees);
        return calculateImportance(id, employeeMap);
    }

    private static int calculateImportance(int id, Map<Integer, Employee> employeeMap) {
        Deque<Employee> employees = new LinkedList<>();
        employees.addFirst(employeeMap.get(id));
        int importance = 0;

        while (!employees.isEmpty()) {
            Employee employee = employees.removeFirst();
            importance += employee.importance;

            for (int subordinate : employee.subordinates) {

                if (employeeMap.containsKey(subordinate)) {
                    employees.addFirst(employeeMap.get(subordinate));
                }
            }
        }
        return importance;
    }

    static Map<Integer, Employee> toEmployeeMap(List<Employee> employees) {
        return employees.parallelStream()
                .collect(Collectors.toMap(e -> e.id, Function.identity()));
    }

    public static class Point {

        final int row;
        final int col;
        final int val;
        final int length;

        public Point(int row, int col, int val, int length) {
            this.row = row;
            this.col = col;
            this.val = val;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (row != point.row) return false;
            return col == point.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }
}
