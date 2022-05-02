import java.util.Iterator;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
			
			//Doucument 조회
			FindIterable iterDoc=collection.find();
			
			//여러개를 받기 위해서
			Iterator it = iterDoc.iterator();//Vector<E>, ArrayList<E>가 상속받는 인터페이스
			
			while(it.hasNext()) //그다음 요소가 있으면 true 없으면 false
			{
				System.out.println(it.next());
			}
		}catch(Exception e) {
			System.out.println(e.getClass().getName()+e.getMessage());
		}

	}

}
