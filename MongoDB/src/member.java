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

public class member {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//mongoDB에 접속
			MongoClient mongoClient = new MongoClient("localhost",27017); //포트넘버 27017
			
			//DB연결
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
			
			//Collection 연결
			MongoCollection collection = mongoDatabase.getCollection("member");

			//Doucument 조회
			FindIterable iterDoc=collection.find();
					
			//여러개를 받기 위해서
			Iterator it = iterDoc.iterator();//Vector<E>, ArrayList<E>가 상속받는 인터페이스
			
			List <Document> list=new ArrayList<Document>();
			String userid=null;
			String pwd=null;
			String name=null;
			String phone_no=null;
			int height=0;
			int weight=0;
//			FindIterable iterDoc2=collection.find(Filters.eq("title","MongoDB"));
			while(it.hasNext()) //그다음 요소가 있으면 true 없으면 false
			{
				list.add((Document)it.next());
			}
			for(Document document:list)
			{
				System.out.println("회원아이디: "+document.get("userid"));
				System.out.println("비밀번호: "+document.get("pwd"));
				System.out.println("이름: "+document.get("name"));
				System.out.println("전화번호: "+document.get("telno"));
				System.out.println("키: "+document.get("height")+"cm");
				System.out.println("몸무게: "+document.get("weight")+"kg");
				System.out.println("----------------------------------------------");
			}
					
	
		}catch(Exception e) {
			System.out.println(e.getClass().getName()+e.getMessage());
		}

	}

}
