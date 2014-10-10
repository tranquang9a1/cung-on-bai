/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TestClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author khangtnse60992
 */
public class BaseDao {

    public Connection openConnection() {
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/SADDB");
            Connection con = ds.getConnection();
            return con;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TestClass> getAllTest() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TestClass> listTest = new ArrayList<TestClass>();
        try {
            con = openConnection();
            ps = con.prepareStatement("select * from testSAD ");
            rs = ps.executeQuery();
            int id = 0;
            String nameTest = "";
            TestClass testClass = null;
            while (rs.next()) {
                id = rs.getInt("id");
                nameTest = rs.getString("testValue");
                testClass = new TestClass(id, nameTest);
                listTest.add(testClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listTest;
    }
}
