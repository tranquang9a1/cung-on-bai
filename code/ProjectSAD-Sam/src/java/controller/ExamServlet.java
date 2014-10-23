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
import entity.DecoratorQuestion;
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
                    // after submit, remove lstDecorator of session
                    session.removeAttribute("lstDecorator");
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
        request.setAttribute("user", session.getAttribute(Constants.VAR_SESSION_USER));
        String stringSubject = request.getParameter("subject");
        String stringQuestion = request.getParameter("numberQuestion");
        if (stringSubject == null || stringSubject.isEmpty()
                && stringQuestion == null || stringQuestion.isEmpty()) {
            request.setAttribute(Constants.CONST_MESSAGE, Constants.MESSAGE_WRONGINPUT );
            request.setAttribute(Constants.CONST_BACKURL, Constants.JSP_ERROR);
        } else {
        int numberQuestion = 0;
        int subjectId = 0;
        try {
            numberQuestion = Integer.parseInt(stringQuestion);
            subjectId = Integer.parseInt(stringSubject);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        QuestionDao daoQuestion = new QuestionDao();
        //random list of question
        List<TblQuestion> lstQuestion =
                daoQuestion.getListRandom(numberQuestion, subjectId);
        List<String> lstType = ExamUtils.checkTypeQuestion(lstQuestion);
        //user decorator partern
        List<DecoratorQuestion> lstDecorator = new ArrayList<DecoratorQuestion>();
        for (int i = 0; i < lstQuestion.size(); i++) {
            TblQuestion question = lstQuestion.get(i);
            String type = lstType.get(i);
            DecoratorQuestion decoratorQuestion = new DecoratorQuestion(type, question);
            lstDecorator.add(decoratorQuestion);
        }
        session.setAttribute("lstDecorator", lstDecorator);
        long startTime = new Date().getTime();
        request.setAttribute("startTime", startTime);
        request.setAttribute("lstDecorator", lstDecorator);
        }
       
    }

    private void submit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        request.setAttribute("user", session.getAttribute(Constants.VAR_SESSION_USER));
        String stringStartTime = request.getParameter("startTime");
        List<DecoratorQuestion> lstQuestion =
                (List<DecoratorQuestion>) session.getAttribute("lstDecorator");
        long startTime = 0;
        try {
            startTime = Long.parseLong(stringStartTime);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        long endTime = date.getTime();
        //create new Session
        TblSubject subject = lstQuestion.get(0).getQuestion().getSubjectId();
        int start = (int) (startTime / 1000L);
        int end = (int) (endTime / 1000L);
        SessionDao daoSession = new SessionDao();
        TblSession examSession = new TblSession(start, end, subject, user);
        daoSession.insert(examSession);
        //create list sesion-question
        List<TblSessionQuestion> lst = new ArrayList<TblSessionQuestion>();
        for (DecoratorQuestion decoratorQuestion : lstQuestion) {
            // set list of question, answer for session
            TblQuestion question = decoratorQuestion.getQuestion();
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
