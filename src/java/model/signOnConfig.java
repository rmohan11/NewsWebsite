package model;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.StoryService;
import services.StoryServiceFactory;

/**
 *
 * @author rakesh
 */
@WebServlet(name = "signOnConfig", urlPatterns = {"/signOnConfig"})
public class signOnConfig extends HttpServlet {

    private static int numOfUsers = 0;
    public static HashMap<Integer, String> userMap = new HashMap <>();
    
    
    
    public static boolean addUser(HttpServletRequest request, HttpServletResponse 
            response) throws IOException{
        
        String userName = request.getParameter("user_name");
        String password = request.getParameter("user_password");
        String firstName = request.getParameter("user_first_name");
        String lastName = request.getParameter("user_last_name");
        String role = request.getParameter("role");
        boolean userExist = false; 
        
        
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        if (story.readUsers(userName, password))   
        {
           userExist = true;
        }
        
        if (userExist == false)
        {
            if (story.createUser(firstName, lastName, userName, password, role) != -1){
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean userErrorExists(HttpServletRequest request, HttpServletResponse 
            response) throws IOException
    {
        String userName = request.getParameter("user_name");
        String password = request.getParameter("user_password");
        
        if(userName.contains(";") || userName.contains(",") || 
           userName.contains(" ") || password.contains(";") || 
           password.contains(",") || password.contains(" "))
        {
            return true;
        }
        return false;
    }
    
    public static boolean loginUser(HttpServletRequest request, HttpServletResponse 
            response) throws IOException{
        
        String userName = request.getParameter("user_name");
        String password = request.getParameter("user_password");
        
        if(userName.contains(";") || userName.contains(",") || 
           userName.contains(" ") || password.contains(";") || 
           password.contains(",") || password.contains(" "))
        {
            return false;
        }
        Cookie lab2CookieUserName = new Cookie("userName", userName);
        response.addCookie(lab2CookieUserName);
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        HttpSession session = request.getSession();
        
        if (story.readUsers(userName, password))
        {
            String [] userParameters = story.readRoles(userName).split("/");
            
            session.setAttribute("currentUser", userParameters[0]);
            session.setAttribute("role", userParameters[1]);
            session.setAttribute("currentUserID", userName);

            request.setAttribute("currentUser", userParameters[0]);
            request.setAttribute("currentRole", userParameters[1]);
            request.setAttribute("currentUserID", userName);
            
            return true;
        }
        return false;
    }

    public static boolean userExists(HttpServletRequest request, HttpServletResponse 
            response) throws IOException{
        
        String userName = request.getParameter("user_name");
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        if(story.userExists(userName))
        {
            return true;
        }
        return false;
    }
    
    public static String getRole(String userName) throws IOException{
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        if (story.readRoles(userName) != null)
        {
            return story.readRoles(userName).split("/")[1];
        }
        return null;
    }
    
    public static boolean logoutUser(HttpServletRequest request, HttpServletResponse 
            response){
        
        HttpSession session = request.getSession();
        
        request.setAttribute("currentUser", "Guest");
        session.setAttribute("currentUser", "Guest");
        
        session.invalidate();
        return true;
    }
}
