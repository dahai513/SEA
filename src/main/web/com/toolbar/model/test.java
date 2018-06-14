package com.toolbar.model;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test {
         public static void main(String[] args) {
            String []cmds = {"curl", "-i", "-w", "状态%{http_code}；DNS时间%{time_namelookup}；"
                    + "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"
                    + "总时间%{time_total}","https://macan.alibaba-inc.com/selfCheck/startSingleSelfCheckJobBySellerCodeAndBusinessTypes?_input_charset=utf-8&sellerCode=604568622&callback=reqwest_1520910049023"};
            ProcessBuilder pb=new ProcessBuilder(cmds);
            pb.redirectErrorStream(true);
            Process p;
            try {
                p = pb.start();
                BufferedReader br=null;
                String line=null;

                br=new BufferedReader(new InputStreamReader(p.getInputStream()));
                while((line=br.readLine())!=null){
                    System.out.println("\t"+line);
                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
