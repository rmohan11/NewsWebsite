/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "defaultStory", urlPatterns = {"/defaultStory"})
public class news extends HttpServlet {

    private static int defaultStoryCount = 0;
   
    public static List defaultStory (HttpServletRequest request) 
            throws IOException, ServletException
    {
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        HttpSession session = request.getSession();
        HashMap<Integer, String> dataMap = new HashMap <>();
        dataMap = story.readStories();
                
        List<String> titleList=new ArrayList();
        
        if (session.getAttribute("role") != null &&
                session.getAttribute("role").toString().trim().equals("subscriber"))
        {
            String userid = null;
            if (session.getAttribute("currentUserID") != null)
            {
                userid = session.getAttribute("currentUserID").toString().trim();
            }
                
            for (String report: dataMap.values())
            {
                String [] data = report.split("/");
                
                if(story.readFavourites(userid, data[0]))
                {
                    titleList.add(data[0]+"/"+data[3]+"/"+data[2]+"/"+data[4]);
                }
            }
            
            for (String report: dataMap.values())
            {
                String [] data = report.split("/");
                
                if(!story.readFavourites(userid, data[0]))
                {
                    titleList.add(data[0]+"/"+data[3]+"/"+data[2]+"/"+data[4]);
                }
            }
        }
        else{

            for (String report: dataMap.values())
            {
                String [] data = report.split("/");
                titleList.add(data[0]+"/"+data[3]+"/"+data[2]+"/"+data[4]);

            }
       }
        
        return titleList;
    }
    
    
    public static void addStory(HttpServletRequest request) throws 
            ServletException, IOException{
     
        String title = request.getParameter("header").replaceAll("'", "''");
        String content = request.getParameter("content").replaceAll("'", "''");
        String security = request.getParameter("security");
        
        HttpSession session = request.getSession();
        
        String author = session.getAttribute("currentUser").toString();
        String userID = session.getAttribute("currentUserID").toString();
        String role = session.getAttribute("role").toString();
        
        request.setAttribute("currentUser", author);
        request.setAttribute("currentUserID", userID);
        request.setAttribute("currentRole", role);
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        story.addStories(title, content, author, security, userID);
        
        
    }
    
    
    public static boolean editStory(HttpServletRequest request) throws 
            ServletException, IOException{
     
        String title = request.getParameter("header").replaceAll("'", "''");
        String content = request.getParameter("content").replaceAll("'", "''");
        String security = request.getParameter("security");
        
        HttpSession session = request.getSession();
        
        String author = session.getAttribute("currentUser").toString();
        String userID = session.getAttribute("currentUserID").toString();
        String role = session.getAttribute("role").toString();
        String previousTitle = session.getAttribute("editTitle").toString().replaceAll("'", "''");
        
        request.setAttribute("currentUser", author);
        request.setAttribute("currentUserID", userID);
        request.setAttribute("currentRole", role);
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        if (story.updateStories(title, content, author, security, 
                userID, previousTitle) != -1)
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean deleteStory(HttpServletRequest request, String title) throws 
            ServletException, IOException{
        
        HttpSession session = request.getSession();
        
        String author = session.getAttribute("currentUser").toString();
        String userID = session.getAttribute("currentUserID").toString();
        String role = session.getAttribute("role").toString();
        
        request.setAttribute("currentUser", author);
        request.setAttribute("currentUserID", userID);
        request.setAttribute("currentRole", role);
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        if (story.deleteStories(title, userID))
        {
            return true;
        }
        
        return false;
    }
    
    
    public static String getStory(HttpServletRequest request, String title){
        
        String temp_title = title.replaceAll("[^a-zA-Z ]", "");
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        HashMap<Integer, String> reportMap = new HashMap <>();
        
        reportMap = story.readStories();
        
        for (String report : reportMap.values()){
            String [] details = report.split("/");
            String temp_data_title = details[0].replaceAll("[^a-zA-Z ]", "");
            
            if(temp_data_title.equals(temp_title))
            {
                request.setAttribute("detailTopic", details[0]);
                return report;
            }
        }
        return null;
    }
    
    public static boolean manageFavourites(String userid, String title,
            String favorite){
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        boolean favRecord = false;
        
        favRecord = story.readFavourites(userid, title);
        
        System.out.println(favRecord);
        
        if (favorite == null && favRecord == true)
        {
            story.deleteFavourites(userid, title);
            return true;
        }
        else if (favorite != null && favRecord == false)
        {
            title = title.replace("'", "''");
            story.addFavourites(userid, title);
            return true;
        }
        else if ((favorite == null && favRecord == false)
                || (favorite != null && favRecord == true))
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean readFavourites(String userid, String title){
        
        StoryService story = null;
        story = StoryServiceFactory.getInstance();
        
        if (story.readFavourites(userid, title))
        {
            return true;
        }
        
        return false;
    }
}
