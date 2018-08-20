package com.tinhcao.mailjet;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Contact;
import com.mailjet.client.resource.Email;
import org.json.JSONArray;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) {

        MailjetClient client = new MailjetClient(
                "bd1d845eba378d81a63f0d7c82b245ba",
                "f3fc9c3e1eee3a8e647be98c136d513e");

        MailjetRequest email;
        JSONArray recipients;
        MailjetResponse response;

        recipients = new JSONArray()
                .put(new JSONObject().put(Contact.EMAIL, "caodangtinh@gmail.com"))
                .put(new JSONObject().put(Contact.EMAIL, "littlesnail18@gmail.com"));

        email = new MailjetRequest(Email.resource)
                .property(Email.FROMNAME, "tinhcao")
                .property(Email.FROMEMAIL, "caodangtinh@gmail.com")
                .property(Email.SUBJECT, "Activate your account")
                .property(Email.TEXTPART, "Please active your account : http://active.com?token=yNjU2ZjljZDY0NWI1ODdiMjA0ZDU2YTE5M2ZkMWZiZWI1Zg")
                .property(Email.RECIPIENTS, recipients)
                .property(Email.MJCUSTOMID, "JAVA-Email");

        try {
            response = client.post(email);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
        } catch (MailjetException e) {
            e.printStackTrace();
        } catch (MailjetSocketTimeoutException e) {
            e.printStackTrace();
        }
    }
}
