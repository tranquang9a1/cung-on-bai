/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnswerDao;
import dao.QuestionDao;
import dao.SessionDao;
import dao.SessionQuestionDao;
import dao.SubjectDao;
import entity.TblAnswer;
import entity.TblQuestion;
import entity.TblSession;
import entity.TblSessionQuestion;
import entity.TblSubject;
import entity.TblUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Constants;
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
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        if (user == null) {
            response.sendRedirect(Constants.URL_USER);
        }
        if (user != null) {
            String action = request.getParameter("action");
            if (action == null || action.isEmpty()) {
//                SubjectDao daoSubject = new SubjectDao();
//                QuestionDao daoQuestion = new QuestionDao();
//                List<TblSubject> lstSubject = daoSubject.getListAllSubject();
//                int[] lstNumberOfQuestion = new int[lstSubject.size()];
//                for (int i = 0; i < lstSubject.size(); i++) {
//                    int subjectId = lstSubject.get(i).getSubjectId();
//                    List<TblQuestion> lst = daoQuestion.findBySubjectId(subjectId);
//                    if (lst == null || lst.isEmpty()) {
//                        lstNumberOfQuestion[i] = 0;
//                    } else {
//                        lstNumberOfQuestion[i] = lst.size();
//                    }
//                }
//                request.setAttribute("lstNumberOfSubject", lstNumberOfQuestion);
//                request.setAttribute("lstSubject", lstSubject);
//                request.getRequestDispatcher("WEB-INF/exam/exam.jsp").
//                        forward(request, response);

                response.sendRedirect(Constants.URL_HOME);
            }
            if (action != null && !action.isEmpty()) {
                if (action.equals("start")) {
                    start(request, response);
                    request.getRequestDispatcher(Constants.JSP_EXAMSESSION).
                            forward(request, response);
                } // end of if action start
                if (action.equals("submit")) {
                    submit(request, response);
                    request.getRequestDispatcher(Constants.URL_POINT).
                            forward(request, response);
                } // end of if action submit
            }
        } // end of else check user
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

    private void start(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        if (user != null) {
            // User information from session
            request.setAttribute("user", session.getAttribute(Constants.VAR_SESSION_USER));

            String stringSubject = request.getParameter("subject");
            String question = request.getParameter("numberQuestion");
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
            session.setAttribute("lstQuestion", lstQuestion);
            List<String> lstType = ExamUtils.checkTypeQuestion(lstQuestion);
            long startTime = new Date().getTime();
            request.setAttribute("startTime", startTime);
            request.setAttribute("lstType", lstType);
        } else {
            response.sendRedirect(Constants.URL_USER);
        }
    }

    private void submit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        String stringStartTime = request.getParameter("startTime");
        List<TblQuestion> lstQuestion =
                (List<TblQuestion>) session.getAttribute("lstQuestion");
        long startTime = 0;
        try {
            startTime = Long.parseLong(stringStartTime);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        long endTime = date.getTime();
        //create new Session
        TblSubject subject = lstQuestion.get(0).getSubjectId();
        int start = (int) (startTime / 1000L);
        int end = (int) (endTime / 1000L);
        SessionDao daoSession = new SessionDao();
        TblSession examSession = new TblSession(start, end, subject, user);
        daoSession.insert(examSession);
        //create list sesion-question
        List<TblSessionQuestion> lst = new ArrayList<TblSessionQuestion>();
        for (TblQuestion question : lstQuestion) {
            // set list of question, answer for session
            String[] lstanswerID = request.getParameterValues(question.getQuestionId().toString());
            if (lstanswerID == null || lstanswerID.length == 0) {
                TblAnswer answer = null;
                TblSessionQuestion session_question = new TblSessionQuestion();
                session_question.setSessionId(examSession);
                session_question.setQuestionId(question);
                session_question.setAnswerId(answer);
                SessionQuestionDao sessionQuestionDao = new SessionQuestionDao();
                sessionQuestionDao.insert(session_question);
                lst.add(session_question);
            } else {
                for (int i = 0; i < lstanswerID.length; i++) {
                    AnswerDao daoAnswer = new AnswerDao();
                    TblAnswer answer = daoAnswer.findById(TblAnswer.class, Integer.parseInt(lstanswerID[i]));
                    TblSessionQuestion session_question = new TblSessionQuestion();
                    session_question.setSessionId(examSession);
                    session_question.setQuestionId(question);
                    session_question.setAnswerId(answer);
                    SessionQuestionDao sessionQuestionDao = new SessionQuestionDao();
                    sessionQuestionDao.insert(session_question);
                    lst.add(session_question);
                }
            }
        } // end of for add answer
        examSession.setTblSessionQuestionList(lst);
        request.setAttribute("examSession", examSession);
    }
}
