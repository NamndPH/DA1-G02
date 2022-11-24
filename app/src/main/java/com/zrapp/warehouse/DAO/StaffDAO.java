package com.zrapp.warehouse.DAO;

import android.util.Log;

import com.zrapp.warehouse.Database.DbSqlServer;
import com.zrapp.warehouse.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StaffDAO {
    Connection objConn;

    public StaffDAO() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    //Hiển thị danh sách nhân viên
    public ArrayList<Staff> getAll() {
        ArrayList<Staff> list = new ArrayList<>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM NhanVien ";

                Statement statement = this.objConn.createStatement();

                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {

                    Staff staff = new Staff();
                    staff.setId(resultSet.getString("maNV"));
                    staff.setName(resultSet.getString("tenNV"));
                    staff.setUsername(resultSet.getString("taiKhoan"));
                    staff.setPass(resultSet.getString("matKhau"));
                    staff.setTel(resultSet.getString("dienThoai"));

                    list.add(staff);
                }
            }
        } catch (Exception e) {
            Log.e("TAG Lỗi", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return list;
    }

    //Lấy ra tài khoản
    public Staff getStaff(String username) {
        Staff staff = new Staff();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM NhanVien WHERE taiKhoan = '" + username + "'";

                Statement statement = this.objConn.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    staff.setId(resultSet.getString("maNV"));
                    staff.setName(resultSet.getString("tenNV"));
                    staff.setUsername(resultSet.getString("taiKhoan"));
                    staff.setPass(resultSet.getString("matKhau"));
                    staff.setTel(resultSet.getString("dienThoai"));
                    staff.setImg(resultSet.getString("anhNV"));
                    staff.setPost(resultSet.getString("chucVu"));
                    staff.setStatus(resultSet.getBoolean("trangThai"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }

    //Thêm nhân viên
    public void insertStaff(Staff staff, int status) {
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO NhanVien VALUES (default," +
                        "N'" + staff.getName() + "'," +
                        "'" + staff.getUsername() + "'," +
                        "'" + staff.getPass() + "'," +
                        "'" + staff.getTel() + "',null," +
                        "N'" + staff.getPost() + "'," +
                        status + ") ";

                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL);
                stmtInsert.execute();

                Log.d("TAG Debug", "insertRow: finish insert");
            }

        } catch (Exception e) {
            Log.e("TAG Lỗi", "insertRow: Có lỗi thêm dữ liệu ");
            e.printStackTrace();
        }
    }

    //Xóa nhân viên
    public void deleteRow(String maNv) {
        try {
            if (this.objConn != null) {
                String deleteSQL = "DELETE NhanVien WHERE maNV='" + maNv + "'";

                PreparedStatement stmtDelete = this.objConn.prepareStatement(deleteSQL);
                stmtDelete.execute();

                Log.d("TAG Debug", "deleteRow: finish insert");
            }

        } catch (Exception e) {
            Log.e("TAG Lỗi", "deleteRow: Có lỗi xóa dữ liệu ");
            e.printStackTrace();
        }
    }

    //Sửa thông tin nhân viên
    public void updateRow(Staff staff) {

        try {
            if (this.objConn != null) {
                String sqlUpdate = "UPDATE NhanVien SET tenNV= N'" + staff.getName() + "'," +
                        "taiKhoan= '" + staff.getUsername() + "'," +
                        "matKhau= '" + staff.getPass() + "'," +
                        "dienThoai= '" + staff.getTel() + "' " +
                        "WHERE maNV = '" + staff.getId() + "'";

                PreparedStatement stmtUpdate = this.objConn.prepareStatement(sqlUpdate);
                stmtUpdate.execute();

                Log.d("zzzzz", "updateRow: finish Update");
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
        }
    }


    public boolean isExistsStaff(String username) {
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM NhanVien WHERE maNV='" + username + "'";

                Statement statement = this.objConn.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}

