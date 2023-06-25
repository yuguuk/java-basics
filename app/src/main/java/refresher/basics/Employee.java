package refresher.basics;

import java.util.Comparator;

public class Employee implements Comparable<Employee>{
    private int id;
    private String name;
    private int age;
    private long salary;
    
    public Employee(int id, String name, int age, long salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee obj) {
        return this.id - obj.id;
    }

    @Override
    public String toString() {
        return "Employee {id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "} \n";
    }

    
}

class NameComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.getName().compareTo(emp2.getName());
    }

}

class SalaryComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee emp1, Employee emp2) {
        return (int)(emp1.getSalary() - emp2.getSalary());
    }

}

class NameAgeComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee emp1, Employee emp2) {
        int flag = emp1.getName().compareTo(emp2.getName());
        if(flag == 0){
            flag = emp1.getAge() - emp2.getAge();
        }
        return flag;
    }

}