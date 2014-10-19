/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import entity.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinhquangtrung
 */
public class UserServlet extends HttpServlet {

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

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("create")) {
                createNewUser(request, response);
            } else if (action.equals("delete")) {
                deleteUser(request, response);
            } else if (action.equals("setadmin")) {
                setAdmin(request, response, 1);
            } else if (action.equals("removeadmin")) {
                setAdmin(request, response, 0);
                   
            } else {
                // Redirect to default page
                response.sendRedirect("user");
            }
        } else {
            // List all user
            listAllUser(request, response);
        }
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

    private void createNewUser(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        UserDao dao = new UserDao();

        String username = request.getParameter("username");
        String message = "";
        if (username == null || username.equals("")) {
            message += "Vui lòng nhập tên tài khoản.";
        }
        if (message.equals("")) {
            dao.insert(new TblUser(username, 0, 0));
            response.sendRedirect("user");
        } else {
            request.setAttribute("message", message);
            request.setAttribute("backUrl", "user");
            request.getRequestDispatcher("WEB-INF/common/error.jsp")
                    .forward(request, response);
        }
    }

    private void listAllUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();

        List<TblUser> users = dao.getAllUser();
        request.setAttribute("users", users);
        request.getRequestDispatcher("WEB-INF/user/index.jsp")
                .forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, 
            HttpServletResponse response) throws IOException {

        String userId = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id != 0) {
            UserDao dao = new UserDao();
            TblUser user = dao.findById(TblUser.class, id);
            dao.remove(user);
        }
        response.sendRedirect("user");
    }

    private void setAdmin(HttpServletRequest request, 
            HttpServletResponse response, int isAdmin) throws IOException {
        
        String userId = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id != 0) {
            UserDao dao = new UserDao();
            TblUser user = dao.findById(TblUser.class, id);
            user.setIsAdmin(isAdmin);
        }
        response.sendRedirect("user");
    }
}
