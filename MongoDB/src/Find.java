import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class Find {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//mongoDB에 접속
			MongoClient mongoClient = new MongoClient("localhost",27017); //포트넘버 27017
			
			//DB연결
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
			
			//Collection 연결
			MongoCollection collection = mongoDatabase.getCollection("test");
			boolean start=true;
			while(start) {
				System.out.println("select or insert or update or delete or input_insert or break : ");
				Scanner s = new Scanner(System.in);
				String str=s.next();
				
				switch(str) //MongoDB CRUD
				{
				case "select":
				//Doucument 조회
					FindIterable iterDoc=collection.find();
					
					//여러개를 받기 위해서
					Iterator it = iterDoc.iterator();//Vector<E>, ArrayList<E>가 상속받는 인터페이스
					
					while(it.hasNext()) //그다음 요소가 있으면 true 없으면 false
					{
						System.out.println(it.next());
					}
					break;
				case "insert":
					//BasicDbObject 클래스 이용하여 삽입
					Document docu = new Document("title","MongoDB1").append("author", "Lee").append("price", 300);
					Document docu2 = new Document("title","MongoDB2").append("author","jeong").append("price",400);
					Document docu3 = new Document("title","MongoDB3").append("author","hi").append("price",500);
					
					List<Document> list =new ArrayList<Document>();
					list.add(docu);
					list.add(docu2);
					list.add(docu3);
					//collection.insertOne(docu); //하나 insert할때
					collection.insertMany(list); //여러개 insert할때
					System.out.println("삽입되었습니다.");
					break;
				case "update":
					//collection.updateOne(Filters.eq("title","MongoDB3"),Updates.set("author","ho"));
					UpdateResult updateResult=collection.updateMany(Filters.eq("author", "Lee"),Updates.set("price", 1000)); //여러개 갱신
					System.out.println(updateResult.getModifiedCount());
					System.out.println("업데이트 되었습니다.");
					break;
				case "delete":
					//Document rm = new Document("smith","Lee");
					//collection.deleteOne(rm); //하나 삭제하기
					DeleteResult deleteResult=collection.deleteMany(Filters.eq("author", "Lee"));//해당 filter에 작성한 조건에 맞는 document 삭제하기
					System.out.println(deleteResult.getDeletedCount());
					System.out.println("삭제되었습니다");
					break;
				case "input_insert":
					System.out.println("title author price를 입력하시오: ");
					Scanner sn = new Scanner(System.in);
					String title=sn.next();
					String author=sn.next();
					int price=sn.nextInt();
					Document t_docu=new Document("title",title).append("author",author).append("price", price); //이렇게 하는게 RDBS 동적쿼리 같은 방법인가?
					collection.insertOne(t_docu);
					System.out.println("삽입되었습니다.");
					break;
				default :
					System.out.println("종료되었습니다");
					start=false;
					break;
					
				}
			}
		}catch(Exception e) {
			System.out.println(e.getClass().getName()+e.getMessage());
		}

	}

}
