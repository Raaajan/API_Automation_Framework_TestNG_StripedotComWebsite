package Testcases;

import Reporting.ExtentListeners;
import Utilities.Base;
import Utilities.Common;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(ExtentListeners.class)
public class ProductApiTest extends Base {

    public static Object product_id;

    @Test
    public void getAllProductDetailsWithValidEndPoint(){
        Response response = Common.hitURrlAndGetResponse("GET","Allproducts","");
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath get_json = Common.getResponseAsJsonPath(response);

        int total_Products = get_json.getList("data").size();
        System.out.println("total_Products = "+total_Products);
        for(int i=0; i<total_Products;i++){
            System.out.println(get_json.getString("data["+i+"].id"));
        }
    }


    @Test (priority = 1)
    public void createProduct(){

        Response response  = Common.hitURrlAndGetResponse("POST","ProductDetails","BodyAsFormParam");
        JsonPath json = Common.getResponseAsJsonPath(response);
        product_id = json.get("id");
        System.out.println("product_id = "+product_id);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(json.get("description"),"created though hashmap");

    }

    @Test (priority = 2)
    public void getNewlyCreatedProductDetails(){
        Response response = Common.hitURrlAndGetResponse("GET","SpecificProduct","");
        JsonPath json = Common.getResponseAsJsonPath(response);
        System.out.println("Newly Created product id = "+json.getString("id"));
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test (priority = 3)
    public void deleteIndividualProduct(){
        Response response = Common.hitURrlAndGetResponse("DELETE","SpecificProduct","");
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath json = Common.getResponseAsJsonPath(response);
        System.out.println("Deleted newly Created product id = "+json.getString("id"));
    }
}
