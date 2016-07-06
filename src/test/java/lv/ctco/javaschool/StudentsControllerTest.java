package lv.ctco.javaschool;


import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentsControllerTest {
//Get Test
    public void getTestOK(int studentId) {
        given().contentType("application/json").
                when().get("students/" + studentId).
                then().statusCode(200);
    }

    public void getTestNotFound(int studentId) {
        given().contentType("application/json").
                when().get("students/" + studentId).
                then().statusCode(404);
    }
    public void getTestIncorrectID(String studentId) {
        given().contentType("application/json").
                when().get("students/" + studentId).
                then().statusCode(400);
    }


    @Test
            public void runGetTests(){
        getTestOK(1);
        getTestNotFound(3);
        getTestIncorrectID("aasdf");
    }

    //Post test
    public void postTest(String studentId) {
        given().contentType("application/json").
                when().get("students/" + studentId).
                then().statusCode(400);
    }

    @Test
    public void runPostTest(){

    }
}