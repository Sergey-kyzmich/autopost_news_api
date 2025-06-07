package AI_agents;
import org.ini4j.Ini;
import java.io.File;
import java.io.IOException;
public class test {
    public void main(String[] arg) {
        try {
            Ini ini = new Ini(new File("config.ini"));  
    } catch (IOException e){
        e.printStackTrace();
    };
    }
}