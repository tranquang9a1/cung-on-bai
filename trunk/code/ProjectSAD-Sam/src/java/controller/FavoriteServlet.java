/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FavoriteDao;
import dao.UserDao;
import entity.TblFavorite;
import entity.TblUser;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Constants;

/**
 *
 * @author Huydt
 */
public class FavoriteServlet extends HttpServlet {

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

        HttpSession session = request.getSession(true);
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        if (user == null) {
            response.sendRedirect(Constants.URL_USER);
        }
        if (user != null) {
            UserDao daoUser = new UserDao();
            // User information from session
            request.setAttribute("user", daoUser.findById(TblUser.class, user.getUserId()));
            
            FavoriteDao dao = new FavoriteDao();
            String action = request.getParameter("action");
            if (action == null || action.isEmpty()) {
                //list all favorite question of user
                int userId = user.getUserId();
                List<TblFavorite> lst = dao.findByUserId(userId);
                request.setAttribute("lstFavorite", lst);
                request.getRequestDispatcher(Constants.JSP_VIEWFAVORITEQUESTION).forward(request, response);
            } else if (action.equals("delete")) {
                //delete favorite list
                String[] lstFavoriteId = request.getParameterValues("favorite");
                if (lstFavoriteId != null && lstFavoriteId.length > 0) {
                    dao.removeFavoriteQuestion(lstFavoriteId);
                }
                response.sendRedirect(Constants.ULR_FAVORITE);
            }
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
}
