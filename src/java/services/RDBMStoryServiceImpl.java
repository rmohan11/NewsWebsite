package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

public class RDBMStoryServiceImpl implements StoryService {

	private static String __jdbcUrl;
	private static String __jdbcUser;
	private static String __jdbcPasswd;
	
	public RDBMStoryServiceImpl() {
	}
	
        public int createUser(String fname, String lname, String userName,
                String password, String role) {
		if (lname == null || fname == null || lname.length() == 0 || fname.length() == 0) {
			return -1;
		}
		Connection conn = null;
		Statement stmt = null;
		try {
			try {
				Class.forName("org.postgresql.Driver");
			}
			catch (Throwable t) {
				t.printStackTrace();
				return -1;
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.createStatement();
			return stmt.executeUpdate("INSERT INTO users (first_name, last_name, role, user_name, password )"
                                + "VALUES ('" + fname + "', '" + lname + "', '" + role + "', '"+userName+"', '"+password+"')");
                        
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return -1;
		} finally { 
			try {
					if (stmt != null) { stmt.close(); }
			} catch (Exception e2) { e2.printStackTrace(); }
			finally {
				try {
					if (conn != null) { conn.close(); }
				} catch (Exception e3) { e3.printStackTrace(); }
			}
		}
    }
        
	@Override
	public boolean readUsers(String userName, String password) {
		// TODO Auto-generated method stub
            if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
                    return false;
            }

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                    try {
                            Class.forName("org.postgresql.Driver");
                    }
                    catch (Throwable t) {
                            t.printStackTrace();
                            return false;
                    }

                    conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                    stmt = conn.createStatement();

                    rs = stmt.executeQuery("SELECT user_name, password FROM users");

                    while (rs.next()) {
                            if (userName.equals(rs.getString(1)) && password.equals(rs.getString(2)))
                            {
                                    return true;
                            }
                    }			
            } catch (SQLException sqe) {
                    sqe.printStackTrace();
                    return false;
            } finally { 
                    try {
                                    if (stmt != null) { stmt.close(); }
                    } catch (Exception e2) { e2.printStackTrace(); }
                    finally {
                            try {
                                    if (conn != null) { conn.close(); }
                            } catch (Exception e3) { e3.printStackTrace(); }
                    }
            }

