package  com.doglovers.abandog.dto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

public class C {

	public static SqlSession sqlSession;
	
	public static ServletContext context;
	
	public static HttpSession session;
}
