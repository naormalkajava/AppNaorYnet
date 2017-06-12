package com.example.ericbell.appnaorynet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hackeru on 01/06/2017.
 */

public class IO {


    public static String getString (InputStream in ,String charest) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line;

        BufferedReader reader = new BufferedReader(new InputStreamReader(in,charest));
        try {


            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        finally {
            reader.close();
        }
        return builder.toString();
    }

   public static String getString (InputStream in ) throws IOException {
       return getString(in,"UTF-8");
   }


   public static String readWebSite (String url ,String charest) throws IOException {
       URL address = new URL(url);
       URLConnection con = address.openConnection();
       InputStream in = con.getInputStream();
       return getString(in,charest);
   }
    public static String readWebSite (String url) throws IOException {
        return readWebSite(url,"UTF-8");
    }
}