package workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jiaweizhang on 11/18/2016.
 */

@RestController
public class AdminController {

    @Autowired
    private JdbcTemplate jt;

    @RequestMapping(value = "/dbup",
            method = RequestMethod.GET)
    @ResponseBody
    public boolean dbup() throws IOException {
        try {
            String query = readQuery("sql/setup.sql");
            jt.execute(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String readQuery(String file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = in.readLine()) != null) {
            int index;
            // removes single-line comments
            if ((index = str.indexOf("--")) >= 0) {
                str = str.substring(0, index);
            }
            sb.append(str).append(" ");
        }
        in.close();

        return sb.toString();
    }

}
