import java.io.*;
import java.text.*;
import java.util.*;
import java.lang.*;

// @author sandeep

public class Payroll {
	 
	int loginstatus=0;
	int currentempid;
	    ArrayList<Employee> emp  =  new ArrayList<Employee>(); 
	    private File file;
	    Scanner ksc = new Scanner(System.in);	    
	    private static String menu = "Payroll Menu\n\t1. Log In "+ "\n\t2. Enter employees\n\t3. List Employees\n"+"\n\t4. Change employee data\n\t5. Terminate an employee"+ "\n\t6. Pay employees \n\t0. Exit system";  
	    public Payroll()  {
	    	try {
	    	file = new File("employee.txt");
	    	/* FileInputStream fis = new FileInputStream(file);        // FileNotFoundException 
	        ObjectInputStream ois = new ObjectInputStream(fis);            // IOException
	        while (ois.readObject() != null) {
	            emp.add((Employee) ois.readObject()); 
	        } */
	     Scanner fsc = new Scanner(file);
	    	 while (fsc.hasNext())
	         {        	 
        	int employeeId = fsc.nextInt();
        	String loginname = fsc.next();        	
        	double salary = fsc.nextDouble();        	
        	String joiningDate = fsc.next();        	
        	String name = fsc.next();        	
           Employee e = new Employee(employeeId, name, loginname, salary, joiningDate);            
             emp.add(e);   //soring  data to arraylist */
            
	         
	         }
	    	}
	    	catch(FileNotFoundException e) {
	    		empdata("boss");
	    	}
	    	/*catch(IOException e) {
	    		
	    	} */
	    	}
	 
	    public static void main(String args[]) {
          
	    	System.out.println("program name:files, student name: sandeep");  	
	    	
	           Payroll p = new Payroll();	           
	           p.doMenu();
	        
	    	 }
	    public void dologin() {	    	
	    	
	    		int flag=0;    //setting a flag to zero before loging in    				
	    		int id;
	    		 String currentuser;
	    	        int currentid=1111;
	    			 ksc = new Scanner( System.in );
	    	        System.out.println("Enter your Login Name:");
	    	        String logname=ksc.nextLine();
	    	       String log;    	        
	    	     //  Employee i = emp.get(0);
	    	      // if (i.equals(0))
	    	    	   currentid=0;
	    	       
	    	        for (Employee k : emp)
	    	         {
	    	        	 log= k.getLoginName();
	    	        	id = k.getEmployeeID();	    	        	 
	    	        	//System.out.println(log);	    	        	 
	    	            if (log.equalsIgnoreCase(logname))
	    	            {
	    	               currentuser=log;
	    	               currentid=id;
	    	              System.out.println(log+"\n"+id);
	    	            	 flag=1;      // setting value to 1  if login success
	    	            	
	    	            }
	    	         }
	    	            if(flag==1) {
	    	            	System.out.println("login success");
	    	            }
	    	            else
	    	            	System.out.println("login failed");
	    	            if( currentid==0)
	    	            	loginstatus=1;
	    	            else 
	    	            	currentempid=currentid;
	    		   }
	 
	    public void empdata(String message) {	
	    	int checklname=0;
	    	String name,loginname;
	    	double salary;	
	    	 ksc = new Scanner(System.in);
	    	System.out.println("enter first name and last name by space");
	    		name = ksc.nextLine();
	    		System.out.println("enter login name(no spaces or symbols)");
	    		loginname = ksc.next();
	    		System.out.println("enter salary");
	    		salary = ksc.nextDouble();  
	    		//ksc.close();
	    		System.out.println("enter employee type S/H");
	    		String type = ksc.next();	    		
	    		String n = name.replaceAll("[\\W]", "");
	    		
	    		for(Employee k: emp) {
	    			if(k.getLoginName().equalsIgnoreCase(loginname)) {
	    				System.out.println("loginname already exists");
	    				checklname=1;
	    				System.out.println(checklname);
	    			}
	    		}
	    		
	    		
	    			
	    	  
	    		 
	    		if(type.equalsIgnoreCase("S")&&checklname==0) {
		    		Salaried sl = new Salaried(loginname,salary,n);
		    		emp.add(sl); 
		    		System.out.println(message);
		    		} 
	    		else if(type.equalsIgnoreCase("H")&&checklname==0) {
		    		Hourly hr = new Hourly(loginname,salary,n);
		    		emp.add(hr); 
		    		System.out.println(message);
		    		}     		
	    		
	    		else
	    			System.out.println("loginname already exists not stored into arraylist");	 	   
	    	}  	
	
