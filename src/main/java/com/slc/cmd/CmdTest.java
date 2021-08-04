package com.slc.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CmdTest {

    public static void main(String[] args) {
        try {
            String []arr=new String[1];
            arr[0]="-ano";
            Process ipconfig = Runtime.getRuntime().exec("netstat",arr);
            InputStream inputStream = ipconfig.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("GBK")));
            String read;
            while((read=br.readLine())!=null){
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
