package com.zrapp.warehouse.DAO;

import android.util.Log;

import com.zrapp.warehouse.Database.DbSqlServer;
import com.zrapp.warehouse.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    Connection objConn;

    public ProductDAO() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<Product> getAll_Prod() {
        List<Product> list = new ArrayList<Product>();

        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham";

                Statement statement = this.objConn.createStatement();

                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    Product objCat = new Product();
                    objCat.setId(resultSet.getString("maSP"));
                    objCat.setName(resultSet.getString("tenSP"));
                    objCat.setViTri(resultSet.getString("viTRi"));
                    objCat.setPrice(resultSet.getInt("giaBan"));
                    objCat.setCost_price(resultSet.getInt("giaVon"));
                    objCat.setImg(resultSet.getString("anhSP"));

                    list.add(objCat);
                }
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }
        return list;
    }


    public void insertProd(Product objProd) {

        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO SanPham VALUES (default,N'" + objProd.getName() + "',N'" + objProd.getViTri() + "'," +
                        "'" + objProd.getPrice() + "', '" + objProd.getCost_price() + "', null) ";

                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL);
                stmtInsert.execute();
                Log.d("zzzzz", "insertRow: finish insert");
            }

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu ");
            e.printStackTrace();
        }
    }
}
