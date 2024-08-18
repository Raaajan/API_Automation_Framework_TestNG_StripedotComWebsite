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

}
