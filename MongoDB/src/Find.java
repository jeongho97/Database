import java.util.Iterator;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Find {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//mongoDB�� ����
			MongoClient mongoClient = new MongoClient("localhost",27017); //��Ʈ�ѹ� 27017
			
			//DB����
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
			
			//Collection ����
			MongoCollection collection = mongoDatabase.getCollection("test");
			
			//Doucument ��ȸ
			FindIterable iterDoc=collection.find();
			
			//�������� �ޱ� ���ؼ�
			Iterator it = iterDoc.iterator();//Vector<E>, ArrayList<E>�� ��ӹ޴� �������̽�
			
			while(it.hasNext()) //�״��� ��Ұ� ������ true ������ false
			{
				System.out.println(it.next());
			}
		}catch(Exception e) {
			System.out.println(e.getClass().getName()+e.getMessage());
		}

	}

}
