import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import com.mongodb.client.model.Filters; 
import com.mongodb.client.model.Updates; 
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import java.util.Scanner;
 class MongoDB {
	public static void main( String args[] ) {
	
		
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		
	
		MongoCredential credential = MongoCredential.createCredential("SanjayRawat", "students", "password".toCharArray());
		System.out.println("Connected to the database successfully");
	
		MongoDatabase database = mongo.getDatabase("Students");
		
		MongoCollection<Document> collection = database.getCollection("STD_Record");
		System.out.println("Collection sampleCollection selected successfully");
                
                 Scanner sc=new Scanner(System.in);
      char ch;
      int choice;
      String s;
              do
		{
			System.out.println("--------------------------------------------");
			System.out.println("1.Insert new Record");
			System.out.println("2.Display all Records");
			System.out.println("3.Update a Record");
			System.out.println("4.Delete a Record");
			System.out.println("5.Exit");
			System.out.println("--------------------------------------------");
			System.out.println("Enter your Choice");
			choice=sc.nextInt();
			switch(choice){
				case 1: 
                                   int rollno,yr;
                                    String name,branch;
                                    System.out.println("Enter Student Rollno: ");
				    rollno=sc.nextInt();
				    System.out.println("Enter Student Name: ");
				    name=sc.next();
				    System.out.println("Enter Student Branch: ");
				    branch=sc.next();
                                    System.out.println("Enter Student Year: ");
				    yr=sc.nextInt();
                                    Document document = new Document("Rollno",rollno).append("Name",name).append("Branch",branch ).append("Year",yr);
                                    collection.insertOne(document);
                                    System.out.println("Inserted Successfully.....");
                                    break;
                                case 2:
                                
                                    FindIterable<Document> iterDoc = collection.find();
                                    int i = 1;
                                    Iterator it = iterDoc.iterator();
                                    while (it.hasNext()) {
                                            System.out.println(it.next());
                                            i++;
                                    }
                                
                                    break;
                                case 3:
                                      int rn,y;
                                    String brh,nme;
                                    System.out.println("what do you want to udpdate:");
                                       System.out.println("1.Name"+"\n"+"2.Year"+"\n"+"3.Branch");
                                       System.out.println("Enter Your choice");
                                       int h =sc.nextInt();
                                       if(h==1)
                                       { 
                                       System.out.println("Enter Student Rollno whose name to be updated: ");
				       rn=sc.nextInt();
				       System.out.println("Enter Name: ");
				       nme=sc.next();
                                       collection.updateOne(Filters.eq("Rollno", rn), Updates.set("Name",nme));       
                                       System.out.println("Document update successfully...");  
                                       }
                                       if(h==2)
                                       { 
                                       System.out.println("Enter Student Rollno whose Year to be updated: ");
				       rn=sc.nextInt();
				       System.out.println("Enter Year: ");
				       y=sc.nextInt();
                                       collection.updateOne(Filters.eq("Rollno", rn), Updates.set("Year",y));       
                                       System.out.println("Document update successfully..."); 
                                       }
                                       if(h==3)
                                       { 
                                       System.out.println("Enter Student Rollno whose name to be updated: ");
				       rn=sc.nextInt();
				       System.out.println("Enter Branch: ");
				       brh=sc.next();
                                       collection.updateOne(Filters.eq("Rollno", rn), Updates.set("Name",brh));       
                                       System.out.println("Document update successfully..."); 
                                       }
                                    break;
                                case 4:
                                     System.out.println("Delete by:");
                                       System.out.println("1.Rollno"+"\n"+"2.Name"+"\n"+"3.Year"+"\n"+"4.Branch");
                                       System.out.println("Enter Your choice");
                                       int c =sc.nextInt();
                                        if(c==1)
                                       {
                                       int r;
                                       System.out.println("Enter the Rollno:");
                                       r=sc.nextInt();
                                       collection.deleteOne(Filters.eq("Rollno",r)); 
                                       System.out.println("Document deleted successfully...");
                                       }                                     
                                       if(c==2)
                                       {
                                       String nm;
                                       System.out.println("Enter the Name:");
                                       nm=sc.next();
                                       collection.deleteOne(Filters.eq("Name",nm)); 
                                       System.out.println("Document deleted successfully..."); 
                                       }                                      
                                       if(c==3)
                                       {
                                       int year;
                                       System.out.println("Enter the Year:");
                                       year=sc.nextInt();
                                       collection.deleteOne(Filters.eq("Year",year)); 
                                        System.out.println("Document deleted successfully..."); 
                                       }
                                       if(c==4)
                                       {
                                       String br;
                                       System.out.println("Enter the Branch:");
                                       br=sc.next();
                                       collection.deleteOne(Filters.eq("Branch",br)); 
                                        System.out.println("Document deleted successfully..."); 
                                       }  
                                    break;
                                case 5:
                                    System.exit(0);
                                    
                }
                        System.out.println("Do you want to continue??(y/n):");
                         s=sc.next();
                         ch=s.charAt(0);
    
}while(ch=='y'||ch=='Y');
        }
 }