package default2;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumValidation()
	{
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		int totalAmount = 0;
		for(int i=0; i<count; i++)
		{
			int coursePrice = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			totalAmount = totalAmount + (coursePrice * copies);
		}
		
		System.out.println(totalAmount);
		
		assert totalAmount == purchaseAmount;
		
	}	

}
