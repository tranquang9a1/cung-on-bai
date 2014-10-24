/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.TblUser;
import java.util.Comparator;

/**
 *
 * @author DuanLA
 */
public class PointComparator implements Comparator<TblUser> {

    @Override
    public int compare(TblUser user1, TblUser user2) {
        if (user1.getScore() < user2.getScore()) {
            return 1;
        } else {
            if (user1.getScore() == user2.getScore()) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
