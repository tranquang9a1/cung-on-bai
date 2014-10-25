/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDao;
import dao.SubjectDao;
import dao.UserDao;
import entity.TblQuestion;
import entity.TblSubject;
import entity.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class HomeServlet extends HttpServlet {

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
        if (user != null) {
            UserDao dao = new UserDao();
            // User information from session
            request.setAttribute("user", dao.findById(TblUser.class, user.getUserId()));

            // Get subjects
            SubjectDao daoSubject = new SubjectDao();
            QuestionDao daoQuestion = new QuestionDao();
            List<TblSubject> lstSubject = daoSubject.getListAllSubject();
            int[] lstNumberOfQuestion = new int[lstSubject.size()];
            for (int i = 0; i < lstSubject.size(); i++) {
                int subjectId = lstSubject.get(i).getSubjectId();
                List<TblQuestion> lst = daoQuestion.findBySubjectId(subjectId);
                if (lst == null || lst.isEmpty()) {
                    lstNumberOfQuestion[i] = 0;
                } else {
                    lstNumberOfQuestion[i] = lst.size();
                }
            }
            request.setAttribute("lstNumberOfSubject", lstNumberOfQuestion);
            request.setAttribute("lstSubject", lstSubject);

            request.getRequestDispatcher(Constants.JSP_HOME)
                    .forward(request, response);
        } else {
            response.sendRedirect(Constants.URL_USER);
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
