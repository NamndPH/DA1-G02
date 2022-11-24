package com.zrapp.warehouse.DAO;

import android.util.Log;

import com.zrapp.warehouse.Database.DbSqlServer;
import com.zrapp.warehouse.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    Connection objConn;

    public OrderDao() {
        // hàm khởi tạo để mở kết nối
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<Order> getAll() {
        List<Order> listCat = new ArrayList<>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM DonHang";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    Order order = new Order();
                    order.setId_order(resultSet.getString("maDH")); // truyền tên cột dữ liệu
                    order.setId_staff(resultSet.getString("maNV")); // tên cột dữ liệu là name
                    order.setDate(resultSet.getString("ngayTao"));
                    order.setKindOfOrder(resultSet.getString("loaiDH"));

                    listCat.add(order);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public void insertRow(Order order) {
        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO DonHang VALUES (default," +
                        "'" + order.getId_staff() + "'," +
                        "N'" + order.getKindOfOrder() + "'," +
                        "'" + order.getDate()+ "') ";

                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL);
                stmtInsert.execute();

                Log.d("zzzzz", "insertRow: finish insert");
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu ");
            e.printStackTrace();
        }
    }

}
