package control;

import model.news;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name= "index", urlPatterns= "/")
public class index extends HttpServlet {

    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List <String> title= news.defaultStory(request);
        request.setAttribute("title", title);
        
        RequestDispatcher dispatch;
        dispatch = request.getRequestDispatcher("home.jsp");
        dispatch.forward(request, response);
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List <String> title= news.defaultStory(request);
        request.setAttribute("title", title);
        
        RequestDispatcher dispatch;
        dispatch = request.getRequestDispatcher("home.jsp");
        dispatch.forward(request, response);
        
    }

}
