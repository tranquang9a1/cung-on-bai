/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author dinhquangtrung
 */
public class CMSLoginChecker {

    private List<String> cookies;
    private final String USER_AGENT = "Mozilla/5.0";
    String loginUrl = "http://cms-hcm.fpt.edu.vn/elearning/login/index.php";
    String targetUrl = "http://cms-hcm.fpt.edu.vn/index.php";
    private final String REFERER = "http://cms-hcm.fpt.edu.vn/elearning/login/index.php";
    private static final String HOST = "cms-hcm.fpt.edu.vn";

    public boolean checkLogin(String username, String password) {
        try {
            // make sure cookies is turn on
            CookieHandler.setDefault(new CookieManager());
            String postParams = "username=" + URLEncoder.encode(username, "UTF-8")
                    + "&password=" + URLEncoder.encode(password, "UTF-8")
                    + "&iboard_url=" + URLEncoder
                    .encode("http://cms-hcm.fpt.edu.vn/index.php", "UTF-8")
                    + "&rememberusername=1"
                    + "&=Login";

            String response = sendPost(loginUrl, postParams);
            
            if (response.contains("CMS-HCM: Login to the site")) {
                return false;
            } else {
                return true;
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String sendPost(String url, String postParams) throws Exception {

        HttpURLConnection conn;

        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", HOST);
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "vi-VN,vi;q=0.8,fr-FR;q=0.6,fr;q=0.4,en-US;q=0.2,en;q=0.2");
        if (this.cookies != null) {
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", REFERER);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Send post request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);
        System.out.println("Location : " + conn.getHeaderField("Location"));

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("Response: " + response);
        return response.toString();
    }

    private String getPageContentHttp(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        // default is GET
        conn.setRequestMethod("GET");
        return getPageContent(conn);
    }

    private String getPageContentHttps(String url) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection.setFollowRedirects(false);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
        // default is GET
        conn.setRequestMethod("GET");
        return getPageContent(conn);
    }

    private String getPageContent(URLConnection conn) throws Exception {


        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "vi-VN,vi;q=0.8,fr-FR;q=0.6,fr;q=0.4,en-US;q=0.2,en;q=0.2");
        if (cookies != null) {
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        try {
            int responseCode = ((HttpURLConnection) conn).getResponseCode();
            System.out.println("Response Code : " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Get the response cookies
        this.cookies = conn.getHeaderFields().get("Set-Cookie");

        return response.toString();

    }
}