            return false;
	}
	
        @Override
	public boolean userExists(String userName) {
		// TODO Auto-generated method stub
            if (userName == null || userName.length() == 0 ) {
                    return false;
            }

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                    try {
                            Class.forName("org.postgresql.Driver");
                    }
                    catch (Throwable t) {
                            t.printStackTrace();
                            return false;
                    }

                    conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                    stmt = conn.createStatement();

                    rs = stmt.executeQuery("SELECT user_name, password FROM users");

                    while (rs.next()) {
                            if (userName.equals(rs.getString(1)))
                            {
                                    return true;
                            }
                    }			
            } catch (SQLException sqe) {
                    sqe.printStackTrace();
                    return false;
            } finally { 
                    try {
                                    if (stmt != null) { stmt.close(); }
                    } catch (Exception e2) { e2.printStackTrace(); }
                    finally {
                            try {
                                    if (conn != null) { conn.close(); }
                            } catch (Exception e3) { e3.printStackTrace(); }
                    }
            }

            return false;
	}
        
        @Override
	public String readRoles(String userName) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			try {
				Class.forName("org.postgresql.Driver");
			}
			catch (Throwable t) {
				t.printStackTrace();
                                return null;
			}
			
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT first_name, role FROM users "
                                + "WHERE user_name = '"+userName+"'");
                        
			while (rs.next()) {
                            
                            return rs.getString(1)+"/"+rs.getString(2);
			}			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return null;
		} finally { 
			try {
					if (stmt != null) { stmt.close(); }
			} catch (Exception e2) { e2.printStackTrace(); }
			finally {
				try {
					if (conn != null) { conn.close(); }
				} catch (Exception e3) { e3.printStackTrace(); }
			}
		}
		
		return null;
	}
        
        @Override
        public int addStories(String title, String description, String author, 
        String security, String userid) {
        if (title == null || description == null || title.length() == 0 
                || description.length() == 0) {
                return -1;
        }
        Connection conn = null;
        Statement stmt = null;
        try {
                try {
                        Class.forName("org.postgresql.Driver");
                }
                catch (Throwable t) {
                        t.printStackTrace();
                        return -1;
                }
                conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                stmt = conn.createStatement();
                
                
                return stmt.executeUpdate("INSERT INTO news (title, "
                        + "description, author, security, userid )"
                        + "VALUES ('" + title + "', '" + description + "', '" 
                        + author + "', '"+security+"', '"+userid+"')");

        } catch (SQLException sqe) {
                sqe.printStackTrace();
                return -1;
        } finally {  
                try {
                                if (stmt != null) { stmt.close(); }
                } catch (Exception e2) { e2.printStackTrace(); }
                finally {
                        try {
                                if (conn != null) { conn.close(); }
                        } catch (Exception e3) { e3.printStackTrace(); }
                }
        }
    }
        
        public int updateStories(String title, String description, String author,
                String security, String userid, String previousTitle){
            
            Connection conn = null;
            Statement stmt = null;
            try {
                    try {
                            Class.forName("org.postgresql.Driver");
                    }
                    catch (Throwable t) {
                            t.printStackTrace();
                            return -1;
                    }
                    conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                    stmt = conn.createStatement();

                    return stmt.executeUpdate("UPDATE news SET title ='" + title 
                            + "', description ='" + description + "', security = '"
                            +security+"' WHERE title = '"
                            + previousTitle+"' AND userid ='"+userid+"'");

            } catch (SQLException sqe) {
                    sqe.printStackTrace();
                    return -1;
            } finally {  
                    try {
                                    if (stmt != null) { stmt.close(); }
                    } catch (Exception e2) { e2.printStackTrace(); }
                    finally {
                            try {
                                    if (conn != null) { conn.close(); }
                            } catch (Exception e3) { e3.printStackTrace(); }
                    }
            }
        }
	
        @Override
	public HashMap readStories() {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
                HashMap <Integer, String> dataMap = new HashMap<>();
                
		try {
			try {
				Class.forName("org.postgresql.Driver");
			}
			catch (Throwable t) {
				t.printStackTrace();
                                return null;
			}
			
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT title, description, author, "
                                + "security, userid FROM news");
                        
			while (rs.next()) {
                            
                            dataMap.put(++id, rs.getString(1).trim()+"/"+rs.getString(2).trim()+
                                    "/"+rs.getString(3).trim()+"/"+rs.getString(4).trim()+"/"
                                    +rs.getString(5).trim());
			}			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return null;
		} finally { 
			try {
					if (stmt != null) { stmt.close(); }
			} catch (Exception e2) { e2.printStackTrace(); }
			finally {
				try {
					if (conn != null) { conn.close(); }
				} catch (Exception e3) { e3.printStackTrace(); }
			}
		}
		
		return dataMap;
	}
        
        
        public boolean deleteStories(String title, String userid){
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                    try {
                            Class.forName("org.postgresql.Driver");
                    }
                    catch (Throwable t) {
                            t.printStackTrace();
                            return false;
                    }

                    title = title.replace("'", "%");
                    conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                    stmt = conn.createStatement();
                    

                    stmt.executeQuery("DELETE FROM news WHERE title LIKE '%"+title
                            +"%' AND userid ='"+userid+"'");
                    return true;


            } catch (SQLException sqe) {
                    sqe.printStackTrace();
                    return false;
            } finally { 
                    try {
                                    if (stmt != null) { stmt.close(); }
                    } catch (Exception e2) { e2.printStackTrace(); }
                    finally {
                            try {
                                    if (conn != null) { conn.close(); }
                            } catch (Exception e3) { e3.printStackTrace(); }
                    }
            }
        }
        
        public int addFavourites(String userid, String title){
            
            Connection conn = null;
            Statement stmt = null;
            try {
                    try {
                            Class.forName("org.postgresql.Driver");
                    }
                    catch (Throwable t) {
                            t.printStackTrace();
                            return -1;
                    }
                    conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                    stmt = conn.createStatement();


                    return stmt.executeUpdate("INSERT INTO favorites (userid, title) "
                            + "VALUES ('" + userid + "', '" + title + "')");

            } catch (SQLException sqe) {
                    sqe.printStackTrace();
                    return -1;
            } finally {  
                    try {
                                    if (stmt != null) { stmt.close(); }
                    } catch (Exception e2) { e2.printStackTrace(); }
                    finally {
                            try {
                                    if (conn != null) { conn.close(); }
                            } catch (Exception e3) { e3.printStackTrace(); }
                    }
        }
        
        }
        
        public boolean readFavourites(String userid, String title){
            
            if (userid == null || title == null || userid.length() == 0 || 
                    title.length() == 0) {
                    return false;
            }

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                    try {
                            Class.forName("org.postgresql.Driver");
                    }
                    catch (Throwable t) {
                            t.printStackTrace();
                            return false;
                    }

                    conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                    stmt = conn.createStatement();

                    rs = stmt.executeQuery("SELECT userid, title FROM favorites");

                    while (rs.next()) {
                        
                            if (userid.equals(rs.getString(1)) && 
                                    title.equals(rs.getString(2)))
                            {   
                                return true;
                            }
                    }			
            } catch (SQLException sqe) {
                    sqe.printStackTrace();
                    return false;
            } finally { 
                    try {
                                    if (stmt != null) { stmt.close(); }
                    } catch (Exception e2) { e2.printStackTrace(); }
                    finally {
                            try {
                                    if (conn != null) { conn.close(); }
                            } catch (Exception e3) { e3.printStackTrace(); }
                    }
            }

            return false;
        }
        
        public int deleteFavourites(String userid, String title){
            Connection conn = null;
            Statement stmt = null;

            try {
                    try {
                            Class.forName("org.postgresql.Driver");
                    }
                    catch (Throwable t) {
                            t.printStackTrace();
                            return -1;
                    }

                    conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
                    stmt = conn.createStatement();
                    
                    title = title.replace("'", "%");
                    
                    return stmt.executeUpdate("DELETE FROM favorites WHERE title LIKE '%"+title
                            +"%' AND userid ='"+userid+"'");
                    


            } catch (SQLException sqe) {
                    sqe.printStackTrace();
                    return -1;
            } finally { 
                    try {
                                    if (stmt != null) { stmt.close(); }
                    } catch (Exception e2) { e2.printStackTrace(); }
                    finally {
                            try {
                                    if (conn != null) { conn.close(); }
                            } catch (Exception e3) { e3.printStackTrace(); }
                    }
            }
        }
        
	static {
            try {
                    Context ctx = new InitialContext();
                    Context env = (Context) ctx.lookup("java:comp/env");
                    final String fileName = (String) env.lookup("rdbmPropertiesFile");

                    Properties dbProperties = new Properties();
                    dbProperties.load(RDBMStoryServiceImpl.class.getClassLoader().getResourceAsStream(fileName));
                    __jdbcUrl    = dbProperties.getProperty("jdbcUrl");
                    __jdbcUser   = dbProperties.getProperty("jdbcUser");
                    __jdbcPasswd = dbProperties.getProperty("jdbcPasswd");
            } catch (Throwable t) {
                    t.printStackTrace();
            } finally {
	}
}

}
