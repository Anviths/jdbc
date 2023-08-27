package org.ty.userapp.daoII;

public class Employee {

	// attributes of employee
	private int id;
	private String name;
	private String email;
	private String designation;
	private long phone;
	private String department;
	private double salary;
	private String password;

	
	// constructor for Employee
	Employee(int id, String name, String email, String designation, long phone, String department, double salary,
			String password) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.designation = designation;
		this.phone = phone;
		this.department = department;
		this.salary = salary;
		this.password = password;
	}
	Employee(){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getDesignation() {
		return designation;
	}

	public long getPhone() {
		return phone;
	}

	public String getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setPassword(String password) {
		this.password = password;
	}
//-------------------------------------------------------------
	
//----------------------------------------------------------
	public static void main(String[] args) {
	
//		Employee e1=new Employee(4, "pava", "pavan@gmail.com", "developer", 9448585957l, "it", 45000, "pavan@123");
//		Employee e2=new Employee(6, "pooja", "pooja@gmail.com", "tester", 7474858747l, "it", 20000, "testS@123");

		Employee e=new Employee();
		EmployeeDAO emp=new EmployeeDAO();
//		emp.saveEmployeeData(e2);
//		emp.updateEmployee(e);
//		emp.deleteEmployee(e);
		emp.getData(3);
	}
//-------------------------------------------------------------
}
