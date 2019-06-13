package movie.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoBase {
	protected Connection con=null;
	public void connect() throws Exception{
		if(con!=null) {
			return;
		}
		InitialContext context=null;
		try {
			String resourceName="jdbc/MySQL";
			String jndi="java:comp/env/"+resourceName;

			context=new InitialContext();
			DataSource dataSource=(DataSource)context.lookup(jndi);
			con=dataSource.getConnection();
		}
		catch(NamingException e) {
			e.printStackTrace();
			throw e;
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	public void close() {
		if(con!=null) {
			try {
				con.close();
				con=null;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}