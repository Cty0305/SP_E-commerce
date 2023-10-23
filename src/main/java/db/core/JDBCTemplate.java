package db.core;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate  {
    Connection connection;

    {
        try {
            connection = DBHelp.getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void query(PreparedStatementCreator preparedStatementCreator, RollCallBackHandler rollCallBackHandler) {

        try{
            PreparedStatement preparedStatement = preparedStatementCreator.createPreparedstatement(connection);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                rollCallBackHandler.rollCallBack(rs);
            }
        } catch (SQLException e){
            System.err.println("Query產生SQLException");
            e.printStackTrace();
        }




    }

    public void query(PreparedStatementCreator preparedStatementCreator) {

        try {
            PreparedStatement preparedStatement = preparedStatementCreator.createPreparedstatement(connection);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println("Query產生SQLException");
            e.printStackTrace();
        }

    }
}
