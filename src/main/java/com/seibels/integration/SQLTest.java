package com.seibels.integration;

import java.sql.*;

public class SQLTest {
    public static void main(String[] args) {
        new SQLTest().execute();
    }

    private void execute() {
        try (Connection con = DriverManager.getConnection("")) {
            ResultSet catalogs = con.getMetaData().getCatalogs();
            while (catalogs.next()) {
                String nextElement =  catalogs.getString(1);
            }

            // process the resultset here, all resources will be cleaned up

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private PreparedStatement createPreparedStatement(Connection con, int userId) throws SQLException {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        return ps;
    }
}
