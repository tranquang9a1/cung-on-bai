/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnswerDao;
import dao.QuestionDao;
import dao.SubjectDao;
import entity.TblAnswer;
import entity.TblQuestion;
import entity.TblSubject;
import entity.TblUser;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Constants;
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
        if(authen(request, response)==1) {
            response.sendRedirect(Constants.URL_USER);
            return;
        }
        String action = request.getParameter("action");
        // action = null redirect to 404
        if (action == null) {
            response.sendRedirect(Constants.JSP_ERROR);
            return;
        }
        // Show question ( admin module)
        if (action.equals("show")) {
            //show module
            show(request, response);

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
            request.getRequestDispatcher(Constants.JSP_QUESTIONDETAILADMINVIEW).forward(request, response);
            return;
        }
        if (action.equals("insertQuestion")) {
            // insertQuestion module
            insert(request, response);


        }
        if (action.equals("updateQuestion")) {
            update(request, response);

        }
        if (action.equals("deleteQuestion")) {
            String subjectId = request.getParameter("subjectId");
            String questionId = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(questionId);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            } catch (NullPointerException e) {
                response.sendRedirect(common.errorPage);
                return;
            }

            QuestionDao questionDao = new QuestionDao();
            questionDao.remove(questionDao.findById(TblQuestion.class, id));
            response.sendRedirect("QuestionServlet?action=show&page=0&subjectId=" + subjectId);
            return;
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

    protected void show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) {
            response.sendRedirect(Constants.JSP_ERROR);
            return;
        }
        if (type.equals("chooseSubject")) {
            SubjectDao subjectDao = new SubjectDao();
            List<TblSubject> tblSubjects = subjectDao.getListAllSubject();
            request.setAttribute("subjects", tblSubjects);
            request.getRequestDispatcher(Constants.JSP_CHOOSESUBJECT).forward(request, response);
            return;
        }
        if (type.equals("viewPage")) {

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
            int count = (int) Math.ceil(  questionDao.getCountListQuestion(id) * 1.0/ common.page);
            request.setAttribute("questions", questions);
            request.setAttribute("number", count);
            request.getRequestDispatcher(Constants.JSP_QUESTIONADMINVIEW).forward(request, response);
            return;
        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) {
            response.sendRedirect(Constants.JSP_ERROR);
            return;
        }
//            if (type.equals("chooseSubject")) {
//                SubjectDao subjectDao = new SubjectDao();
//                List<TblSubject> tblSubjects = subjectDao.getListAllSubject();
//                request.setAttribute("subjects", tblSubjects);
//                request.getRequestDispatcher("WEB-INF/chooseSubject.jsp").forward(request, response);
//                return;
//            }
        if (type.equals("viewPage")) {
            SubjectDao subjectDao = new SubjectDao();
            String subjectId = request.getParameter("subjectId");
            int id = 0;
            try {
                id = Integer.parseInt(subjectId);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
            }
            TblSubject subject = subjectDao.findById(TblSubject.class, id);
            request.setAttribute("subject", subject);
            request.getRequestDispatcher(Constants.JSP_INSERTQUESTION).forward(request, response);
//                response.sendRedirect("WEB-INF/insertQuestion.jsp?subjectId="+subjectId+"&subjectName="+subject.getSubjectName());
            return;
        }
        if (type.equals("persit")) {
            StringBuffer questionContent = new StringBuffer(request.getParameter("questionContent"));
            String subjectIdTxt = request.getParameter("subjectId");
            String[] answerContent = request.getParameterValues("answerContent");
            String[] pointTxt = request.getParameterValues("point");
            QuestionDao questionDao = new QuestionDao();
            SubjectDao subjectDao = new SubjectDao();
            AnswerDao answerDao = new AnswerDao();
            int subjectId = 0;
            try {
                subjectId = Integer.parseInt(subjectIdTxt);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            } catch (NullPointerException e) {
                response.sendRedirect(common.errorPage);
                return;
            }

            int loc = (new String(questionContent)).indexOf('\n');
            while (loc > 0) {
                questionContent.replace(loc, loc + 1, "<BR>");
                loc = (new String(questionContent)).indexOf('\n');
            }
            // get Subject
            TblSubject tblSubject = subjectDao.findById(TblSubject.class, subjectId);
            TblQuestion tblQuestion = new TblQuestion();
            tblQuestion.setSubjectId(tblSubject);
            tblQuestion.setContent(questionContent.toString());
            List<TblAnswer> tblAnswers = answerDao.getListAnswer(pointTxt, answerContent, tblQuestion);
            tblQuestion.setTblAnswerList(tblAnswers);
            // insert Question and return question
            tblQuestion = questionDao.insert(tblQuestion);
//                answerDao.insertAnswer(point, answerContent, tblQuestion);
            response.sendRedirect("QuestionServlet?action=showDetail&id=" + tblQuestion.getQuestionId());
            return;
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) {
            response.sendRedirect(common.errorPage);
            return;
        }
        if (type.equals("viewUpdatePage")) {
            String questionId = request.getParameter("questionId");
            int id = 0;
            try {
                id = Integer.parseInt(questionId);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            } catch (NullPointerException e) {
                response.sendRedirect(common.errorPage);
                return;
            }
            QuestionDao questionDao = new QuestionDao();
            TblQuestion tblQuestion = questionDao.findById(TblQuestion.class, id);
            request.setAttribute("question", tblQuestion);
            request.getRequestDispatcher(Constants.JSP_UPDATEQUESTION).forward(request, response);
            return;
        }
        if (type.equals("mergeQuestion")) {
            String questionId = request.getParameter("questionId");
            StringBuffer questionContent = new StringBuffer(request.getParameter("questionContent"));
            String[] answerContent = request.getParameterValues("answerContent");
            String[] pointTxt = request.getParameterValues("point");
            String[] answerIdTxt = request.getParameterValues("answerId");
            int id = 0;
//                int[] point;
            int[] answerId;
            try {
                id = Integer.parseInt(questionId);
            } catch (NumberFormatException e) {
                response.sendRedirect(common.errorPage);
                return;
            } catch (NullPointerException e) {
                response.sendRedirect(common.errorPage);
                return;
            }

            answerId = new int[answerIdTxt.length];
            for (int i = 0; i < answerIdTxt.length; i++) {
                try {
                    answerId[i] = Integer.parseInt(answerIdTxt[i]);
                } catch (NumberFormatException e) {
                    response.sendRedirect(common.errorPage);
                    return;
                } catch (NullPointerException e) {
                    response.sendRedirect(common.errorPage);
                    return;
                }
            }
            int loc = (new String(questionContent)).indexOf('\n');
            while (loc > 0) {
                questionContent.replace(loc, loc + 1, "<BR>");
                loc = (new String(questionContent)).indexOf('\n');
            }
            QuestionDao questionDao = new QuestionDao();
            AnswerDao answerDao = new AnswerDao();
            TblQuestion tblQuestion = questionDao.findById(TblQuestion.class, id);
            tblQuestion.setContent(questionContent.toString());
            List<TblAnswer> tblAnswers = answerDao.getListAnswer(answerId, pointTxt, answerContent, tblQuestion);
            tblQuestion.setTblAnswerList(tblAnswers);
            tblQuestion = questionDao.update(tblQuestion);
            //answerDao.updateAnswer(answerId, point, answerContent, tblQuestion);
            response.sendRedirect("QuestionServlet?action=showDetail&id=" + tblQuestion.getQuestionId());
            return;
        }
    }
    
    protected int authen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
        if(user==null) {
            return 1;
        } 
        if(user.getIsAdmin()!=1) {
            return 1;
        }
        return 0;
    }
}
