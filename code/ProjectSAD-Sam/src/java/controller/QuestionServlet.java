/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnswerDao;
import dao.QuestionDao;
import dao.SubjectDao;
import entity.TblQuestion;
import entity.TblSubject;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.common;

/**
 *
 * @author khangtnse60992
 */
public class QuestionServlet extends HttpServlet {

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
        // action = null redirect to 404
        if (action == null) {
            response.sendRedirect("404.html");
            return;
        }
        // Show question ( admin module)
        if (action.equals("show")) {
            SubjectDao subjectDao = new SubjectDao();
            QuestionDao questionDao = new QuestionDao();
            String subjectId = request.getParameter("subjectId");
            String pageTxt = request.getParameter("page");
            int id = 0;
            try {
                id = Integer.parseInt(subjectId);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            }
            int page = 0;
            try {
                page = Integer.parseInt(pageTxt);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            }
//            TblSubject tblSubject = subjectDao.findById(TblSubject.class,id );
            List<TblQuestion> questions = questionDao.getListQuestion(id, page, common.page);
            request.setAttribute("questions", questions);
            request.getRequestDispatcher("WEB-INF/questionAdminView.jsp").forward(request, response);
            return;
        }
        if (action.equals("showDetail")) {
            String questionId = request.getParameter("id");
            QuestionDao questionDao = new QuestionDao();
            int id = 0;
            try {
                id = Integer.parseInt(questionId);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            }
            TblQuestion question = questionDao.findById(TblQuestion.class, id);
            request.setAttribute("question", question);
            request.getRequestDispatcher("WEB-INF/questionDetailAdminView.jsp").forward(request, response);
            return;
        }
        if (action.equals("insertQuestion")) {
            String type = request.getParameter("type");
            if(type.equals("chooseSubject")) {
                SubjectDao subjectDao = new SubjectDao();
                List<TblSubject> tblSubjects = subjectDao.getListAllSubject();
                request.setAttribute("subjects", tblSubjects);
                request.getRequestDispatcher("WEB-INF/questionDetailAdminView.jsp").forward(request, response);
                return;
            }
            if(type.equals("insertPage")) {
                String subjectId = request.getParameter("subjectId");
                request.setAttribute("subjectId", subjectId);
                request.getRequestDispatcher("WEB-INF/insertQuestion.jsp").forward(request, response);
                return;
            }
            String questionContent = request.getParameter("questionContent");
            String subjectIdTxt = request.getParameter("subjectId");
            String[] answerContent = request.getParameterValues("answerContent");
            String[] pointTxt = request.getParameterValues("point");
            int[] point = null;
            QuestionDao questionDao = new QuestionDao();
            SubjectDao subjectDao = new SubjectDao();
            AnswerDao answerDao = new AnswerDao();
            int subjectId = 0;
            try {
                subjectId = Integer.parseInt(subjectIdTxt);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            }
            // get Subject
            TblSubject tblSubject = subjectDao.findById(TblSubject.class, subjectId);
            TblQuestion tblQuestion = new TblQuestion();
            tblQuestion.setSubjectId(tblSubject);
            tblQuestion.setContent(questionContent);
            // insert Question and return question
            point = new int[pointTxt.length];
            for (int i = 0; i < pointTxt.length; i++) {
                try {
                    point[i] = Integer.parseInt(pointTxt[i]);
                } catch (NumberFormatException e) {
                    response.sendRedirect(common.errorPage);
                    return;
                } 
            }
            tblQuestion = questionDao.insert(tblQuestion);
            answerDao.insertAnswer(point, answerContent, tblQuestion);
            response.sendRedirect("QuestionServlet?action=showDetail&id="+tblQuestion.getQuestionId());
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
