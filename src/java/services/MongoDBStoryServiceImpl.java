/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rakesh
 */
public class MongoDBStoryServiceImpl implements StoryService{

    @Override
    public int createUser(String fname, String lname, 
            String userName, String password, String role) {
        
        if (lname == null || fname == null || lname.length() == 0 
                || fname.length() == 0) {
			return -1;
		}
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("users");

            BasicDBObject details = new BasicDBObject();
            
            details.put("first_name", fname);
            details.put("last_name", lname);
            details.put("role", role);
            details.put("user_name", userName);
            details.put("password", password);
            collection.insert(details);
            
            return 1;
        } catch (MongoException e) {
            e.printStackTrace();
                return -1;
        } catch (Throwable t) {
            t.printStackTrace();
        }finally{
            
        }
        
        return -1;
        
        
    }
        
    

    @Override
    public boolean readUsers(String userName, String password) {
        
        if (userName == null || password == null || 
                userName.length() == 0 || password.length() == 0) {
                    return false;
            }
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("users");
            BasicDBObject searchQuery = new BasicDBObject();
            BasicDBObject Fields = new BasicDBObject();
            Fields.put("user_name", "1");
            Fields.put("password", "1");

            DBCursor cursor = collection.find(searchQuery, Fields);

            while (cursor.hasNext()) {
                
                Map <String, String> details;
		details = cursor.next().toMap();
                String user_name = details.get("user_name");
                String pwd = details.get("password");
                
                if (userName.equals(user_name) && password.equals(pwd))
                {
                    return true;
                }
            }
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
            }
        return false;
    }
    

    

    @Override
    public boolean userExists(String userName) {
        if (userName == null || userName.length() == 0 ) {
                    return false;
            }
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("users");
            BasicDBObject searchQuery = new BasicDBObject();
            BasicDBObject Fields = new BasicDBObject();
            Fields.put("user_name", "1");

            DBCursor cursor = collection.find(searchQuery, Fields);

            while (cursor.hasNext()) {
                
                Map <String, String> details;
		details = cursor.next().toMap();
                String user_name = details.get("user_name");
                
                if (userName.equals(user_name))
                {
                    return true;
                }
            }
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
            }
        return false;
        
    }

    @Override
    public String readRoles(String userName) {
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("users");
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("user_name", userName);

            DBCursor cursor = collection.find(searchQuery);

            while (cursor.hasNext()) {
                
                Map <String, String> details;
		details = cursor.next().toMap();
                String first_name = details.get("first_name");
                String role = details.get("role");
                
                return first_name+"/"+role;
            }
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
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
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("news");

            BasicDBObject details = new BasicDBObject();
            
            details.put("title", title);
            details.put("description", description);
            details.put("author", author);
            details.put("security", security);
            details.put("userid", userid);
            collection.insert(details);
            
            return 1;
        } catch (MongoException e) {
            e.printStackTrace();
                return -1;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        return -1;
        
    }

    @Override
    public int updateStories(String title, String description, String author, 
            String security, String userid, String previousTitle) {
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("news");
            
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("title", previousTitle);
            searchQuery.put("userid", userid);
            
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("title", title);
            newDocument.put("description", description);
            newDocument.put("security", security);

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);
            collection.update(searchQuery, updateObj);
            
            return 1;
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
            }
        return -1;
    }

    @Override
    public HashMap readStories() {
        
        int id = 0;
        HashMap <Integer, String> dataMap = new HashMap<>();
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("news");
            DBCursor cursor = collection.find();
            
            while (cursor.hasNext()) {
                
                Map <String, String> details;
		details = cursor.next().toMap();
                String title = details.get("title");
                String description = details.get("description");
                String author = details.get("author");
                String security = details.get("security");
                String userid = details.get("userid");
                
                
                dataMap.put(++id, title+"/"+description+"/"+author+"/"+security+"/"
                                    +userid);
            }
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
            }
        
        return dataMap;
    }

    @Override
    public boolean deleteStories(String title, String userid) {
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("news");
            
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("title", title);
            searchQuery.put("userid", userid);
            
            collection.remove(searchQuery);
            return true;
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
            }
        return false;
    }

    @Override
    public int addFavourites(String userid, String title) {
        
        try{
            title = title.replace("''", "'");
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("favorites");

            BasicDBObject details = new BasicDBObject();
            
            details.put("userid", userid);
            details.put("title", title);
            collection.insert(details);
            
            return 1;
        } catch (MongoException e) {
            e.printStackTrace();
                return -1;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        return -1;
    }

    @Override
    public boolean readFavourites(String userid, String title) {
        
        if (userid == null || title == null || userid.length() == 0 || 
                    title.length() == 0) {
                    return false;
            }
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("favorites");
            DBCursor cursor = collection.find();

            while (cursor.hasNext()) {
                
                Map <String, String> details;
		details = cursor.next().toMap();
                String userID = details.get("userid");
                String Title = details.get("title");
                
                if (userid.equals(userID) && title.equals(Title))
                {
                    return true;
                }
            }
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
            }
        
        return false;
    }

    @Override
    public int deleteFavourites(String userid, String title) {
        
        try{
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("newnewsdb");

            DBCollection collection = db.getCollection("favorites");
            
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("title", title);
            searchQuery.put("userid", userid);
            
            collection.remove(searchQuery);
            return 1;
                        
            } catch (MongoException e) {
			e.printStackTrace();
            } catch (Throwable t) {
		    t.printStackTrace();
            }
        return -1;
    }
}
