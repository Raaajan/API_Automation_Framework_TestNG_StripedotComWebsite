package Utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.util.Properties;

public class Base {

    public static RequestSpecification reqspec;
    public static Properties prop;
   @BeforeSuite
    public void SetUp(){
       String propertiesFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\Config.properties";
       prop = new Properties();
       FileInputStream propfile;
       try {
            propfile= new FileInputStream(propertiesFilePath);
            prop.load(propfile);
       }catch (Exception e){
           System.out.println(e.getStackTrace());
       }

        RestAssured.baseURI = prop.getProperty("BaseURI");
   }

   @BeforeMethod
   public void setupReqSpec() {
       reqspec = RestAssured.given().auth().basic(prop.getProperty("SecretKey"), "");

   }

    @AfterSuite
    public void tearDown() {

    }
}
