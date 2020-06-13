package oesk.databases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryExecuter {
    private static Statement stmt;
    private static ResultSet resultsSelect;
    private static int resultsInsert;

    public ArrayList<Table> executeSelect(String query){
        ArrayList<Table> list = new ArrayList<>();
        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            resultsSelect = stmt.executeQuery(query);

            while (resultsSelect.next()) {

                Table tableObject = new Table();

                tableObject.setId(Integer.parseInt(resultsSelect.getString("id")));
                tableObject.setDownloadAverage(Float.parseFloat(resultsSelect.getString("downloadAverage")));
                tableObject.setUploadAverage(Float.parseFloat(resultsSelect.getString("uploadAverage")));

                list.add(tableObject);
            }

            if(!list.isEmpty()){
                ObjectMapper mapper = new ObjectMapper();
                String JSONOutput = mapper.writeValueAsString(list.get(0));
                //System.out.println(JSONOutput);
                for (Table table: list) {
                    //System.out.println(mapper.writeValueAsString(table));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void executeInsert(String query) {

        try(Connection conn = DBConnection.createNewDBconnection()){

            stmt = conn.createStatement();
            resultsInsert = stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
