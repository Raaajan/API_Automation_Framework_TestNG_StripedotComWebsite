package Testcases;

import Reporting.ExtentListeners;
import Utilities.Base;
import Utilities.Common;
import Utilities.ExcelReadWriteActions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

//@Listeners(ExtentListeners.class)
public class ProductApiTest extends Base {

    public static Object product_id;
    public static HashMap<String,Object> product_res = new HashMap<String, Object>();

    @Test
    public void getAllProductDetailsWithValidEndPoint() throws IOException {
        Response response = Common.hitURrlAndGetResponse("GET","Allproducts","");
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath get_json = Common.getResponseAsJsonPath(response);

        int total_Products = get_json.getList("data").size();
        System.out.println("total_Products = "+total_Products);
        for(int i=0; i<total_Products;i++){
            String res_product_id = get_json.getString("data[" + i + "].id");
            product_res.put("Id",res_product_id);
            String res_product_name = get_json.getString("data["+i+"].name");
            product_res.put("Name",res_product_name);
            String res_product_description = get_json.getString("data["+i+"].description");
            product_res.put("Description",res_product_description);
            System.out.println(get_json.getString("data["+i+"].id"));
            ExcelReadWriteActions.ExcelWriterUsingHashMap(product_res);
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
