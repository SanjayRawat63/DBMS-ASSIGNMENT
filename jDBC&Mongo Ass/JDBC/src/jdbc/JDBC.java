/* Name:SANJAY RAWAT
Rollno:7341
*/
package jdbc;
/**
 *
 * @author Sanjay Rawat
 */
import java.sql.*;
import java.util.Scanner;
public class JDBC {

   
    public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","anish&#*1290");
      Statement st= con.createStatement();
      Scanner sc=new Scanner(System.in);
      char ch;
      int choice;
      String s;
              do
		{
			System.out.println("--------------------------------------------");
			System.out.println("1.Display all Records");
			System.out.println("2.Insert new Record");
			System.out.println("3.Update a Record");
			System.out.println("4.Delete a Record");
			System.out.println("5.Exit");
			System.out.println("--------------------------------------------");
			System.out.println("Enter your Choice");
			choice=sc.nextInt();
			switch(choice){
				case 1: 
                                       ResultSet rs=st.executeQuery("select * from STD_Record");
			               while(rs.next())				
			               System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"    "+rs.getInt(3)+"    "+rs.getString(4));
                                    
					break;
				case 2:
                                       int Rollno,Age;
                                       String name,branch;
                                       System.out.println("Enter Student Rollno: ");
				       Rollno=sc.nextInt();
				       System.out.println("Enter Student Name: ");
				       name=sc.next();
					System.out.println("Enter Student Age: ");
					Age=sc.nextInt();
					System.out.println("Enter Student Branch: ");
					branch=sc.next();
					String query = "insert into STD_Record(Rollno,Name,Age,Branch)" + " values("+Rollno+",'"+name+"',"+Age+",'"+branch+"')";
                                        PreparedStatement p=con.prepareStatement(query);
			                p.execute();
					break;
				case 3: 
                                    int rn,a;
                                    String brh,nme;
                                    System.out.println("what do you want to udpdate:");
                                       System.out.println("1.Name"+"\n"+"2.Age"+"\n"+"3.Branch");
                                       System.out.println("Enter Your choice");
                                       int h =sc.nextInt();
                                       if(h==1)
                                       { 
                                       System.out.println("Enter Student Rollno whose name to be updated: ");
				       rn=sc.nextInt();
				       System.out.println("Enter Name: ");
				       nme=sc.next();
                                       PreparedStatement ut = con.prepareStatement("update STD_Record set Name= ? where Rollno = ?");
                                       ut.setString(1,nme);
                                       ut.setInt(2, rn);
                                       ut.executeUpdate();
                                       }
                                       if(h==2)
                                       { 
                                       System.out.println("Enter Student Rollno whose Age to be updated: ");
				       rn=sc.nextInt();
				       System.out.println("Enter Age: ");
				       a=sc.nextInt();
                                       PreparedStatement ut = con.prepareStatement("update STD_Record set Age=? where Rollno=?");
                                       ut.setInt(1,a);
                                       ut.setInt(2, rn);
                                       ut.executeUpdate();
                                       }
                                       if(h==3)
                                       { 
                                       System.out.println("Enter Student Rollno whose name to be updated: ");
				       rn=sc.nextInt();
				       System.out.println("Enter Branch: ");
				       brh=sc.next();
                                       PreparedStatement ut = con.prepareStatement("update STD_Record set Branch=? where Rollno=?");
                                       ut.setString(1,brh);
                                       ut.setInt(2, rn);
                                       ut.executeUpdate();
                                       }
                                       
                                       break;
                                case 4:
                                       System.out.println("Delete by:");
                                       System.out.println("1.Rollno"+"\n"+"2.Name"+"\n"+"3.Age"+"\n"+"4.Branch");
                                       System.out.println("Enter Your choice");
                                       int c =sc.nextInt();
                                       if(c==1)
                                       {
                                       int rollno;
                                       System.out.println("Enter the Rollno:");
                                       rollno=sc.nextInt();
                                       PreparedStatement pst = con.prepareStatement("Delete from STD_Record where Rollno=?");
                                       pst.setInt(1, rollno);
                                       pst.execute();
                                       System.out.println("Record Deleted..");
                                       }
                                     
                                       if(c==2)
                                       {
                                       String nm;
                                       System.out.println("Enter the Name:");
                                       nm=sc.next();
                                       PreparedStatement pst = con.prepareStatement("Delete from STD_Record where Name=?");
                                       pst.setString(1,nm);
                                       pst.execute();
                                       System.out.println("Record Deleted..");
                                       }
                                      
                                       if(c==3)
                                       {
                                       int ag;
                                       System.out.println("Enter the Age:");
                                       ag=sc.nextInt();
                                       PreparedStatement pst = con.prepareStatement("Delete from STD_Record where Age=?");
                                       pst.setInt(1, ag);
                                       pst.execute();
                                       System.out.println("Record Deleted..");
                                       }
                                       if(c==4)
                                       {
                                       String br;
                                       System.out.println("Enter the Branch:");
                                       br=sc.next();
                                       PreparedStatement pst = con.prepareStatement("Delete from STD_Record where Branch=?");
                                       pst.setString(1, br);
                                       pst.execute();
                                       System.out.println("Record Deleted..");
                                       }  
                                       break;
                                case 5:
                                    System.exit(0);
                                       
			}
                 System.out.println("Do you want to continue??(y/n):");
                 s=sc.next();
                 ch=s.charAt(0);
		}while(ch=='y'||ch=='Y');
      
      st.close();
      con.close();
    }
    
}
