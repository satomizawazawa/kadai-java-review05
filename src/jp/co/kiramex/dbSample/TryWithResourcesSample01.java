package jp.co.kiramex.dbSample;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TryWithResourcesSample01 {

    public static void main(String[] args) {
        try {
            // ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Select文の実行と結果を格納／代入
            String sql = "SELECT * FROM country LIMIT 50";

            // try-with-resources構文
            try (// DBと接続する
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                            "root",
                            "2018886Yahoo");
                    // DBとやりとりする窓口（Statementオブジェクト）の作成
                    Statement stmt = con.createStatement();
                    // SQLを発行
                    ResultSet rs = stmt.executeQuery(sql);) {

                // 結果を表示する
                while (rs.next()) {
                    // 取得
                    String name = rs.getString("Name");
                    // 表示
                    System.out.println(name);
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }
    }
}
