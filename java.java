import java.io.*;


public class Student {
    private final String FILE_NAME="studentdata.txt";
    public String studentNo;
    public String studentName;
    private boolean acceptStudentInformation(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the student no:");
            studentNo=br.readLine();
            if (studentNo.isEmpty()){
                System.out.println("please enter student no");
                return false;
            }
            System.out.println("enter the student name : ");
            studentName=br.readLine();
            if (studentName.isEmpty()){
                System.out.println("please enter student name:");
                return false;
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean saveStudentInformation(){
        if (!acceptStudentInformation()){
            return false;
        }
        try{
            PrintWriter pw=new PrintWriter(new FileWriter(new File(FILE_NAME),true));
            pw.println(studentNo+","+studentName);
            pw.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean printStudentInformation(){
        try{
            BufferedReader br=new BufferedReader(new FileReader(FILE_NAME));
            System.out.println(String.format("%-15s %-40s", "student NO", "student Name"));
            String dataLine=br.readLine();
            while (dataLine!=null){
                String[] data=dataLine.split(",");
                System.out.println(String.format("%-15s %-40s",data[0],data[1]));
                dataLine=br.readLine();
            }
            br.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
import java.io.*;

public class StudentMenu {
    public static void main(String args[]){
        try{
            BufferedReader br=new BufferedReader(new  InputStreamReader(System.in));
            Student s=new Student();
            String select="";
            do{
                showMenu();
                select=br.readLine();
                switch (select){
                    case "1":
                        if (s.saveStudentInformation())
                            System.out.println("Save successful...");
                        else
                            System.out.println("Error occured while saving student information..");
                        break;
                    case "2":
                        if (s.printStudentInformation())
                            System.out.println("printing successful..");
                        else
                            System.out.println("Error occured while printint studetn information..");
                        break;
                    case "3":
                        return;
                }
            } while (!select.equals("3"));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static void showMenu(){
        System.out.println("1: Add student");
        System.out.println("2: Print all student");
        System.out.println("3: Exit");
        System.out.println("Select Number (1 or 2 or 3):");
    }
}