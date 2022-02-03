package brain.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	private String name;
    private int age;
	private int count;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, int age, int count) {
		super();
		this.name = name;
		this.age = age;
		this.count = count;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", count=" + count + "]";
	}
	
}
