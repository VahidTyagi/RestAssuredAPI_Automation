package default2;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		js.getInt("courses.size()"); // use only on array
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		// print purchase amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
			System.out.println(purchaseAmount);
		
			// print title of the first course
			String titleFirstCourse = js.getString("courses[0].title");
			System.out.println(titleFirstCourse);
			
			// print all course titles and their respective prices
			for(int i=0; i<count; i++)
			{
				String courseTitle = js.getString("courses["+i+"].title");
				int coursePrice = js.getInt("courses["+i+"].price");
				System.out.println(courseTitle + " : " + coursePrice);
			}
			
			// print no of copies sold by RPA course
			for(int i=0; i<count; i++)
			{
				String courseTitle = js.getString("courses["+i+"].title");
				if(courseTitle.equalsIgnoreCase("RPA"))
				{
					int copies = js.getInt("courses["+i+"].copies");
					System.out.println("No of copies sold by RPA course: " + copies);
					break;
				}
			}
			
			// verify if sum of all course prices matches with purchase amount
			int totalAmount = 0;
			for(int i=0; i<count; i++)
			{
				int coursePrice = js.getInt("courses["+i+"].price");
				int copies = js.getInt("courses["+i+"].copies");
				totalAmount = totalAmount + (coursePrice * copies);
			}
			
			// assertion
			assert totalAmount == purchaseAmount;
			
			// print all course titles and their respective prices using for each loop
			for(int i=0; i<count; i++)
			{
				String courseTitle = js.getString("courses["+i+"].title");
				int coursePrice = js.getInt("courses["+i+"].price");
				System.out.println(courseTitle + " : " + coursePrice);
			}
			
			// print no of copies sold by RPA course using for each loop
			for(int i=0; i<count; i++)
			{
				String courseTitle = js.getString("courses["+i+"].title");
				if(courseTitle.equalsIgnoreCase("RPA"))
				{
					int copies = js.getInt("courses["+i+"].copies");
					System.out.println("No of copies sold by RPA course: " + copies);
					break;
				}
			}
			
			//verify if sum of all course prices matches with purchase amount using for each loop
			int totalAmount1 = 0;
			for(int i=0; i<count; i++)
			{
				int coursePrice = js.getInt("courses["+i+"].price");
				int copies = js.getInt("courses["+i+"].copies");
				totalAmount1 = totalAmount1 + (coursePrice * copies);
				
			}
			
			System.out.println("Total amount: " + totalAmount1);
			System.out.println("Purchase amount: " + purchaseAmount);
			assert totalAmount1 == purchaseAmount;
			
		
	
		
	}	

}
