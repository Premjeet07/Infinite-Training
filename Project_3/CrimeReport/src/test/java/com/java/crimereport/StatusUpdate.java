package com.java.crimereport;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.java.Dao.CrimeDao;
import com.java.Dao.CrimeDaoImpl;

public class StatusUpdate {

    private CrimeDao dao;

    @Before
    public void setUp() {
        dao = new CrimeDaoImpl();
    }

    @Test
    public void testUpdateCrimeCaseStatus_ValidIdAndStatus() throws SQLException, ClassNotFoundException {
        boolean result = dao.updateCrimeCaseStatus(101, "Open");
        assertTrue("Status should be updated for a valid incident ID", result);
    }

    @Test
    public void testUpdateCrimeCaseStatus_InvalidIncidentId() throws SQLException, ClassNotFoundException {
        boolean result = dao.updateCrimeCaseStatus(9999, "Closed");
        assertFalse("Update should fail for a non-existent incident ID", result);
    }

    @Test
    public void testUpdateCrimeCaseStatus_InvalidStatus() throws SQLException, ClassNotFoundException {
        boolean result = dao.updateCrimeCaseStatus(101, "InvalidStatus");
        assertTrue("If the DB accepts invalid statuses, this will return true (you might need validation)", result);
    }

    @Test
    public void testUpdateCrimeCaseStatus_NullStatus() throws SQLException, ClassNotFoundException {
        boolean result = dao.updateCrimeCaseStatus(101, null);
        assertFalse("Update should fail for a null status", result);
    }
}