	   public void doMenu() {
	    	
	    		try {	    		
	    		while(true) {                       // infinite loop
	    			System.out.println(menu);	    		
	    		int c =ksc.nextInt();
	    		int boss;
	    	switch(c) {
	    	case 0: 
	    		
	    		
	    		//System.exit(0);
	    		break;
	    	case 1:  dologin();
	    	
	    			break;
	    	case 2: 
	    	         	
    		if(loginstatus==1)
	    		empdata("emp added successfully");
    		
    		else 
    			System.out.println("please login to add employee ");
    		
	    			break;
	    	case 3: listemployees();
	    			break;
	    	case 4: change();
	    			break;
	    	case 5: 
	    		if(loginstatus==1)
	    		terminate();
	    		else 
	    			System.out.println("please login to terminate employee ");
	    			break;
	    	case 6: 
	    		if(loginstatus==1)
	    		pay();
	    		else 
	    			System.out.println("please login to pay employee ");
	    			break;
	    	default: System.out.println("wrong input");
	    				break;
	    				
	    	}
	    	if(c==0)
	    		break;
	    	
	    } 
	    		
	   }
	   finally {
		   
		   try {
   				if(!file.exists())
	    	      file.createNewFile();
   			/*	FileOutputStream fos = new FileOutputStream(file);           // FileNotFoundException 
   		        ObjectOutputStream oos  = new ObjectOutputStream(fos);
   		     oos.writeObject(emp);   */
	    		FileWriter fw = new FileWriter(file);
 	    	
 	    	PrintWriter pw = new PrintWriter(fw);
 	    	
 	    	for(Employee k: emp)
 	    	{
	    	    	//System.out.println("writing to file ");

 	    	pw.println(k);    // writing the data to the file
 	    	}
 	    	
 	    	fw.close();
 	    	pw.close(); 
	    		}	    		
	    		catch (IOException ioe)
	    	      {
	    	         System.out.println("Exception occurred:");
	    	         ioe.printStackTrace();
	    	      }
 	    	System.out.println("inserted to file ");
	    }
   }
	   public void listemployees()
   	{
		   for(Employee k: emp)
		   System.out.println(k);
   	/*	try
   		{
   			FileReader fr = new FileReader(file);
   		    BufferedReader br = new BufferedReader(fr);
   		    String line;
   		    while((line = br.readLine())!= null){
   		       System.out.println(line);
   		    }
   		    br.close();
   		    fr.close();
   		}
   		catch(IOException ioe)
   		{
   			System.out.println("Exception occurred:");
   	    	ioe.printStackTrace();		
   		}
   		*/
   	}
	   public void change() {
		   System.out.println("enter employee id you want to change");
		   Scanner sc = new Scanner(System.in);
		   int cid;
		   int flag=0;
		   int uid = sc.nextInt();
		  
		 
		   for (Employee k : emp)
	         {
	        	
	        	cid = k.getEmployeeID();	    	        	 
	        	        	 
	            if (cid==uid)
	            {              
	            	System.out.println("enter 1 to change salary \n enter 2 to change name");
	            	int option = sc.nextInt();
	            	if(option ==1) {
	            	 System.out.println("enter the salary amount to change");
	            	 double originalsal = k.getSalary();
	      		   double csalary= sc.nextDouble();
	      		 k.setSalary(csalary);
	      		System.out.println("employee salary "+originalsal+" changed to"+csalary);
	            	}
	            	else if(option==2) {
	      		 System.out.println("enter the name to change");
	      		 String originalname = k.getName();
	      		 String cname = sc.next();
	      		k.setName(cname);
	      		System.out.println("employee name "+originalname+" is changed to"+cname);
	            	}
	            	else
	            	System.out.println("wrong input");       	
	            	
	            	
	            }
	         }
		  
	   }
	   public void terminate() {
		   System.out.println("enter id of employee you want to terminate");
		   Scanner sc = new Scanner(System.in);
		   int cid;
		   int flag=0;
		   int tid= sc.nextInt();
		   for (Employee k : emp)
	         {
	        	
	        	cid = k.getEmployeeID();	    	        	 
	        	    	        	 
	            if (cid==tid)
	            {               
	             
	            	flag=1;
	            }
	         }
		   if(flag==1) {
		   emp.remove(tid);
		   System.out.println("employee teminated successfully");
		   }
		   else
			   System.out.println("employee id not exist");
	   }
	   public void pay() {
		   
		   System.out.println("this function not implemented yet");
	   }
 }

