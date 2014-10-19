/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDao;
import dao.SubjectDao;
import entity.TblQuestion;
import entity.TblSubject;
import entity.TblUser;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.ExamUtils;

/**
 *
 * @author computer
 */
public class ExamServlet extends HttpServlet {

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
        TblUser user = (TblUser) session.getAttribute("user"); //TODO: check login or not
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            SubjectDao daoSubject = new SubjectDao();
            List<TblSubject> lstSubject = daoSubject.getListAllSubject();
            request.setAttribute("lstSubject", lstSubject);
            request.getRequestDispatcher("WEB-INF/exam/exam.jsp").
                    forward(request, response);
        } else {
            if (action.equals("start")) {
                String stringSubject = request.getParameter("subject");
                String question = request.getParameter("numberQuestion");
                String isFavoriteQS = request.getParameter("isFavoriteQS");
                int numberQuestion = 0;
                int subjectId = 0;
                try {
                    numberQuestion = Integer.parseInt(question);
                    subjectId = Integer.parseInt(stringSubject);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                QuestionDao daoQuestion = new QuestionDao();
                List<TblQuestion> lstQuestion =
                        daoQuestion.getListRandom(numberQuestion, subjectId);
                List<String> lstType = ExamUtils.checkTypeQuestion(lstQuestion);
                request.setAttribute("lstQuestion", lstQuestion);
                request.setAttribute("lstType", lstType);
                request.getRequestDispatcher("WEB-INF/exam/examsession.jsp").
                        forward(request, response);

            } else {
                if (action.equals("submit")) {
                    Date startTime;
                    Date date = new Date();
//                    Date start = date.getTime();
                    List<TblQuestion> lstQuestion = (List<TblQuestion>) request.getAttribute("lstQuestion");
                    //create new Session
                    int userId = user.getUserId();
                    int subjectId = lstQuestion.get(0).getSubjectId().getSubjectId();
                    
                }

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
