/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import entity.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Constants;
import helper.PointComparator;
import helper.UserComparator;

/**
 *
 * @author DuanLA
 */
@WebServlet(name = "StatServlet", urlPatterns = {"/stat"})
public class StatServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listAllUser(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listAllUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        if (user != null) {
            // User information from session
            UserDao dao = new UserDao();
            // User information from session
            request.setAttribute("user", dao.findById(TblUser.class, user.getUserId()));
            String action = request.getParameter("action");
            if (action == null){
               action = "point";
            }
            List<TblUser> users = dao.getAllUser();
            Comparator comparator = null;
            if (action.equals("point")) {
                comparator = new PointComparator();
            } else if (action.equals("user")) {
                comparator = new UserComparator();
            }
            Collections.sort(users, comparator);
            request.setAttribute("users", users);
            request.setAttribute("title", "Thống kê");
            request.getRequestDispatcher(Constants.JSP_STAT)
                    .forward(request, response);
        } else {
            response.sendRedirect(Constants.URL_USER);
        }
    }

    private void viewDetail(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        TblUser users = dao.getUserByUsername(null);
        request.setAttribute(null, dao);
        request.getRequestDispatcher(Constants.VAR_SESSION_USER).forward(request, response);
    }
}
