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
public class CouponApiTest extends Base {

    public static Object coupon_id;

    @Test
    public void getAllCouponDetailsWithValidEndPoint(){
        Response response = Common.hitURrlAndGetResponse("GET","AllCoupons","");
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath get_json = Common.getResponseAsJsonPath(response);

        int total_Coupons = get_json.getList("dat").size();
        System.out.println("total_Coupons = "+total_Coupons);
        for(int i=0; i<total_Coupons;i++){
            System.out.println(get_json.getString("data["+i+"].id"));
        }
    }


    @Test (priority = 1)
    public void createCoupon(){

        Response response  = Common.hitURrlAndGetResponse("POST","CouponDetails","BodyAsFormParam");
        JsonPath json = Common.getResponseAsJsonPath(response);
        coupon_id = json.get("id");
        System.out.println("Coupon_id = "+coupon_id);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(json.get("duration"),"repeating");

    }

    @Test (priority = 2)
    public void getNewlyCreatedCouponDetails(){
        Response response = Common.hitURrlAndGetResponse("GET","SpecificCoupon","");
        JsonPath json = Common.getResponseAsJsonPath(response);
        System.out.println("Newly Created Coupon id = "+json.getString("id"));
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test (priority = 3)
    public void deleteIndividualCoupon(){
        Response response = Common.hitURrlAndGetResponse("DELETE","SpecificCoupon","");
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath json = Common.getResponseAsJsonPath(response);
        System.out.println("Deleted newly Created Coupon id = "+json.getString("id"));
    }
}
