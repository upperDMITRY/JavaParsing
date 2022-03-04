import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import dto.ValCurs;
import dto.Valute;
import my.first.httpProgram.PropertyReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestPropertyReader {

    public static void main(String[] args) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(PropertyReader.getProperty("bnmUrl"));
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));

        String fullResponse = "";
        String line = "";

        while ((line = rd.readLine()) != null){
            fullResponse += line + "\r\n";
        }
        System.out.println(fullResponse);

        XStream xStream = new XStream();

        xStream.processAnnotations(ValCurs.class);
        xStream.processAnnotations(Valute.class);
        xStream.addPermission(NoTypePermission.NONE);

        xStream.addImplicitCollection(ValCurs.class, "valutes", Valute.class);
        ValCurs valCurs = (ValCurs) xStream.fromXML(fullResponse);

        for(Valute valute : valCurs.getValutes()){
            System.out.println(valute.getName());
        }
    }
}
