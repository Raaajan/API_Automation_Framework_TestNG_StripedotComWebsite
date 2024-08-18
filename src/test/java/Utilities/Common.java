package Utilities;

import Tescases.ProductApiTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;

import static Utilities.Base.reqspec;

public class Common {


    public static Response hitURrlAndGetResponse(String httpMethod, String info){
        Response  response = null;
        switch(httpMethod){
            case "GET":
                if(info.equalsIgnoreCase("AllProducts"))
                  response  = reqspec.when().get(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT);
                if (info.equalsIgnoreCase("SpecificProduct"))
                    response  = reqspec.when().get(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT+"/"+ ProductApiTest.product_id);
                break;
            case "POST":
                if (info.equalsIgnoreCase("BodyAsFormParam"))
                 response  = reqspec.formParams(PostAPIBody.createProductBody()).when().post(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT);
                break;
            case "PUT":

                break;
            case "DELETE":
                if (info.equalsIgnoreCase("SpecificProduct"))
                 response = reqspec.when().delete(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT+"/"+ ProductApiTest.product_id);
                break;

            default:
                System.out.println("Invalid HTTP method");
        }
        return response;
    }

    public static JsonPath getResponseAsJsonPath(Response response){
        String res = response.asString();
        System.out.println(res);
        JsonPath json = new JsonPath(res);
        return json;
    }
}
