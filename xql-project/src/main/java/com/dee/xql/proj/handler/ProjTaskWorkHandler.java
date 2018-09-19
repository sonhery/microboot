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

import com.dee.xql.api.model.ProjTaskWork;
import com.dee.xql.proj.uitls.DBHelper;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@SuppressWarnings("deprecation")
public class ProjTaskWorkHandler extends BaseTypeHandler<Object> {
	
    @SuppressWarnings("unchecked")
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType)
            throws SQLException {
        Connection conn = null;
        try {
            if (null != o) {
                List<ProjTaskWork> projTaskWorks = (List<ProjTaskWork>) o;
                conn = DBHelper.getConn();
                // 这里必须用大写，而且需要引入一个包，如果不引入这个包的话字符串无法正常转换，包是：orai18n.jar
                ARRAY array = getArray(conn, "VO_PROJ_TASK_WORK_SVN", "VT_PROJ_TASK_WORK_SVN", projTaskWorks);
                preparedStatement.setArray(i, array);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                conn.close();
            }
        }
    }

    private ARRAY getArray(Connection conn, String OracleObj, String Oraclelist, List<ProjTaskWork> listData)
            throws Exception {
        ARRAY array = null;

        ArrayDescriptor desc = ArrayDescriptor.createDescriptor(Oraclelist, conn);
        STRUCT[] structs = new STRUCT[listData.size()];
        if (listData != null && listData.size() > 0) {
            StructDescriptor structdesc = new StructDescriptor(OracleObj, conn);
            for (int i = 0; i < listData.size(); i++) {
                ProjTaskWork ptw = listData.get(i);
                Object[] result = {
                        ptw.getId(),
                        ptw.getTaskUid(),
                        ptw.getProjectId(),
                        ptw.getTaskSequenceCode(),
                        ptw.getTaskName(),
                        ptw.getWbs(),
                        ptw.getActive(),
                        ptw.getSuperiorTaskId(),
                        ptw.getSuperiorTaskUid(),
                        ptw.getNotes(),
                        ptw.getContact(),
                        ptw.getTaskModel(),
                        ptw.getPriority(),
                        ptw.getIsMilepost(),
                        ptw.getPlanStartDate() == null ? ptw.getPlanStartDate() : new Date(ptw.getPlanStartDate()
                                .getTime()),
                        ptw.getPlanEndDate() == null ? ptw.getPlanEndDate() : new Date(ptw.getPlanEndDate()
                                .getTime()),
                        ptw.getPlanTimeLimit(),
                        ptw.getLastDate() == null ? ptw.getLastDate() : new Date(ptw.getLastDate().getTime()),
                        ptw.getStartDate() == null ? ptw.getStartDate() : new Date(ptw.getStartDate().getTime()),
                        ptw.getEndDate() == null ? ptw.getEndDate() : new Date(ptw.getEndDate().getTime()),
                        ptw.getTimeLimit(),
                        ptw.getWorkHours(),
                        ptw.getCompletePercent(),
                        ptw.getFirstStartDate() == null ? ptw.getFirstStartDate() : new Date(ptw.getFirstStartDate()
                                .getTime()),
                        ptw.getFirstCompleteDate() == null ? ptw.getFirstCompleteDate() : new Date(ptw
                                .getFirstCompleteDate().getTime()),
                        ptw.getLastStartDate() == null ? ptw.getLastStartDate() : new Date(ptw.getLastStartDate()
                                .getTime()),
                        ptw.getLastCompleteDate() == null ? ptw.getLastCompleteDate() : new Date(ptw
                                .getLastCompleteDate().getTime()),
                        ptw.getStartDelay(),
                        ptw.getCompleteDelay(),
                        ptw.getTotalDelay(),
                        ptw.getCreateDate() == null ? ptw.getCreateDate() : new Date(ptw
                                .getCreateDate().getTime()),
                        ptw.getOverallocated(),
                        ptw.getTaskType(),
                        ptw.getCalendarGuid(),
                        ptw.getEstimated(),
                        ptw.getConstraintType(),
                        ptw.getConstraintDate() == null ? ptw.getConstraintDate() : new Date(ptw.getConstraintDate()
                                .getTime()),
                        ptw.getLogId(),
                        ptw.getFileName(),
                        ptw.getStartMode(),
                        ptw.getSummaryId(),
                        ptw.getIsSummary(),
                        ptw.getTaskSupervision(),
                        ptw.getTaskCode(),
                        ptw.getSvnLastVersion(),
                        ptw.getResourceNames(),
                        ptw.getCost(),
                        ptw.getOvertimeCost(),
                        ptw.getFixedCost(),
                        ptw.getActualCost(),
                        ptw.getActualOvertimeCost(),
                        ptw.getActualOvertimeWork(),
                        ptw.getPhysicalPercentComplete(),
                        ptw.getRemainingCost(),
                        ptw.getRemainingDuration(),
                        ptw.getRemainingWork(),
                        ptw.getRemainingOvertimeCost(),
                        ptw.getRemainingOvertimeWork(),
                        ptw.getHyperlink(),
                        ptw.getHyperlinkAddress()
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
