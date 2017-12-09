

import java.text.*;
import java.util.*;
import java.io.*;
// @author sandeep
public   class Employee  {
	
private String loginname;
private double salary;
private String name;
private String joiningdate;
private  int employeeID;
 static int nextID=0;
public Employee(String loginname,double salary,String name) {
	Date dateobj = new Date();
    DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
    String joiningdate = f.format(dateobj);
    employee(nextID, name, loginname, salary, joiningdate);  	
}
Employee(int employeeID, String name, String loginName, double salary, String joiningDate)  //used to store from file
{
   employee(employeeID, name, loginName, salary, joiningDate);
}

private void employee(int employeeID, String name, String loginName, double salary, String joiningdate)
{
   this.name = name;
   this.loginname = loginName;
   this.salary = salary;
   this.joiningdate = joiningdate;
   this.employeeID = empId();
}
//-------set function for salary--------
public void setSalary(double changeinsalary) {
	salary = changeinsalary;
}
public void setName(String changeinname) {
	name = changeinname;
}
public int empId(){
	employeeID = nextID;
	Employee.nextID++;
	return employeeID;
}

public String getLoginName()
{
   return loginname;
}
public String getName() {
	return name;
}
public double getSalary() {
	return salary;
}
public int getEmployeeID()
{
	return employeeID;
}
public String toString() {   
	return String.format( "%05d\t%s\t%f\t%s\t%s", employeeID,loginname,salary,joiningdate,name );
}
//public  abstract double getPay();


}

