package control;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "redirect", urlPatterns = "/redirect")
public class redirect extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String option = request.getParameter("option");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        RequestDispatcher dispatch;
        news newreport = new news();
        
        if(option.equals("login"))
        {
            Cookie[] cookies = request.getCookies();
            
            if (cookies != null) {
            for (Cookie cookie : cookies) {
              if (cookie.getName().equals("userName")) {
                    request.setAttribute("cookieUser", cookie.getValue());
              }
            }
            dispatch = request.getRequestDispatcher("login.jsp");
            dispatch.forward(request, response);
            
            }
        }
        else if(option.equals("write")){
            
            if (session.getAttribute("currentUser") != null)
            {
                String user = session.getAttribute("currentUser").toString();
                String userId = session.getAttribute("currentUserID").toString();
                request.setAttribute("currentUser", user);
                
                String role = signOnConfig.getRole(userId).trim();
                
                request.setAttribute("currentRole",role);
            }
            dispatch = request.getRequestDispatcher("addEditStory.jsp");
            dispatch.forward(request, response);
            
        }
        else if(option.equals("logout")){
            
            signOnConfig.logoutUser(request, response);
            dispatch = request.getRequestDispatcher("index");
            dispatch.forward(request, response);
        }
        else if(option.equals("signup"))
        {
            dispatch = request.getRequestDispatcher("signup.jsp");
            dispatch.forward(request, response);
        }
        else if(option.contains("delete"))
        {
            String [] deleteParameters = option.split("/");
            session.setAttribute("deleteTitle", deleteParameters[1]);
            
            dispatch = request.getRequestDispatcher("delete.jsp");
            dispatch.forward(request, response);
        }
        else if(option.contains("edit"))
        {
            String [] editParameters = option.split("/");
            session.setAttribute("editTitle", editParameters[1]);
            
            String [] storyDetails = newreport.getStory(request, 
                    editParameters[1]).split("/");
            
            String user = storyDetails[4];
            String role = signOnConfig.getRole(user).trim();
            
            request.setAttribute("currentRole",role);
            
            request.setAttribute("currentTitle", storyDetails[0]);
            request.setAttribute("currentReport", storyDetails[1]);
            request.setAttribute("currentSecurity", storyDetails[3]);
            
            dispatch = request.getRequestDispatcher("addEditStory.jsp");
            dispatch.forward(request, response);
        }
        else
        {
            String detailReport = newreport.getStory(request, option);
            
            if (detailReport != null)
            {
                if (session.getAttribute("role") != null){
                    
                    
                    if (session.getAttribute("role").toString().
                            trim().equals("subscriber"))
                    {
                        String userid = session.getAttribute("currentUserID").toString().
                                trim();
                        
                        if (news.readFavourites(userid, option))
                        {
                            request.setAttribute("favorOption", "true");
                        }
                        else
                        {
                            request.setAttribute("favorOption", "false");
                        }
                    }
                   
                    if (session.getAttribute("currentUser") != null){
                        
                        String userid = session.getAttribute("currentUserID").toString().
                                trim();
                        String user = session.getAttribute("currentUser").toString().trim();
                        String role = signOnConfig.getRole(userid).trim();
                        
                        request.setAttribute("currentUser", user);
                        request.setAttribute("currentRole",role);
                    }
                }

                request.setAttribute("detailReport", detailReport);
                dispatch = request.getRequestDispatcher("detailStory.jsp");
                dispatch.forward(request, response);
                
            }
        }
   
    }
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String option = request.getParameter("button");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatch;
        
        boolean result = false;
        
        if (option.equals("SignUp"))
        {
            
            
            if(signOnConfig.userErrorExists(request, response))
            {
                dispatch = request.getRequestDispatcher("characterError.jsp");
                dispatch.forward(request, response);
            }
            
            result = signOnConfig.addUser(request, response);
            
            if (result == false)
            {
                dispatch = request.getRequestDispatcher("signupError.jsp");
                dispatch.forward(request, response);
                
            }
            else
            {
                dispatch = request.getRequestDispatcher("signupSuccess.jsp");
                dispatch.forward(request, response);
                
            }
            
        }
        else if(option.equals("Login"))
        {
            result = signOnConfig.loginUser(request, response);
            if (result == true)
            {
                dispatch = request.getRequestDispatcher("index");
                dispatch.forward(request, response);
            }
            else if (signOnConfig.userExists(request, response))
            {
                response.setStatus(422);
                dispatch = request.getRequestDispatcher("loginPasswordError.jsp");
                dispatch.forward(request, response);
            }
            else
            {   
                dispatch = request.getRequestDispatcher("loginUserError.jsp");
                dispatch.forward(request, response);
            }
        }
        
        
        if (option.equals("Add")){
            
            news.addStory(request);
            dispatch = request.getRequestDispatcher("index");
            dispatch.forward(request, response);
            
        }
        else if(option.equals("Update"))
        {
            if (news.editStory(request))
            {
                dispatch = request.getRequestDispatcher("index");
                dispatch.forward(request, response);
            }
            else
            {
                dispatch = request.getRequestDispatcher("updateError.jss");
                dispatch.forward(request, response);
            }
            
        }
        
        if(option.equals("Yes"))
        {
            
            news.deleteStory(request, session.getAttribute("deleteTitle").toString());
            dispatch = request.getRequestDispatcher("index");
            dispatch.forward(request, response);
        }
        
        if(option.equals("ok"))
        {
            String favorite = request.getParameter("user_favour");
            String title = session.getAttribute("favourTitle").toString();
            
            if (session.getAttribute("currentUserID") != null &&
                 session.getAttribute("currentUser") != null &&
                 session.getAttribute("role") != null)
            {
                String userid = session.getAttribute("currentUserID").toString().trim();
                String user = session.getAttribute("currentUser").toString().trim();
                String role = session.getAttribute("role").toString().trim();
                
                if (role.equals("subscriber"))
                {
                    if (!news.manageFavourites(userid, title, favorite))
                    {
                        dispatch = request.getRequestDispatcher("favoriteError.jsp");
                        dispatch.forward(request, response);
                    }
                }
                
                session.setAttribute("favoriteOption", favorite);
                request.setAttribute("currentUser", user);
                request.setAttribute("currentRole", role);
                
            }

            dispatch = request.getRequestDispatcher("index");
            dispatch.forward(request, response);

        }
    }
   
}
