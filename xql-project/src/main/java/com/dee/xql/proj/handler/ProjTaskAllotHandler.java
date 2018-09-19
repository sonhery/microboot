package com.dee.xql.proj.handler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dee.xql.api.model.ProjTaskAllot;
import com.dee.xql.proj.uitls.DBHelper;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@SuppressWarnings("deprecation")
public class ProjTaskAllotHandler extends BaseTypeHandler<Object> {
	
    @SuppressWarnings("unchecked")
	@Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws
            SQLException {
        Connection conn = null;
        try {
            if (null != o) {
                List<ProjTaskAllot> frontTaskItems = (List<ProjTaskAllot>) o;
                conn = DBHelper.getConn();
                // 这里必须用大写，而且需要引入一个包，如果不引入这个包的话字符串无法正常转换，包是：orai18n.jar
                ARRAY array = getArray(conn, "VO_PROJ_TASK_ALLOT_SVN", "VT_PROJ_TASK_ALLOT_SVN", frontTaskItems);
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

    private ARRAY getArray(Connection conn, String OracleObj, String Oraclelist, List<ProjTaskAllot> listData)
            throws Exception {
        ARRAY array = null;
        ArrayDescriptor desc = ArrayDescriptor.createDescriptor(Oraclelist, conn);
        STRUCT[] structs = new STRUCT[listData.size()];
        if (listData != null && listData.size() > 0) {
            StructDescriptor structdesc = new StructDescriptor(OracleObj, conn);
            for (int i = 0; i < listData.size(); i++) {
                ProjTaskAllot pta = listData.get(i);
                Object[] result = {
                        pta.getTaskId(),
                        pta.getAssignmentUid(),
                        pta.getTaskUid(),
                        pta.getProjectId(),
                        pta.getResourceId(),
                        pta.getResourceUid(),
                        pta.getNotes(),
                        pta.getPlanHours(),
                        pta.getPlanStartDate() == null ? pta.getPlanStartDate() : new Date(pta.getPlanStartDate()
                                .getTime()),
                        pta.getPlanEndDate() == null ? pta.getPlanEndDate() : new Date(pta.getPlanEndDate()
                                .getTime()),
                        pta.getStartDate() == null ? pta.getStartDate() : new Date(pta.getStartDate()
                                .getTime()),
                        pta.getEndDate() == null ? pta.getEndDate() : new Date(pta.getEndDate()
                                .getTime()),
                        pta.getWorkHours(),
                        pta.getCreateDate() == null ? pta.getCreateDate() : new Date(pta.getCreateDate()
                                .getTime()),
                        pta.getPercentWorkComplete(),
                        pta.getRemainingWork(),
                        pta.getCostRateTable(),
                        pta.getUnits(),
                        pta.getWorkContour(),
                        pta.getSummaryId(),
                        pta.getSvnLastVersion(),
                        pta.getTaskOutlineNumber(),
                        pta.getTaskSummaryName(),
                        pta.getResNo()
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
