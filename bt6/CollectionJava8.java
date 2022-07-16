package bt6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionJava8 {
//TODO:
//research: stream methods: creation, intermediate, termination
//functional interface + lambda expression
//optional

	public void exercise1() {
		List<Student> students = getStudents();
		List<Student> studentsIdsGreater5 = getStudentWithIdGreaterThan5(students);
		System.out.println("Danh sách những học sinh có id>5: ");
		for (Student s : studentsIdsGreater5) {
			System.out.println(s);
		}
		System.out.println("--------------------");

		int sumIds = calculateSumStudentIds(students);
		System.out.println("Tổng id của các học sinh sau khi cộng: " + sumIds);
		System.out.println("--------------------");

		int multiID = calculateMultiStudentIds(students);
		System.out.println("Tổng id của các học sinh sau khi nhân: " + multiID);
		System.out.println("--------------------");

//		List<String> studentNames = getStudentNames(students);
//		System.out.println("Danh sách tên các học sinh: ");
//		for (String s : studentNames) {
//			System.out.println(s);
//		}
		
		Map<String, List<Student>> studentsByAddress = groupStudentByAddress(students);
		for (Map.Entry<String, List<Student>> entry : studentsByAddress.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue());
		}
		System.out.println("--------------------");
		printStudnetForEach(students);
	}

	public static void main(String[] args) {
		CollectionJava8 object = new CollectionJava8();
		object.exercise1();

	}

	public List<Student> getStudentWithIdGreaterThan5(List<Student> input) {
		// use input.toStream().filter(.....)
		List<Student> result = input.stream().filter(student -> student.getId() > 5).collect(Collectors.toList());
		return result;
	}

	public int calculateSumStudentIds(List<Student> input) {
		// use input.toStream().reduce(..........) or sum()
		List<Integer> listInt = new ArrayList<>();
		for (Student s : input) {
			listInt.add(s.getId());
		}
		int result = listInt.stream().reduce(0, Integer::sum);
		//int result = input.stream().map(i -> i.getId()).reduce(0, (i, j) -> i+j); //ducnv
		return result;
	}

	// Test them
	public int calculateMultiStudentIds(List<Student> input) {
		// use input.toStream().reduce(..........) or sum()
		List<Integer> listInt = new ArrayList<>();
		for (Student s : input) {
			listInt.add(s.getId());
		}
		int result = listInt.stream().reduce(1, CollectionJava8::multi);
		return result;
	}

	public static int multi(int a, int b) {
		return a * b;
	}

	public List<String> getStudentNames(List<Student> input) {
		// use input.toStream().map(.....)
		//ducnv
		List<String> names =   input.stream().map(nameStudent -> nameStudent.getName()).collect(Collectors.toList());
		return names;
	}

	public Map<String, List<Student>> groupStudentByAddress(List<Student> input) {
		// use input.toStream().collect(Collectors.groupBy...)
		Map<String, List<Student>> list = input.stream().collect(Collectors.groupingBy(Student::getAddress));
		return list;
	}

	public void printStudnetForEach(List<Student> input) {
		// print all students to console
		// use input.forEach(....)
		input.forEach(student -> System.out.println(
				"ID: " + student.getId() + ", tên: " + student.getName() + ", địa chỉ: " + student.getAddress()));

	}

	public static List<Student> getStudents() {
		Student s1 = new Student(1, "David", "London");
		Student s2 = new Student(3, "John", "Paris");
		Student s3 = new Student(5, "Alice", "Madrid");
		Student s4 = new Student(2, "Laura", "Madrid");
		Student s5 = new Student(4, "Anna", "London");
		Student s6 = new Student(10, "Jack", "Paris");
		Student s7 = new Student(12, "Robert", "Paris");
		Student s8 = new Student(9, "Timo", "Madrid");
		Student s9 = new Student(8, "Luna", "Paris");
		return Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9);
	}

}

class Student {
	private int id;
	private String name;
	private String address;

	public Student() {
	}

	public Student(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
