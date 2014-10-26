/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import entity.TblUser;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author DuanLA
 */
public class UserComparator implements Comparator<TblUser> {

    @Override
    public int compare(TblUser username1, TblUser username2) {
        int compare = username1.getUsername().compareTo(username2.getUsername());
        return compare;
    }
}
