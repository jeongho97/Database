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
			//mongoDB�� ����
			MongoClient mongoClient = new MongoClient("localhost",27017); //��Ʈ�ѹ� 27017
			
			//DB����
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
			
			//Collection ����
			MongoCollection collection = mongoDatabase.getCollection("test");
			boolean start=true;
			while(start) {
				System.out.println("select or insert or update or delete or input_insert or break : ");
				Scanner s = new Scanner(System.in);
				String str=s.next();
				
				switch(str) //MongoDB CRUD
				{
				case "select":
				//Doucument ��ȸ
					FindIterable iterDoc=collection.find();
					
					//�������� �ޱ� ���ؼ�
					Iterator it = iterDoc.iterator();//Vector<E>, ArrayList<E>�� ��ӹ޴� �������̽�
					
					while(it.hasNext()) //�״��� ��Ұ� ������ true ������ false
					{
						System.out.println(it.next());
					}
					break;
				case "insert":
					//BasicDbObject Ŭ���� �̿��Ͽ� ����
					Document docu = new Document("title","MongoDB1").append("author", "Lee").append("price", 300);
					Document docu2 = new Document("title","MongoDB2").append("author","jeong").append("price",400);
					Document docu3 = new Document("title","MongoDB3").append("author","hi").append("price",500);
					
					List<Document> list =new ArrayList<Document>();
					list.add(docu);
					list.add(docu2);
					list.add(docu3);
					//collection.insertOne(docu); //�ϳ� insert�Ҷ�
					collection.insertMany(list); //������ insert�Ҷ�
					System.out.println("���ԵǾ����ϴ�.");
					break;
				case "update":
					//collection.updateOne(Filters.eq("title","MongoDB3"),Updates.set("author","ho"));
					UpdateResult updateResult=collection.updateMany(Filters.eq("author", "Lee"),Updates.set("price", 1000)); //������ ����
					System.out.println(updateResult.getModifiedCount());
					System.out.println("������Ʈ �Ǿ����ϴ�.");
					break;
				case "delete":
					//Document rm = new Document("smith","Lee");
					//collection.deleteOne(rm); //�ϳ� �����ϱ�
					DeleteResult deleteResult=collection.deleteMany(Filters.eq("author", "Lee"));//�ش� filter�� �ۼ��� ���ǿ� �´� document �����ϱ�
					System.out.println(deleteResult.getDeletedCount());
					System.out.println("�����Ǿ����ϴ�");
					break;
				case "input_insert":
					System.out.println("title author price�� �Է��Ͻÿ�: ");
					Scanner sn = new Scanner(System.in);
					String title=sn.next();
					String author=sn.next();
					int price=sn.nextInt();
					Document t_docu=new Document("title",title).append("author",author).append("price", price); //�̷��� �ϴ°� RDBS �������� ���� ����ΰ�?
					collection.insertOne(t_docu);
					System.out.println("���ԵǾ����ϴ�.");
					break;
				default :
					System.out.println("����Ǿ����ϴ�");
					start=false;
					break;
					
				}
			}
		}catch(Exception e) {
			System.out.println(e.getClass().getName()+e.getMessage());
		}

	}

}
