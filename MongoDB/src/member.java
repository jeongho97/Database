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
			//mongoDB�� ����
			MongoClient mongoClient = new MongoClient("localhost",27017); //��Ʈ�ѹ� 27017
			
			//DB����
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
			
			//Collection ����
			MongoCollection collection = mongoDatabase.getCollection("member");

			//Doucument ��ȸ
			FindIterable iterDoc=collection.find();
					
			//�������� �ޱ� ���ؼ�
			Iterator it = iterDoc.iterator();//Vector<E>, ArrayList<E>�� ��ӹ޴� �������̽�
			
			List <Document> list=new ArrayList<Document>();
			String userid=null;
			String pwd=null;
			String name=null;
			String phone_no=null;
			int height=0;
			int weight=0;
//			FindIterable iterDoc2=collection.find(Filters.eq("title","MongoDB"));
			while(it.hasNext()) //�״��� ��Ұ� ������ true ������ false
			{
				list.add((Document)it.next());
			}
			for(Document document:list)
			{
				System.out.println("ȸ�����̵�: "+document.get("userid"));
				System.out.println("��й�ȣ: "+document.get("pwd"));
				System.out.println("�̸�: "+document.get("name"));
				System.out.println("��ȭ��ȣ: "+document.get("telno"));
				System.out.println("Ű: "+document.get("height")+"cm");
				System.out.println("������: "+document.get("weight")+"kg");
				System.out.println("----------------------------------------------");
			}
					
	
		}catch(Exception e) {
			System.out.println(e.getClass().getName()+e.getMessage());
		}

	}

}
