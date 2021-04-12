/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

/**
 *
 * @author Sanjay Rawat
 */
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import com.mongodb.client.model.Filters; 
import com.mongodb.client.model.Updates; 
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Mongo {
    public static void main( String args[] ) throws IOException {
	
		
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		
	
		MongoCredential credential = MongoCredential.createCredential("SanjayRawat", "students", "password".toCharArray());
		System.out.println("Connected to the database successfully");	
                 Scanner sc=new Scanner(System.in);
      char ch;
      int choice,a;
      String s;
              do
		{   System.out.println("--------------------------------------------");
			System.out.println("1.For Text");
			System.out.println("2.For Image");
			System.out.println("3.For Audio");
			System.out.println("4.For Video");
                        System.out.println("5.Exit.");
			System.out.println("--------------------------------------------");
			System.out.println("Enter your Choice");
			a=sc.nextInt();
			switch(a){
                    case 1:
                        System.out.println("--------------------------------------------");
			System.out.println("1.Insert new Record");
			System.out.println("2.Display all Records");
			System.out.println("3.Update a Record");
			System.out.println("4.Delete a Record");
			System.out.println("--------------------------------------------");
			System.out.println("Enter your Choice");
			choice=sc.nextInt();
                        MongoDatabase database = mongo.getDatabase("Students");
                        MongoCollection collection = database.getCollection("STD_Record");
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
                                       System.out.println("Enter Student Rollno whose Branch to be updated: ");
				       rn=sc.nextInt();
				       System.out.println("Enter Branch: ");
				       brh=sc.next();
                                       collection.updateOne(Filters.eq("Rollno", rn), Updates.set("Branch",brh));       
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
                         }
                         break;
                    case 2:
                      int flag;
                        do{
                       System.out.println("--------------------------------------------");
			System.out.println("1.Insert Image");
		        System.out.println("2.Display Image details");
                        System.out.println("3.Delete Image");
                        System.out.println("Enter your choice: ");
                        int b=sc.nextInt();
                        DB db = mongo.getDB("Students");
			
			GridFS gfsPhoto = new GridFS(db, "photo");
                        switch(b){
                       case 1:         
                        System.out.println("Enter the Image name: ");
                        String str=sc.next();
                        System.out.println("Enter the Image format: ");
                        String format=sc.next();
                        File imageFile = new File("C:\\"+str+format);
			GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
			gfsFile.setFilename(str);
			gfsFile.save();
                          break;                               
                      case 2:
			DBCursor cursor = gfsPhoto.getFileList();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
		
                        }
                        break;
                      case 3:
                          System.out.println("Enter the Image name you want to delete: ");
                        String st=sc.next();
			gfsPhoto.remove(gfsPhoto.findOne(st));

			System.out.println("Done");
                        break;
                }
                        
                        System.out.println("If you want to continue press 1 else 0:");
                        flag=sc.nextInt();
                        }while(flag==1); 
                        break;
                    case 3:
                         int fg;
                        do{
                       System.out.println("--------------------------------------------");
			System.out.println("1.Insert Audio");
		        System.out.println("2.Display  Audio details");
                        System.out.println("3.Delete Audio");
                        System.out.println("Enter your choice: ");
                        int b=sc.nextInt();
                        DB db = mongo.getDB("Students");
			
			GridFS gfsAudio = new GridFS(db, "audio");
                        switch(b){
                       case 1:         
                        System.out.println("Enter the Audio name : ");
                        String str=sc.next();
                        System.out.println("Enter the Audio format: ");
                        String format=sc.next();
                        File AudioFile = new File("C:\\"+str+format);
			GridFSInputFile gfsFile = gfsAudio.createFile(AudioFile);
			gfsFile.setFilename(str);
			gfsFile.save();
                          break;                               
                      case 2:
			DBCursor cursor = gfsAudio.getFileList();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
		
                        }
                        break;
                      case 3:
                          System.out.println("Enter the Audio name you want to delete: ");
                        String st=sc.next();
			gfsAudio.remove(gfsAudio.findOne(st));

			System.out.println("Done");
                        break;
                }
                        
                        System.out.println("If you want to continue press 1 else 0:");
                        fg=sc.nextInt();
                        }while(fg==1); 
                        break;
                    case 4:
                        int f;
                        do{
                       System.out.println("--------------------------------------------");
			System.out.println("1.Insert Video");
		        System.out.println("2.Display  Video details");
                        System.out.println("3.Delete Video");
                        System.out.println("Enter your choice: ");
                        int b=sc.nextInt();
			DB db = mongo.getDB("Students");
			
			GridFS gfsVideo = new GridFS(db, "Video");
                        
                        switch(b){
                       case 1:         
                        System.out.println("Enter the Video name : ");
                        String str=sc.next();
                        System.out.println("Enter the Video format: ");
                        String format=sc.next();
                        File VideoFile = new File("C:\\"+str+format);
			GridFSInputFile gfsFile = gfsVideo.createFile(VideoFile);
			gfsFile.setFilename(str);
			gfsFile.save();
                          break;                               
                      case 2:
			DBCursor cursor = gfsVideo.getFileList();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
		
                        }
                        break;
                      case 3:
                          System.out.println("Enter the Video name you want to delete : ");
                        String st=sc.next();
			gfsVideo.remove(gfsVideo.findOne(st));

			System.out.println("Done");
                        break;
                }
                        
                        System.out.println("If you want to continue press 1 else 0:");
                        fg=sc.nextInt();
                        }while(fg==1); 
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
