package vada.constants;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class CommonConstants {
	
	public static Properties props;
	
	static {
		props = new Properties();
		try {
			File file
				= new File("C:/eclipse_workspace/Vada/src/main/webapp/WEB-INF/props/common.properties");
			Reader reader = new FileReader(file);
			props.load(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // static 초기화 블록

} // class





