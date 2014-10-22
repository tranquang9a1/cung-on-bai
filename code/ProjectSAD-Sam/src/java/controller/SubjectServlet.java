/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SubjectDao;
import entity.TblSubject;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Constants;

/**
 *
 * @author Huydt
 */
@WebServlet(name = "SubjectServlet", urlPatterns = {"/SubjectServlet"})
public class SubjectServlet extends HttpServlet {
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            if (action.equals("create")) {
                createNewSubject(request, response);
            } else if (action.equals("delete")) {
                deleteSubject(request, response);
            } else {
                // Redirect to default page
                response.sendRedirect(Constants.URL_SUBJECT);
            }
        } else {
            // List all subject
            listAllSubject(request, response);
        }
    }
    
    private void deleteSubject(HttpServletRequest request, 
            HttpServletResponse response) throws IOException {

        String subjectId = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(subjectId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id != 0) {
            SubjectDao dao = new SubjectDao();
            TblSubject subject = dao.findBySubjectId(id);
            dao.remove(subject);
        }
        response.sendRedirect(Constants.URL_SUBJECT);
    }
    
   
    
    private void listAllSubject(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        SubjectDao dao = new SubjectDao();
        List<TblSubject> subjects = dao.getListAllSubject();
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher(Constants.JSP_CREATESUBJECT).forward(request, response);
    }
    
    
    private void createNewSubject (HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        SubjectDao dao = new SubjectDao();
        
        
        String subjectName = request.getParameter("subjectName");
        String message = "";
        if (subjectName == null || subjectName.equals("")) {
            message += "Vui lòng nhập tên môn học.";
        }
        if (message.equals("")) {
            TblSubject subject = new TblSubject();
            subject.setSubjectName(subjectName);
            dao.insert(subject);
            response.sendRedirect(Constants.URL_SUBJECT);
        } else {
            request.setAttribute("message", message);
            request.setAttribute("backUrl", Constants.URL_SUBJECT);
            request.getRequestDispatcher(Constants.JSP_ERROR)
                    .forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

