package com.vh.repository.impl;

import com.vh.model.OtherStaff;
import com.vh.model.Worker;
import com.vh.model.enums.DepartmentType;
import com.vh.model.enums.RoleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class OtherStaffDAO extends WorkerDAO{

    private final RoleType roleType = RoleType.OTHER;

    public OtherStaffDAO(String url, String user, String password) {
        super(url, user, password);
    }

    @Override
    public OtherStaff getById(Integer id) throws SQLException {
        OtherStaff otherStaff = (OtherStaff) super.getById(id);

        ResultSet resultSet;
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("SELECT * FROM vh_worker " +
                "WHERE id = ?");
        myStmt.setLong(1, id);

        resultSet = myStmt.executeQuery();

        while (resultSet.next()){
            otherStaff.setDescription(resultSet.getString("description"));
        }
        return otherStaff;
    }

    @Override
    public OtherStaff create(Worker worker) throws SQLException {
        OtherStaff otherStaff = (OtherStaff) super.create(worker);

        setDepartment(otherStaff);

        return otherStaff;
    }

    @Override
    public OtherStaff update(Worker worker) throws SQLException {
        OtherStaff otherStaff = (OtherStaff) super.update(worker);

        setDepartment(otherStaff);

        return otherStaff;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public List<Worker> findAll() throws SQLException {
        List<OtherStaff> otherStaffs = new ArrayList<>();

        PreparedStatement myStmt;
        ResultSet resultSet;

        myStmt = myConn.prepareStatement("SELECT * FROM vh_worker WHERE role = ?");
        myStmt.setObject(1, roleType, Types.OTHER);
        resultSet = myStmt.executeQuery();

        while (resultSet.next()){
            otherStaffs.add(new OtherStaff(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDate("birthday"),
                    resultSet.getDate("start_date"),
                    resultSet.getLong("salary"),
                    RoleType.valueOf(resultSet.getString("role")),
                    DepartmentType.valueOf(resultSet.getString("department")),
                    resultSet.getString("description")));
        }
        return new ArrayList<>(otherStaffs);
    }

    private void setDepartment(OtherStaff otherStaff) throws SQLException {
        PreparedStatement myStmt;
        myStmt = myConn.prepareStatement("UPDATE vh_worker SET " +
                "description = ? " +
                "WHERE id = ?");
        myStmt.setString(1, otherStaff.getDescription());
        myStmt.setLong(2, otherStaff.getId());

        myStmt.execute();
    }
}