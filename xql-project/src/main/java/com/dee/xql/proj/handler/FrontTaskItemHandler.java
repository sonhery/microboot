package com.dee.xql.proj.handler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dee.xql.api.model.FrontTaskItem;
import com.dee.xql.proj.uitls.DBHelper;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@SuppressWarnings("deprecation")
public class FrontTaskItemHandler extends BaseTypeHandler<Object> {
	
    @Override
    @SuppressWarnings("unchecked" )
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws
            SQLException {
        Connection conn = null;
        try {
            if (null != o) {
                List<FrontTaskItem> frontTaskItems = (List<FrontTaskItem>) o;
                conn = DBHelper.getConn();
                // 这里必须用大写，而且需要引入一个包，如果不引入这个包的话字符串无法正常转换，包是：orai18n.jar
                ARRAY array = getArray(conn, "VO_FRONT_TASK_ITEM_SVN", "VT_FRONT_TASK_ITEM_SVN", frontTaskItems);
                preparedStatement.setArray(i, array);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

	private ARRAY getArray(Connection conn, String OracleObj, String Oraclelist, List<FrontTaskItem> listData)
            throws Exception {
        ARRAY array = null;
        ArrayDescriptor desc = ArrayDescriptor.createDescriptor(Oraclelist, conn);
        STRUCT[] structs = new STRUCT[listData.size()];
        if (listData != null && listData.size() > 0) {
            StructDescriptor structdesc = new StructDescriptor(OracleObj, conn);
            for (int i = 0; i < listData.size(); i++) {
                FrontTaskItem fti = listData.get(i);
                Object[] result = {
                        fti.getTaskId(),
                        fti.getTaskUid(),
                        fti.getProjectId(),
                        fti.getFrontTaskId(),
                        fti.getFrontTaskUid(),
                        fti.getFrontProjectId(),
                        fti.getFrontType(),
                        fti.getDelayTime(),
                        fti.getSummaryId(),
                        fti.getSvnLastVersion()
                };
                structs[i] = new STRUCT(structdesc, conn, result);
            }
            array = new ARRAY(desc, conn, structs);
        } else {
            array = new ARRAY(desc, conn, structs);
        }
        return array;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
