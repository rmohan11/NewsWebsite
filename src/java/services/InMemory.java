/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashMap;

/**
 *
 * @author rakesh
 */
public class InMemory implements StoryService {

    int id = 0;
    int storyId = 0;
    int favorId = 0;
    HashMap <Integer, String> userMap = new HashMap<>();
    HashMap <Integer, String> dataMap = new HashMap<>();
    HashMap <Integer, String> favoriteMap = new HashMap<>();
    @Override
    public int createUser(String fname, String lname, 
            String userName, String password, String role) {
        
        if (lname == null || fname == null || lname.length() == 0 || fname.length() == 0) {
			return -1;
		}
        
        userMap.put(++id, fname+"/"+lname+"/"+role+"/"+userName+"/"+password);
        return 1;
    }

    @Override
    public boolean readUsers(String userName, String password) {
    
        if (userName == null || password == null 
                || userName.length() == 0 || password.length() == 0) {
                    return false;
            }
        
        for (String userDetails : userMap.values())
        {
            String [] userDtl = userDetails.split("/");
            if(userName.equals(userDtl[3]) &&
                    password.equals(userDtl[4]))
            {
                return true;
            }
        }
        return false;
    }
    
    
    @Override
    public boolean userExists(String userName) {
    
        if (userName == null || userName.length() == 0 ) {
                    return false;
            }
        
        for (String userDetails : userMap.values())
        {
            String [] userDtl = userDetails.split("/");
            if(userName.equals(userDtl[3]))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String readRoles(String userName) {
    
        String role = null;
        
        for (String userDetails : userMap.values())
        {
            String [] userDtl = userDetails.split("/");
            if(userName.equals(userDtl[3]))
            {
                role = userDtl[0]+"/"+userDtl[2];
                return role;
            }
        }
        return role;
    }

    @Override
    public int addStories(String title, String description, 
            String author, String security, String userid) {
        
        if (title == null || description == null || title.length() == 0 
                || description.length() == 0) {
                return -1;
        }
        
        dataMap.put(++storyId, title+"/"+description+"/"+author+"/"
                +security+"/"+userid);
        return 1;
    }

    @Override
    public int updateStories(String title, String description, String author, 
            String security, String userid, String previousTitle) {
        
        for (Integer key: dataMap.keySet()){
            String [] data = dataMap.get(key).split("/");
            
            if (previousTitle.equals(data[0]))
            {
                dataMap.put(key, title+"/"+description+"/"+author+"/"
                +security+"/"+userid);
                return 1;
            }
        }
        return -1;
    }

    @Override
    public HashMap readStories() {
        
        return dataMap;
    }

    @Override
    public boolean deleteStories(String title, String userid) {
        
        for(Integer key: dataMap.keySet()){
            String [] data = dataMap.get(key).split("/");
            
            if(title.equals(data[0]) && 
                    userid.equals(data[4]))
            {
                dataMap.remove(key);
                return true;
            }
        }
        return false;
    }

    @Override
    public int addFavourites(String userid, String title) {
        
        favoriteMap.put(++favorId, userid+"/"+title);
        return 1;
    }

    @Override
    public boolean readFavourites(String userid, String title) {
        
        if (userid == null || title == null || userid.length() == 0 || 
                    title.length() == 0) {
                    return false;
            }
        for(String favourite : favoriteMap.values())
        {
            String [] favor = favourite.split("/");
            
            if (userid.equals(favor[0]) &&
                    title.equals(favor[1]))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int deleteFavourites(String userid, String title) {
        
        if (userid == null || title == null || userid.length() == 0 || 
                    title.length() == 0) {
                    return -1;
            }
        
        for (Integer key: favoriteMap.keySet())
        {
            String [] favor = favoriteMap.get(key).split("/");
            if (userid.equals(favor[0]) &&
                    title.equals(favor[1]))
            {
                favoriteMap.remove(key);
                return 1;
            }
        }
        return -1;
    }
    
}
