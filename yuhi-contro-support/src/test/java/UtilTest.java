

import java.util.Map;

import org.junit.Test;

import com.yuhi.base.entity.SysFile;
import com.yuhi.util.BeanUtil;

public class UtilTest {

	
	@Test
	public void objectToMap() throws Exception{
		SysFile object=new SysFile();
		object.setName("aa");
		BeanUtil bu=BeanUtil.getInstance();
		Map<String, Object> map=bu.objectToMap(object);
		System.out.println(map);
		object= (SysFile) bu.mapToObject(map, SysFile.class);
	}
	
	
}
