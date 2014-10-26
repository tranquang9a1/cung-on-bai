/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import entity.TblUser;
import utils.CMSLoginChecker;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Constants;

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
            if (action.equals("login")) {
                doLogin(request, response);
            } else if (action.equals("logout")) {
                doLogout(request, response);
            } else {
                // No action specific, redirect to login page
                response.sendRedirect(Constants.URL_USER);
            }
        } else {
            // Default page, show login form


            HttpSession session = request.getSession(true);
            // Check if user is already logged in, redirect to home page
            TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
            if (user != null) {
                response.sendRedirect(Constants.URL_HOME);
                return;
            }

            request.getRequestDispatcher(Constants.JSP_LOGIN)
                    .forward(request, response);
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

    private void doLogin(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        // If this line is reached, user is not logged in yet
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = "";
        if (username == null || username.equals("")) {
            message += "Vui lòng nhập tài khoản CMS!<br/>";
        }
        if (password == null || password.equals("")) {
            message += "Vui lòng nhập mật khẩu!<br/>";
        }
        CMSLoginChecker checker = CMSLoginChecker.getInstance();
        if (message.equals("") && !checker.checkLogin(username, password)) {
            // Login failed
            message += "Sai tài khoản hoặc mật khẩu!<br/>";
        }

        if (message.equals("")) {
            // Login success

            // Check if user is already exists in database or not
            UserDao dao = new UserDao();
            TblUser user = dao.getUserByUsername(username);

            // If user is not exists yet, create new
            if (user == null) {
                user = new TblUser(username, 0, 0);
                dao.insert(user);
            }

            // Set user to session
            session.setAttribute(Constants.VAR_SESSION_USER, user);
            response.sendRedirect(Constants.URL_HOME);
        } else {
            // Something wrong
            request.setAttribute("errorMessage", message);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher(Constants.JSP_LOGIN)
                    .forward(request, response);
        }
    }

    private void doLogout(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(Constants.URL_USER);
    }
}
