package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface
{
	public void insertBlog(Blog blog) throws Exception
	{
		int id = blog.getBlogId();
		String title = blog.getBlogTitle();
		String desc = blog.getBlogDescription();
		LocalDate date = blog.getPostedOn();
		
		System.out.println("Enter details to insert into the blog:");
		String sql = "INSERT INTO BLOG(ID, TITLE, DESCRIPTION, DATE1) VALUES (?,?,?,?)";
		System.out.println("TABLE BLOG EXISTS");
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2, title);
		st.setString(3, desc);
		st.setDate(4, java.sql.Date.valueOf(date));
		st.executeQuery();
		ConnectionManager.getConnection().close();
	}

	public List<Blog> selectAllBlogs() throws SQLException, Exception 
	{	
		System.out.println("your Blogs are listed below:");
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * from BLOG");
		List<Blog> list = new ArrayList<Blog>();
		
		while(rs.next())
		{
			
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String desc = rs.getString("DESCRIPTION");
			Date date = rs.getDate("DATE1"); 
			
			Blog blog = new Blog();
			blog.setBlogId(id);
			blog.setBlogTitle(title);
			blog.setBlogDescription(desc);
			
			LocalDate date1 = date.toLocalDate();
			blog.setPostedOn(date1);
			list.add(blog);
			ConnectionManager.getConnection().close();
		}
		return list;
	}
}
© 2020 GitHub, Inc.