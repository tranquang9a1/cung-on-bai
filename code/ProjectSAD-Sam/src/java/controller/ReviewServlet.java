/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SessionDao;
import dao.SessionQuestionDao;
import entity.DecoratorQuestion;
import entity.TblQuestion;
import entity.TblSession;
import entity.TblSessionQuestion;
import entity.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Constants;
import utils.ExamUtils;

/**
 *
 * @author DuanLA
 */
@WebServlet(name = "ReviewServlet", urlPatterns = {"/review"})
public class ReviewServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        // Check if user is already logged in, redirect to home page
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        if (user != null) {
            request.setAttribute("user", session.getAttribute(Constants.VAR_SESSION_USER));
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("viewDetail")) {
                    viewDetail(request, response);
                }
            } else {
                listSessionByUserId(request, response, user.getUserId());
            }
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

    private void listSessionByUserId(HttpServletRequest request,
            HttpServletResponse response, int userId) throws ServletException, IOException {
        SessionDao dao = new SessionDao();
        List<TblSession> sessions = dao.getSessionByUserId(userId);
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher(Constants.JSP_REVIEW)
                .forward(request, response);
    }

    private void viewDetail(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(sessionId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id != 0) {
            SessionDao dao = new SessionDao();
            TblSession session = dao.findById(TblSession.class, id);
            List<TblSessionQuestion> sessionQuestion = session.getTblSessionQuestionList();
            ArrayList<TblQuestion> questions = new ArrayList<TblQuestion>();
            
            for (int i = 0; i < sessionQuestion.size(); i++) {
                questions.add(sessionQuestion.get(i).getQuestionId());
            }
            List<String> lstType = ExamUtils.checkTypeQuestion(questions);
            List<DecoratorQuestion> lstDecorator = new ArrayList<DecoratorQuestion>();
            for (int i = 0; i < questions.size(); i++) {
                TblQuestion question = questions.get(i);
                String type = lstType.get(i);
                DecoratorQuestion decoratorQuestion = new DecoratorQuestion(type, question, 
                        sessionQuestion.get(i).getAnswerId());
                lstDecorator.add(decoratorQuestion);
            }
            request.setAttribute("lstDecorator", lstDecorator);
            request.getRequestDispatcher(Constants.JSP_REVIEWEXAM)
                    .forward(request, response);
        }
    }
}
