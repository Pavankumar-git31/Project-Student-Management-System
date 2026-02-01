package StudentManagement;
import java.io.*;
import java.util.*;
public class StudentManagementSystem {
	class Student
	{
		String name;
		int rollNumber;
		String Branch;
		double percentage;
		Student(String name,int rollNumber,String Branch,double percentage )
		{
			this.rollNumber=rollNumber;
			this.name=name;
			this.Branch=Branch;
			this.percentage=percentage;
		}
	}
	ArrayList<Student>StudentList=new ArrayList<>();
	Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		StudentManagementSystem obj=new StudentManagementSystem();
		obj.loadFile();
		System.out.println("Enter the userr id ");
		String user=sc.next();
		System.out.println("Enter the password");
		String s=sc.next();
		if(user.charAt(user.length()-1)=='e'&&s.equals("e123"))
		{
			obj.unlockEmployee();
		}
		else if(user.charAt(user.length()-1)=='m'&&s.equals("m123"))
		{
			obj.unlockManagement();
		}
		else if(user.charAt(user.length()-1)=='p'&&s.equals("p123"))
		{
			obj.unlockPrincipal();
		}
		else
		{
			System.out.println("UnAuthorized User ");
		}
	}
	public   void unlockEmployee()
	{//only see the Student details
		System.out.println("Choose the Option");
		System.out.println("1.See Student Details");
		System.out.println("2.Exit");
		int option=sc.nextInt();
		switch(option)
		{
		case 1:veiwStudent();
		break;
		case 2: break;
		
		}
		
	}
	public void unlockManagement() throws IOException
	{//see& update and add the student details
		System.out.println("Choose the Option");
		System.out.println("1.See Student Details");
		System.out.println("2.Add Student");
		System.out.println("3.Update Student");
		System.out.println("4.exit");
		int option=sc.nextInt();
		switch(option)
		{
		case 1:veiwStudent();
		break;
		case 2:addStudent();
		break;
		case 3:updateStudent();
		break;
		case 4:break;
		}
		
	}
	public void unlockPrincipal() throws IOException
	{//see, add,update & delete the Student details
		System.out.println("Choose the Option");
		System.out.println("1.See Student Details");
		System.out.println("2.Add Student");
		System.out.println("3.Update Student");
		System.out.println("4.Remove Student");
		System.out.println("5.Exit");
		int option=sc.nextInt();
		switch(option)
		{
		case 1:veiwStudent();
		break;
		case 2:addStudent();
		break;
		case 3:updateStudent();
		break;
		case 4:deleteStudent();
		break;
		case 5:break;
		}
		
	}
	
	
	
	
	public void veiwStudent()
	{
		boolean j=false;
		System.out.println("Enter the Student rollno");
		int roll=sc.nextInt();
		if(StudentList.isEmpty())
		{
			System.out.println("No Students Data");
		}
		else
		{
			for(Student x:StudentList)
			{
				if(x.rollNumber==roll)
				{
					System.out.println("Student Name: "+x.name);
					System.out.println("Student RollNumber: "+x.rollNumber);
					System.out.println("Student Branch: "+x.Branch);
					System.out.println("Student Percentage: "+x.percentage);
					j=true;
				}
			}
			if(!j)
			{
				System.out.println("No Results Found");
			}
		}
	}
	public void updateStudent() throws IOException
	{
		boolean j=false;
		System.out.println("Enter the Student roll Number you want to update");
		int roll=sc.nextInt();
		if(StudentList.isEmpty())
		{
			System.out.println("No Students Data");
		}
		else
		{
			for(Student x:StudentList)
			{
				if(x.rollNumber==roll)
				{
					j=true;
					System.out.println("choose the option what you want to update ");
					System.out.println("1.RollNumber:");
					System.out.println("2.percentage");
					System.out.println("3.Branch");
					System.out.println("4.Name");
					int option=sc.nextInt();
					switch(option)
					{
					case 1:
					{
						System.out.println("Enter the new RollNumber :");
						x.rollNumber=sc.nextInt();
						System.out.println("Updated Successfully!");
						break;
					}
					case 2:{
						System.out.println("Enter the new Percentage :");
						x.percentage=sc.nextDouble();
						System.out.println("Updated Successfully!");
						break;
					}
					case 3:{
						System.out.println("Enter the new Branch :");
						x.Branch=sc.next();
						System.out.println("Updated Successfully!");
						break;
					}
					case 4:{
						System.out.println("Enter the new Name :");
						x.name=sc.next();
						System.out.println("Updated Successfully!");
						break;
					}
					}	
				}
			}
			if(!j)
			{
				System.out.println("Student not found");
			}
			saveAllToFile();
		
	}

	}
	
	public  void addStudent() throws IOException
	{
		System.out.println("Enter the Student Name");
		String name=sc.next();
		System.out.println("Enter the Student roll Number");
		int roll=sc.nextInt();
		System.out.println("Enter the Student percentage");
		double per=sc.nextDouble();
		System.out.println("Enter the Student Branch");
		String branch=sc.next();
		StudentList.add(new Student(name,roll,branch,per));
		saveAllToFile();
		System.out.println("Student Added Successfully");
		
	}
	public void deleteStudent() throws IOException
	{
		boolean j=false;
		System.out.println("Enter the Student Roll Number you Want to delete");
		int r=sc.nextInt();
		Iterator<Student> it = StudentList.iterator();
		while (it.hasNext()) {
		    Student s = it.next();
		    if (s.rollNumber == r) {
		    	j=true;
		        it.remove(); 
		    }
		    System.out.println("Student Removed SuccesFully!");
		}
		
		if(!j)
		{
			System.out.println("Student Data Not Found!");
		}
		saveAllToFile();
}
	public void loadFile() throws IOException
	{
		File f=new File("./studentData.txt");
		if(!f.exists()) return;
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		String line;
		String name="";
		int roll=0;
		double percentage=0;
		String branch="";
		int count=0;
		while((line=br.readLine())!=null)
		{
			if(line.trim().isEmpty()) continue;
			if(line.startsWith("Name: "))
			{
				name=line.substring(6).trim();
				count++;
			}
			else if(line.startsWith("RollNumber: "))
			{
				roll=Integer.parseInt(line.substring(12).trim());
				count++;
			}
			else if(line.startsWith("Branch: "))
			{
				branch=line.substring(8).trim();
				count++;
			}
			else
			{
				percentage=Double.parseDouble(line.substring(11).trim());
				count++;
			}
			if(count==4)
			{
				StudentList.add(new Student(name,roll,branch,percentage));
				count=0;
			}
		}
		br.close();
		fr.close();
	}
	public void saveAllToFile() throws IOException {
	    File f = new File("./studentData.txt");
	    FileWriter fr = new FileWriter(f, false); // overwrite
	    BufferedWriter br = new BufferedWriter(fr);

	    for (Student s : StudentList) {
	        br.write("Name: " + s.name);
	        br.newLine();
	        br.write("RollNumber: " + s.rollNumber);
	        br.newLine();
	        br.write("Branch: " + s.Branch);
	        br.newLine();
	        br.write("Percentage: " + s.percentage);
	        br.newLine();
	        br.newLine();
	    }
	    br.close();
	}

	
	
}
