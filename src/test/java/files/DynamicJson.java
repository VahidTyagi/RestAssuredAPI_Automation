package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn_, String aisle_) {

        RestAssured.baseURI = "http://216.10.245.166";

        // ADD BOOK API
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.addBook(isbn_, aisle_))
        .when()
                .post("/Library/Addbook.php")
        .then()
                .log().all()
                .extract().response().asString();

        System.out.println("ADD Response: " + response);

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.getString("ID");

        System.out.println("Added Book ID: " + id);

        // DELETE BOOK API
        deleteBook(id);
    }

    public void deleteBook(String id) {

        String deleteResponse = given().log().all()
                .header("Content-Type", "application/json")
                .body("{\"ID\":\"" + id + "\"}")
        .when()
                .post("/Library/DeleteBook.php")
        .then()
                .log().all()
                .extract().response().asString();

        System.out.println("Delete Response: " + deleteResponse);
        System.out.println("Deleted Book ID: " + id);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {

        return new Object[][] {
			{"IFBN1001", "Java Automation20"},
//			{"IFBN1002", "Python Automation"},
//			{"IFBN1003", "C# Automation"},
//			{"IFBN1004", "JavaScript Automation"},
//			{"IFBN1005", "Ruby Automation"},
//			{"IFBN1006", "PHP Automation"},
//			{"IFBN1007", "Swift Automation"},
//			{"IFBN1008", "Kotlin Automation"},
//			{"IFBN1009", "Go Automation"},
			{"IFBN1010", "Rust Automation20"}	
        };
    }
}