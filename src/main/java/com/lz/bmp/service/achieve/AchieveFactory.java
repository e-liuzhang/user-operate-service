package com.lz.bmp.service.achieve;

import com.lz.bmp.service.Achieve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rocky
 * @ClassName AchieveFactory
 * @Description
 * @Create by rocky
 * @Date 2020/11/2 下午5:22
 */
@Service
public class AchieveFactory {


//    @Autowired
//    private AchieveList achieveList;
//
//    @Autowired
//    private AchieveMap achieveMap;

    @Autowired
    private AchieveJsonArray achieveJsonArray;

    @Autowired
    private AchieveJsonObject achieveJsonObject;

//    private static final String LIST_STRING = "List";
//    private static final String MAP_STRING_INTEGER = "Map";
    private static final String JSON_OBJECT = "JSONOBJECT";
    private static final String JSON_ARRAY = "JSONARRAY";

    public Achieve getAchieve(String checkType){
        if(checkType == null){
            return null;
        }
//        if(checkType.startsWith(LIST_STRING)){
//            return achieveList;
//        } else if(checkType.startsWith(MAP_STRING_INTEGER)){
//            return achieveMap;
//        }else
        if(JSON_OBJECT.equals(checkType)){
            return achieveJsonObject;
        }else if(JSON_ARRAY.equals(checkType)){
            return achieveJsonArray;
        }
        return null;
    }

}
