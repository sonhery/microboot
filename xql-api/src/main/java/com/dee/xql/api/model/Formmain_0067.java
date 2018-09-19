package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目资料表
 * 
 * @author YSH
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class Formmain_0067 extends Formmain implements Serializable {

	/**
	 * 项目编码
	 */
	private String projectId;// field0001 项目编码
	private String projectName;// field0002 项目名称
	private String customerId;// field0003 客户编码
	private String customerName;// field0004 客户名称
	private String payerId;// field0158 付款方编码
	private String payerName;// field0159 付款方名称
	private String customerIndustry;// field0005 客户行业
	private String mainContact;// field0006 主要联系人
	private String mainContactPhone;// field0007 联系人电话
	private String otherContactPhone;// field0148 其它联系电话
	private String otherContact;// field0143 其他联系人
	private String phone;// field0144 联系电话
	private String projectIndustry;// field0138 项目所属行业
	private String province;// field0008 省
	private String city;// field0009 市
	private String county;// field0010 县(区)
	private String street;// field0175 街道(路)
	private String streetNo;// field0176 街道门牌
	private String buildName;// field0177 商厦名称
	private String buildFloor;// field0178 商厦楼层
	private String shopName;// field0180 主店招名称
	private String notes;// field0179 备注
	private String projectAddress;// field0011 项目地址
	private String money;// field0012 金额
	private Date planStartDate;// field0013 计划开工时间
	private Date planFinishDate;// field0014 计划完工时间
	private Date actualStartDate;// field0181 实际开始时间
	private Date actualFinishDate;// field0182 实际结束时间
	private String field0183;// 免收一期款
	private String projectDesc;// field0015 项目简介
	private String projectAtt;// field0016 项目资料附件
	private String projectLevel;// field0017 项目级别
	private String importanceDegree;// field0018 重要程度
	private String emergencyDegree;// field0019 紧急程度
	private String salePrincipal;// field0023 销售负责人
	private String salePrincipalCompay;// field0021 销售负责人单位
	private String salePrincipalPhone;// field0139 销售负责人电话
	private String mainDutyCompanyId;// field0166 主责公司代码
	private String constructionPrincipal;// field0028 施工负责人
	private String constructionPrincipalCompany;// field0026 施工负责人单位
	private String constructionPrincipalPhone;// field0136 施工负责人电话
	private String projectStage;// field0030 项目阶段
	private String projectStatus;// field0031 项目状态
	private String applyArbitrationCount;// field0032 申请仲裁次数
	private String projectType;// field0066 项目类型
	private String crossDistrict;// field0061 是否跨区
	private String agreeCrossDistrictOrderNo;// field0067 同意跨区单号
	private String projectNature;// field0063 项目性质
	private String commercialOpportunityProvide;// field0059 商机提供人员
	private String projectBrand;// field0156 项目涉及品牌
	private String field0135;// 是否已有平面图方案
	private String field0145;// 是否需要重新调整/设计平面图方案
	private String field0137;// 是否已有效果图方案
	private String field0147;// 是否需要重新调整/设计效果图方案
	private String field0142;// 是否已有施工图方案
	private String field0150;// 是否需要重新调整/设计施工图方案
	private String amountRoomOrderNo;// field0126 量房单号
	private String amountRoomPerson;// field0064 量房人员
	private String amountRoomDate;// field0057 测量日期
	private String businessScope;// field0154;// 经营范围
	private String designStyle;// field0155 设计风格
	private String field0125;// 复尺单号
	private String field0058;// 是否需要复尺
	private String field0068;// 复尺计划阶段
	private String projectDemand;// field0065 项目需求
	private Double fitmentArea;// field0069 装修面积
	private String predictPutMoney;// field0060 预计投入金额
	private String predictStartBusinessDate;// field0062 预计开业时间
	private String dataNature;// field0164 数据性质
	private String empCode;//field0165 EMP编码
	private String projectBelongToCompanyId;// field0185 项目所属公司代码
	private String projectBelongToCompanyName;// field0184 项目所属公司
	private String authDeptId;// field0168 授权部门名称
	private String authDeptCode;// field0167 授权部门代码

}
