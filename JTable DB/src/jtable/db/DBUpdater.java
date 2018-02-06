package jtable.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class DBUpdater {

    String conString = "jdbc:mysql://localhost:3306/playersdb";
    String username = "root";
    String password = "";

    //INSERT INTO DB
    public Boolean add(String name, String pos, String team) {
        //SQL STATEMENT
        String sql = "INSERT INTO playerstb(Name,Position,Team) VALUES('" + name + "','" + pos + "','" + team + "')";

        try {
            //GET COONECTION
            Connection con = DriverManager.getConnection(conString, username, password);

            // PREPARED STMT
            Statement s = con.prepareStatement(sql);

            s.execute(sql);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //RETRIEVE DATA
    public DefaultTableModel getData() {
        //ADD COLUMNS TO TABLE MODEL
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("ID");
        dm.addColumn("Name");
        dm.addColumn("City");
        dm.addColumn("Country");

        //SQL STATEMENT
        String sql = "SELECT * FROM playerstb";

        try {
            Connection con = DriverManager.getConnection(conString, username, password);

            //PREPARED STMT
            Statement s = con.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            //LOOP THRU GETTING ALL VALUES
            while (rs.next()) {
                //GET VALUES
                String id = rs.getString(1);
                String name = rs.getString(2);
                String pos = rs.getString(3);
                String team = rs.getString(4);

                dm.addRow(new String[]{id, name, pos, team});
            }

            return dm;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

    //UPDATE DATA
    public Boolean update(String id, String name, String pos, String team) {
        //SQL STMT
        String sql = "UPDATE playerstb SET Name ='" + name + "',Position='" + pos + "',Team='" + team + "' WHERE ID='" + id + "'";

        try {
            //GET COONECTION
            Connection con = DriverManager.getConnection(conString, username, password);

            //STATEMENT
            Statement s = con.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    //DELETE DATA
    public Boolean delete(String id)
    {
        //SQL STMT
        String sql="DELETE FROM playerstb WHERE ID ='"+id+"'";
        
        
        try
        {
            //GET COONECTION
            Connection con=DriverManager.getConnection(conString, username, password);
            
            //STATEMENT
            Statement s=con.prepareStatement(sql);
            
            //EXECUTE
            s.execute(sql);
            
            return true;
            
        }catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

}
