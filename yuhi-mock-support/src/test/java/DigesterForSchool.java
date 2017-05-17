import java.io.File;
import com.yuhi.mock.entity.MockTestConfig;
import com.yuhi.mock.service.MockMethodApdate;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.ExtendedBaseRules;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
public class DigesterForSchool {
    public static void main(String[] args) throws Exception{
        String path = System.getProperty("user.dir") + File.separator + "etc";
        Digester digester = new Digester();    
        digester.addObjectCreate("school", "School");    //2.类路径
        digester.addSetProperties("school");    
//        digester.addObjectCreate("school/department", "Department");
//        digester.addSetProperties("school/department");
//        digester.addSetNext("school/department", "addDepartment");
//        digester.addObjectCreate("school/department/student",
//                "Student");
//        digester.addSetNext("school/department/student", "addStudent");
//        digester.addSetProperties("school/department/student");
        School school = (School)digester.parse(DigesterForSchool.class.getResourceAsStream("/school.xml"));
        System.out.println(school);    
    }    
}  