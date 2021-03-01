package com.cqj.project.contants;


public class Const {

	public static final String MANAGE_USER="manageUser";
	
	public static final String DEVELOPE_USER="developeUser";

	public static final String TEST_USER="testUser";
	
	public static final String AMALDAR_USER="amaldarUser";
	
	public interface DelFlagEnum{
		int NORMAL=0;   //正常
		int DELETED=1;	 //删除	 
	}
	
	
	public interface errorWritingStatus{
		int CLOSE=0;   //报告为关闭
		int OPEN=1;	 //报告为开放	 
		int GIVE=2;	 //报告已分配 
		int REFUSE=3;	 //报告拒绝
		int REPAIR=4;	 //报告已修复
		
	}
	
	
	public interface PONDERANCE{
		int ONE=1;	 	//级别：1 
		int TWO=2;	 	//级别：2	 
		int THREE=3;	 //级别：3	 
		int FOUR=4;	 	//级别：4
		int FIVE=5;	 	//级别：5
	}
	
	
	public interface PRIORITY{
		int ONE=1;	 //禁用	 
		int TWO=2;	 //禁用	 
		int THREE=3;	 //禁用	 
		int FOUR=4;	 //禁用	 
	}


	/**
	 * 默认的访问Token的HTTP请求头的名字
	 */
	public static final String ACCESS_TOKEN_HEADER_NAME = "X-Access-Auth-Token";

	/**
	 * 支付宝用户USERID的HTTP请求头的名字
	 */
	public static final String ALIPAY_USER_ID_HEADER_NAME = "Yd_Alipay_User_Id";

	/**
	 * 缓存用户的key,USER_; USER_1
	 */
	public static final String SERVER_USER_KEY = "USER_";

	/**
	 * 用户过期时间(单位:秒，1天)
	 */
	public static final int SERVER_USER_EXP_KEY = 60 * 60 * 24 * 1;

	/**
	 * 端来源-请求头key
	 */
	public static final String REQUEST_SIDE_HEAD_NAME = "X-REQUEST-SIDE";

	/**
	 * 系统参数缓存前缀
	 */
	public static final String CACHE_GLOBAL_CONFIG_PREFIX = "CACHE_GLOBAL_CONFIG_";
	/**
	 * 系统参数缓存数据 7天
	 */
	public static final int CACHE_GLOBAL_CONFIG_SECOND = 86400 * 7;

	/**
	 * 周榜缓存7天
	 */
	public static final int WEEK_RANK_EXPIRE_TIME = 86400 * 7;

	public enum Status {

		NORMAL(1, "正常"), FROZEN(2, "禁用");
		public int code;
		public String label;

		private Status(int code, String label) {
			this.code = code;
			this.label = label;
		}

		public static boolean isNormal(Integer status) {
			if (status != null && status.intValue() == NORMAL.code) {
				return true;
			}
			return false;
		}
	}

	public interface Role{
		int ROLE_HOST=0;  //直播员
		int ROLE_ADMIN=1; //后台管理员
	}
	
	public interface GroupStatusEnum{
		byte NORMAL=1;
		byte ABANDON=2;
	}
	
	public interface AdDeliveryTypeEnum{
		int BY_DEVICE_GROUP=0;  //按设备分组投放
		int BY_BUILDING=1;  //投楼宇投放
	}
	
	public interface AdTypeEnum{
		int DEFAULT_PLAY=0;   //正常播放
		int EMERGENT_PLAY=1;  //插播
	}
	
	
}
