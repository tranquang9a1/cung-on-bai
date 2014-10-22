/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MessageDao;
import entity.TblMessage;
import entity.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import utils.Constants;

/**
 *
 * @author dinhquangtrung
 */
public class MessageServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("action");
            if (action != null && !action.isEmpty()) {
                if (action.equals("list")) {
                    int from = 0;
                    try {
                        from = Integer.parseInt(request.getParameter("from"));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    MessageDao dao = new MessageDao();
                    List<TblMessage> allMessage = dao.getAllMessage(from);
                    StringBuilder result = new StringBuilder();
                    for (TblMessage msg : allMessage) {
                        result.append("<b title='")
                                .append(msg.getFromUsername())
                                .append("'>")
                                .append(msg.getFromUsername())
                                .append(":</b> ")
                                .append(msg.getContent())
                                .append("<br/>");
                    }
                    JSONObject obj = new JSONObject();
                    if (allMessage.size() > 0) {
                        obj.put("success", 1);
                    } else {
                        obj.put("success", 0);
                    }
                    obj.put("content", result.toString());
                    obj.put("lastMsg", (new Date()).getTime() / 1000);
                    out.print(obj.toJSONString());
                } else if (action.equals("add")) {
                    HttpSession session = request.getSession(true);
                    TblUser user = (TblUser) session.getAttribute(Constants.VAR_SESSION_USER);
                    String content = request.getParameter("content");
                    if (user != null && content != null && !content.isEmpty()) {
                        TblMessage msg = new TblMessage();
                        msg.setContent(content);
                        msg.setFromUsername(user.getUsername());
                        msg.setCreatedDate((int) ((new Date()).getTime() / 1000));
                        MessageDao dao = new MessageDao();
                        dao.insert(msg);
                        out.println(1);
                    } else {
                        // Not logged in!
                        out.println(0);
                    }
                }
            }

        } finally {
            out.close();
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
