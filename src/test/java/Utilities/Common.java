package Utilities;

import Testcases.CouponApiTest;
import Testcases.ProductApiTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static Utilities.Base.reqspec;

public class Common {


    public static Response hitURrlAndGetResponse(String httpMethod, String apiName, String bodyType){
        Response  response = null;
        switch(httpMethod){
            case "GET":
                if(apiName.equalsIgnoreCase("AllProducts"))
                  response  = reqspec.when().get(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT);
                if (apiName.equalsIgnoreCase("SpecificProduct"))
                    response  = reqspec.when().get(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT+"/"+ ProductApiTest.product_id);
                if (apiName.equalsIgnoreCase("AllCoupons"))
                    response  = reqspec.when().get(Contants.COUPONDETAILS_VALIDAPIENDPOINT);
                if (apiName.equalsIgnoreCase("SpecificCoupon"))
                    response  = reqspec.when().get(Contants.COUPONDETAILS_VALIDAPIENDPOINT+"/"+ CouponApiTest.coupon_id);
                break;
            case "POST":
                if (bodyType.equalsIgnoreCase("BodyAsFormParam"))
                    if(apiName.equalsIgnoreCase("ProductDetails")) {
                        response = reqspec.formParams(PostAPIBody.createProductBody()).when().post(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT);
                    } else if (apiName.equalsIgnoreCase("CouponDetails")) {
                        response = reqspec.formParams(PostAPIBody.createCouponBody()).when().post(Contants.COUPONDETAILS_VALIDAPIENDPOINT);
                    }
                break;
            case "PUT":

                break;
            case "DELETE":
                if (apiName.equalsIgnoreCase("SpecificProduct"))
                 response = reqspec.when().delete(Contants.PRODUCTDETAILS_VALIDAPIENDPOINT+"/"+ ProductApiTest.product_id);
                if(apiName.equalsIgnoreCase("SpecificCoupon"))
                    response = reqspec.when().delete(Contants.COUPONDETAILS_VALIDAPIENDPOINT+"/"+ CouponApiTest.coupon_id);
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
