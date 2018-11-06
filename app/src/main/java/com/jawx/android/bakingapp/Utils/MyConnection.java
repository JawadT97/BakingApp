package com.jawx.android.bakingapp.Utils;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyConnection {
    static String URL ="";
    public static URL getURL() throws MalformedURLException {
        URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        Uri builtUri = Uri.parse(URL)
                .buildUpon()
                .build();
        URL url = null;
        url = new URL(builtUri.toString());
        return url;
    }
    public static String getData (URL url) throws IOException {
        StringBuffer response = new StringBuffer();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader bufferedInputStream=new BufferedReader(new InputStreamReader( con.getInputStream()));
            String data;
            while ((data = bufferedInputStream.readLine()) != null) {
                response.append(data);
            }
            bufferedInputStream.close();
        } finally {
            con.disconnect();
        }
        return response.toString();
    }
}
