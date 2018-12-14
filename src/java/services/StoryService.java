package services;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface StoryService {

        public int createUser(String fname, String lname, String userName,
                String password, String role);
	public boolean readUsers(String userName, String password);
        public boolean userExists(String userName);
        public String readRoles(String userName);
        public int addStories(String title, String description, String author, 
                String security, String userid);
        public int updateStories(String title, String description, String author,
                String security, String userid, String previousTitle);
        public HashMap readStories();
        public boolean deleteStories(String title, String userid);
        public int addFavourites(String userid, String title);
        public boolean readFavourites(String userid, String title);
        public int deleteFavourites(String userid, String title);
}
