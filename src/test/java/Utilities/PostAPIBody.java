package Utilities;

import java.util.HashMap;

public class PostAPIBody {

    public static HashMap createProductBody(){
        HashMap<String, Object> createProductBody = new HashMap<>();
        createProductBody.put("name","Testnew");
        createProductBody.put("shippable","true");
        createProductBody.put("description","created though hashmap");
        createProductBody.put("url","www.xyz.com");

        return createProductBody;
    }

    public static HashMap createCouponBody(){
        HashMap<String, Object> createCouponBody = new HashMap<>();
        createCouponBody.put("name","try250");
        createCouponBody.put("duration","repeating");
        createCouponBody.put("duration_in_months",3);
        createCouponBody.put("percent_off","25.30");

        return createCouponBody;
    }
}
