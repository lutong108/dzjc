package com.chinacreator.dzjc_todoStatistics;

public class EgovernmentListBean extends EgovernmentBean{
		// 单位代码
		private String areaCode;

		// 单位名称
		private String areaName;
		
		private String orgId;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public String getAreaCode() {
			return areaCode;
		}

		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		public String getOrgId() {
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}
		
}
