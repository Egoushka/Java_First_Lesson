package javadbwork1;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Random;

public class JavaDBWork1 {

    private static final String HOST = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String HOST_ELEVATOR = "jdbc:mysql://localhost:3306/Elevator?useUnicode=true";
    private static Statement stmt;                                              // SqlCommand
    private static Connection con;
    private static ResultSet result;                                           // DataReader

    public static void main(String[] args) {
        try {
            /*
                con = DriverManager.getConnection(HOST, USER, PASS);
                System.out.println("Connection OK");
                stmt = con.createStatement();
                stmt.executeUpdate(                                                 // -> NonQuery
                    "CREATE DATABASE IF NOT EXISTS Elevator"
                );
                System.out.println("CREATE DB OK");
            */

            /* 
                con = DriverManager.getConnection(HOST,USER,PASS);
                stmt = con.createStatement();
                stmt.executeLargeUpdate("CREATE DATABASE Elevator");
                System.out.println("Create OK");           
            */
            /*         
                con = DriverManager.getConnection(HOST_ELEVATOR, USER, PASS);
                System.out.println("Connection OK");
                stmt = con.createStatement();
                stmt.executeUpdate(
                        "CREATE TABLE Building(" +
                                "`id` INT PRIMARY  KEY AUTO_INCREMENT," +
                                "`address` VARCHAR(255)" +
                                ")Engine=InnoDB DEFAULT CHARSET = utf8"
                 stmt.executeUpdate(" INSERT INTO Building " +
                        "VALUES " +
                        "(1, 'Pushka')," +
                        "(2,'Jabka')," +            
                        "(3, 'Lal')");
                  System.out.println("Insert and CREATE OK");

               stmt.executeUpdate(
                        "CREATE TABLE Floor(" +
                                "`id` INT PRIMARY KEY AUTO_INCREMENT," +
                                "`title` VARCHAR(32)," +
                                "`level` INT" +
                                ")Engine=InnoDB DEFAULT CHARSET = utf8"
                );
                System.out.println("CREATE TABLE OK");

                stmt.executeUpdate(" INSERT INTO Floor(" +
                        "VALUES" +
                        "(1,'Parking',-1)," +
                        "(2,'0',0)," +
                        "(3,'1',0)," +
                        "(4,'2',0)," +
                        "(5,'3',0)," +
                        "(6,'4',0)," +
                        "(7,'5',0)," +
                        "(8,'6',0)," +
                        "(9,'Penthouse',1));");

                System.out.println("Insert and CREATE OK");
                
                stmt.executeUpdate("CREATE TABLE Composite("
                        + "`id` INT PRIMARY KEY AUTO_INCREMENT,"
                        + "`id_B` INT,"
                        + "`id_F` INT)Engine=InnoDB DEFAULT CHARSET = utf8");
                System.out.println("Insert and CREATE OK");
                
                stmt.executeUpdate("INSERT INTO Composite(id_B, id_F)("
                        + "VALUES"
                        + "(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),"
                        + "(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),"
                        + "(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),(3,8),(3,9))");
                System.out.println("Insert and CREATE OK");
            */
            Building building = new Building();
            int count = 10;
            con = DriverManager.getConnection(HOST_ELEVATOR, USER, PASS);
            stmt = con.createStatement();
            System.out.println("Connection OK");

            String query = "SELECT COUNT(id) FROM Building";
            ResultSet res = stmt.executeQuery(query);
            res.next();
            System.out.println("cnt" + " " + res.getInt(1));

            Random rnd = new Random();
            int rndBuilding = rnd.nextInt(count);
            query
                    = "SELECT * FROM Composite AS C "
                    + "JOIN Floor AS F ON F.id = C.id_F "
                    + "JOIN Building AS B ON B.id = C.id_B "
                    + "WHERE B.id = (SELECT id FROM Building LIMIT " + rndBuilding + ",1) order by F.level";

            res = stmt.executeQuery(query);

            res.next();
            building.setAdress(res.getString(1));
            do {
                building.floors.add(new Floor(res.getString(2)));
            } while (res.next());

            result.close();
            stmt.close();
            con.close();
            // Passengers on floors
            for (Floor floor : building.floors) {
                //floor.pas -
                int pasCnt = rnd.nextInt(10);
                for(int i = 0;i<pasCnt;++i){
                   //10 - максимум, по ТЗ
                   int cur = building.floors.indexOf(floor);
                   int dest;
                   do{
                       dest = rnd.nextInt(building.floors.size());
                   }while(dest == cur);
                floor.passangers.add(new Passanger(dest));
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
