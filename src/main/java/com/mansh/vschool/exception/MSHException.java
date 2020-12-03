package com.mansh.vschool.exception;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MSHException extends RuntimeException{

    private String errMsg;
    private static Map<String,String> errMap = new HashMap<>();

    static{
        load();
    }

    /**
     * 从数据库查询对应的Exception
     */
    private static void load(){
        Connection conn = null;
        try{

        } catch (Exception e){

        } finally {

        }
    }

    public MSHException(String res){
        this.errMsg = res;
    }

    public String getMessage(){
        return this.errMsg;
    }



}
