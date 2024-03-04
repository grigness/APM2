package repository;

import domain.Gene;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private ArrayList<Gene> genes;
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }
    public Repository() {
        genes = new ArrayList<>();
        openConnection();
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Gene");

            while (rs.next()) {
                genes.add(new Gene(
                        rs.getString("name"),
                        rs.getString("function"),
                        rs.getString("organism"),
                        rs.getString("sequence")
                ));
                ;
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Gene> getGenes(){
        return this.genes;
    }

    public void update(Gene gene) throws SQLException {
        String updateSql="Update Gene set function= ?, sequence= ? WHERE name = ? ";
        PreparedStatement preparedStatement=getConnection().prepareStatement(updateSql);
        preparedStatement.setString(1,gene.getFunction());
        preparedStatement.setString(2,gene.getSequence());
        preparedStatement.setString(3, gene.getName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
