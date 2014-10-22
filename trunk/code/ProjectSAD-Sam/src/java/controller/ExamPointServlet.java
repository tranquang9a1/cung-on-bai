/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import entity.TblSession;
import entity.TblSessionQuestion;
import entity.TblUser;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Constants;

/**
 *
 * @author computer
 */
public class ExamPointServlet extends HttpServlet {

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
        } else {
            TblSession examSession = (TblSession) request.getAttribute("examSession");
            List<TblSessionQuestion> lst = examSession.getTblSessionQuestionList();
            // canculate total point
            Map<Integer, Integer> mapPoint = canculatePoint(lst);
            int pointSum = 0;
            for (Entry entry : mapPoint.entrySet()) {
                Integer value = (Integer) entry.getValue();
                if (value > 0) {
                    pointSum += value;
                }
            }
            request.setAttribute("numberQuestion", mapPoint.size());
            double point = (double) (pointSum) / 100.00;
            request.setAttribute("point", point);
            // add point into account of user
            long pointLong = Math.round(point);
            int pointInt = (int) pointLong;
            user.setScore(user.getScore() + pointInt);
            request.getRequestDispatcher(Constants.JSP_VIEWPOINT).forward(request, response);
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

    private Map<Integer, Integer> canculatePoint(List<TblSessionQuestion> lst) {
        //create map to store point of each question
        Map<Integer, Integer> mapPoint = new HashMap<Integer, Integer>();
        for (int i = 0; i < lst.size(); i++) {
            TblSessionQuestion sessionQuestion = lst.get(i);
            mapPoint.put(sessionQuestion.getQuestionId().getQuestionId(), 0);
        }
        // canculate point for each question
        for (int i = 0; i < lst.size(); i++) {
            TblSessionQuestion sessionQuestion = lst.get(i);
            for (Entry entry : mapPoint.entrySet()) {
                Integer key = (Integer) entry.getKey();
                if (sessionQuestion.getQuestionId().getQuestionId() == key) {
                    if (sessionQuestion.getAnswerId() != null) { // check answer null or not
                        Integer value = (Integer) entry.getValue() + sessionQuestion.getAnswerId().getPoint();
                        mapPoint.put(key, value);
                    }
                }
            }
        }
        return mapPoint;
    }
}
