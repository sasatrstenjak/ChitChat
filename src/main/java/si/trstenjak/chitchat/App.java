package si.trstenjak.chitchat;

import org.apache.http.client.fluent.Request;

import java.io.IOException;

/**
 * Hello ChitChat!
 */
public class App {
    public static void main(String[] args) {
        try {
            String hello = Request.Get("http://chitchat.andrej.com")
                                  .execute()
                                  .returnContent().asString();
            System.out.println(hello);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
