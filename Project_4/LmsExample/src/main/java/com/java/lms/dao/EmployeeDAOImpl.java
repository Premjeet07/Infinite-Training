package com.java.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.lms.model.Employee;
import com.java.lms.model.LeaveHistory;
import com.java.lms.util.ConnectionHelper;

public class EmployeeDAOImpl implements EmployeeDAO {

    Connection connection;
    PreparedStatement pst;

    @Override
    public List<Employee> showEmployeeDao() throws ClassNotFoundException, SQLException {
        connection = ConnectionHelper.getConnection();
        String cmd = "select * from Employee";
        pst = connection.prepareStatement(cmd);
        ResultSet rs = pst.executeQuery();
        Employee employee = null;
        List<Employee> employList = new ArrayList<Employee>();
        while (rs.next()) {
            employee = new Employee();
            employee.setEmpId(rs.getInt("EMP_ID"));
            employee.setEmpName(rs.getString("Emp_Name"));
            employee.setEmpEmail(rs.getString("EMP_MAIL"));
            employee.setDept(rs.getString("EMP_DEPT"));
            employee.setMobileNo(rs.getString("EMP_MOB_NO"));
            employee.setDateOfJoin(rs.getDate("EMP_DT_JOIN"));
            employee.setManagerId(rs.getInt("EMP_MANAGER_ID"));
            employee.setLeaveAvail(rs.getInt("EMP_AVAIL_LEAVE_BAL"));
            employList.add(employee);
        }
        return employList;
    }

    @Override
    public Employee searchEmployeeDao(int empId) throws ClassNotFoundException, SQLException {
        connection = ConnectionHelper.getConnection();
        String cmd = "select * from Employee where EMP_ID=?";
        pst = connection.prepareStatement(cmd);
        pst.setInt(1, empId);
        ResultSet rs = pst.executeQuery();
        Employee employee = null;
        if (rs.next()) {
            employee = new Employee();
            employee.setEmpId(rs.getInt("EMP_ID"));
            employee.setEmpName(rs.getString("Emp_Name"));
            employee.setDept(rs.getString("EMP_DEPT"));
            employee.setEmpEmail(rs.getString("EMP_MAIL"));
            employee.setMobileNo(rs.getString("EMP_MOB_NO"));
            employee.setDateOfJoin(rs.getDate("EMP_DT_JOIN"));
            employee.setManagerId(rs.getInt("EMP_MANAGER_ID"));
            employee.setLeaveAvail(rs.getInt("EMP_AVAIL_LEAVE_BAL"));
        }
        return employee;
    }

    @Override
    public List<LeaveHistory> getLeaveHistory(int empId) throws SQLException, ClassNotFoundException {
        List<LeaveHistory> leaveHistoryList = new ArrayList<>();
        connection = ConnectionHelper.getConnection();

        String sql = "SELECT * FROM leave_history WHERE EMP_ID = ?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setInt(1, empId);

        ResultSet rs = psmt.executeQuery();

        while (rs.next()) {
            LeaveHistory leave = new LeaveHistory();
            leave.setLeaveId(rs.getInt("LEAVE_ID"));
            leave.setEmpId(rs.getInt("EMP_ID"));
            leave.setStartDate(rs.getDate("LEAVE_START_DATE"));
            leave.setEndDate(rs.getDate("LEAVE_END_DATE"));
            leave.setLeaveType(rs.getString("LEAVE_TYPE"));
            leave.setLeaveStatus(rs.getString("LEAVE_STATUS"));
            leave.setLeaveReason(rs.getString("LEAVE_REASON"));
            leave.setMngrComments(rs.getString("LEAVE_MNGR_COMMENTS"));
            leaveHistoryList.add(leave);
        }

        return leaveHistoryList;
    }

    // ✅ NEW METHOD ADDED
    public List<LeaveHistory> getPendingLeaveApplications(int managerId) throws SQLException, ClassNotFoundException {
        List<LeaveHistory> pendingLeaves = new ArrayList<>();
        connection = ConnectionHelper.getConnection();

        String sql = "SELECT lh.*, e.EMP_NAME FROM leave_history lh "
                   + "JOIN employee e ON lh.EMP_ID = e.EMP_ID "
                   + "WHERE e.EMP_MANAGER_ID = ? AND lh.LEAVE_STATUS = 'PENDING'";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, managerId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            LeaveHistory leave = new LeaveHistory();
            leave.setLeaveId(rs.getInt("LEAVE_ID"));
            leave.setEmpId(rs.getInt("EMP_ID"));
            leave.setEmpName(rs.getString("EMP_NAME"));  // requires a field in your LeaveHistory model
            leave.setStartDate(rs.getDate("LEAVE_START_DATE"));
            leave.setEndDate(rs.getDate("LEAVE_END_DATE"));
            leave.setLeaveType(rs.getString("LEAVE_TYPE"));
            leave.setLeaveStatus(rs.getString("LEAVE_STATUS"));
            leave.setLeaveReason(rs.getString("LEAVE_REASON"));
            leave.setMngrComments(rs.getString("LEAVE_MNGR_COMMENTS"));
            pendingLeaves.add(leave);
        }

        return pendingLeaves;
    }
}
