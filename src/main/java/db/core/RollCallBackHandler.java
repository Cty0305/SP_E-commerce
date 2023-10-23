package db.core;

import Exception.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RollCallBackHandler {

void rollCallBack(ResultSet rs) throws SQLException;
}
