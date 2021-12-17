package com.bsu.project.data;

import com.bsu.project.entity.Ad;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Gulshirin Berdiyeva
 */
public interface Reader {
    void read(Ad ad, HttpServletResponse response) throws DataException;
}
