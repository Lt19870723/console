package com.cxdai.console.customer.information.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * <p>
 * Description:会员类Vo<br />
 * </p>
 * 
 * @title MemberVo.java
 * @package com.cxdai.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月11日
 */
public class MemberVo implements Serializable {

	private static final long serialVersionUID = -5890980057423538506L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户名ID
	 */
	private String userId;
	/**
	 * 登录密码
	 */
	private String logpassword;
	/**
	 * 交易密码
	 */
	private String paypassword;
	/**
	 * 用户头像路径
	 */
	private String headimg;
	/**
	 * email
	 */
	private String email;
	/**
	 * 账户状态（-1：账户注销；0：正常状态）
	 */
	private Integer status;
	/**
	 * 借款次数
	 */
	private Integer borrowtimes;
	/**
	 * 投资次数
	 */
	private Integer investtimes;
	/**
	 * 登陆次数
	 */
	private Integer logintimes;
	/**
	 * 账户注册时间
	 */
	private String addtime;
	/**
	 * 账户注册IP
	 */
	private String addip;
	/**
	 * 推荐人，推荐码
	 */
	private String shareperson;
	/**
	 * 最后登陆时间
	 */
	private String lastlogintime;
	/**
	 * 最后登陆IP
	 */
	private String lastloginip;
	/**
	 * 用户IDMD5值
	 */
	private String useridmd5;
	/**
	 * 邀请人ID
	 */
	private Integer inviterid;
	/**
	 * 赠送金额
	 */
	private BigDecimal awardmoney;
	/**
	 * 会员总积分
	 */
	private Integer accumulatepoints;
	/**
	 * 会员类型（0：普通会员 1：新手导航版主 2：平台心声版主 3：报道领工资版主 4：你问我答版主 5：投资者交流区版主 6：投资者考察区版主
	 * 7：平台交易数据统计区版主 8：娱乐灌水专区版主 9：超级管理员 10:平台公告区版主）
	 */
	private Integer type;
	/**
	 * 会员来源（0：网站注册 1：网贷第三方）
	 */
	private Integer source;
	/**
	 * 会员可兑换积分
	 */
	private Integer gainaccumulatepoints;
	/**
	 * 注册时的国家或省份
	 */
	private String province;
	/**
	 * 注册时的登录城市
	 */
	private String city;
	/**
	 * 注册时的登录区域
	 */
	private String area;

	/**
	 * 是否是内部员工 (1 是 0 否)
	 */
	private Integer isEmployeer;

	/**
	 * 理财/借款用户（1 理财用户 0 借款用户）
	 */
	private Integer isFinancialUser;

	/**
	 * QQ号码
	 */
	private String qq;
	/**
	 * 微信号码
	 */
	private String wxNo;
	/**
	 * 邮件打开密码
	 */
	private String emailPassword;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 手机号
	 */
	private String mobileNum;
	private String addtimeStr;
	private String lastlogintimeStr;
	private BigDecimal total;
	private BigDecimal useMoney;
	private BigDecimal noUseMoney;
	private BigDecimal collection;
	private BigDecimal drawMoney;
	private BigDecimal noDrawMoney;
	private BigDecimal firstBorrowUseMoney;
	private BigDecimal curTotal;
	private BigDecimal fixTotal;
	private String totalStr;
	private String useMoneyStr;
	private String noUseMoneyStr;
	private String collectionStr;
	private String drawMoneyStr;
	private String noDrawMoneyStr;
	private String firstBorrowUseMoneyStr;
	private String curTotalStr;
	private String fixTotalStr;
	private String logintimesStr;
	private Integer isVip; // 是否VIP，1：是，0：否

	private BigDecimal eUseMoney;
	private BigDecimal eFreezeMoney;
	private BigDecimal eCollection;
	private String eUseMoneyStr;
	private String eFreezeMoneyStr;
	private String eCollectionStr;

	// 注销客户返回ID
	private int MEMBERID;

	// 来源描述
	private String sourceDesc;

	/** 投资金额 */
	private BigDecimal tenderAccountTotal;
	private String tenderAccountTotalStr;

	/** 身份证号码 */
	private String idcard;
	
	private Date addtimeDate;

    private String idCardNo;

    private Integer isCustody;

    private Integer eType;
    
    private String phplatform;//平台来源
    
    
    

  

	public String getPhplatform() {
		return phplatform;
	}

	public void setPhplatform(String phplatform) {
		this.phplatform = phplatform;
	}

	public MemberVo() {
    }

    public Date getAddtimeDate() {
		return addtimeDate;
	}

	public void setAddtimeDate(Date addtimeDate) {
		this.addtimeDate = addtimeDate;
	}

	public int getMEMBERID() {
		return MEMBERID;
	}

	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLogpassword() {
		return logpassword;
	}

	public void setLogpassword(String logpassword) {
		this.logpassword = logpassword;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBorrowtimes() {
		return borrowtimes;
	}

	public void setBorrowtimes(Integer borrowtimes) {
		this.borrowtimes = borrowtimes;
	}

	public Integer getInvesttimes() {
		return investtimes;
	}

	public void setInvesttimes(Integer investtimes) {
		this.investtimes = investtimes;
	}

	public Integer getLogintimes() {
		return logintimes;
	}

	public void setLogintimes(Integer logintimes) {
		this.logintimes = logintimes;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getShareperson() {
		return shareperson;
	}

	public void setShareperson(String shareperson) {
		this.shareperson = shareperson;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getUseridmd5() {
		return useridmd5;
	}

	public void setUseridmd5(String useridmd5) {
		this.useridmd5 = useridmd5;
	}

	public Integer getInviterid() {
		return inviterid;
	}

	public void setInviterid(Integer inviterid) {
		this.inviterid = inviterid;
	}

	public BigDecimal getAwardmoney() {
		return awardmoney;
	}

	public void setAwardmoney(BigDecimal awardmoney) {
		this.awardmoney = awardmoney;
	}

	public Integer getAccumulatepoints() {
		return accumulatepoints;
	}

	public void setAccumulatepoints(Integer accumulatepoints) {
		this.accumulatepoints = accumulatepoints;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getGainaccumulatepoints() {
		return gainaccumulatepoints;
	}

	public void setGainaccumulatepoints(Integer gainaccumulatepoints) {
		this.gainaccumulatepoints = gainaccumulatepoints;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getIsEmployeer() {
		return isEmployeer;
	}

	public void setIsEmployeer(Integer isEmployeer) {
		this.isEmployeer = isEmployeer;
	}

	public Integer getIsFinancialUser() {
		return isFinancialUser;
	}

	public void setIsFinancialUser(Integer isFinancialUser) {
		this.isFinancialUser = isFinancialUser;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWxNo() {
		return wxNo;
	}

	public void setWxNo(String wxNo) {
		this.wxNo = wxNo;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", username=" + username + ", logpassword=" + logpassword + ", paypassword=" + paypassword + ", headimg="
				+ headimg + ", email=" + email + ", status=" + status + ", borrowtimes=" + borrowtimes + ", investtimes=" + investtimes
				+ ", logintimes=" + logintimes + ", addtime=" + addtime + ", addip=" + addip + ", shareperson=" + shareperson + ", lastlogintime="
				+ lastlogintime + ", lastloginip=" + lastloginip + ", useridmd5=" + useridmd5 + ", inviterid=" + inviterid + ", awardmoney="
				+ awardmoney + ", accumulatepoints=" + accumulatepoints + ", type=" + type + ", source=" + source + ", gainaccumulatepoints="
				+ gainaccumulatepoints + ", province=" + province + ", city=" + city + ", area=" + area + ", isEmployeer=" + isEmployeer
				+ ", isFinancialUser=" + isFinancialUser + ", qq=" + qq + ", wxNo=" + wxNo + ", emailPassword=" + emailPassword + ", realName="
				+ realName + ", mobileNum=" + mobileNum + "]";
	}

	public String getAddtimeStr() {
		if (addtime != null && !addtime.equals("")) {
			return DateUtils.timeStampToDate(addtime, DateUtils.YMD_DASH);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getNoUseMoney() {
		return noUseMoney;
	}

	public void setNoUseMoney(BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}

	public BigDecimal getFirstBorrowUseMoney() {
		return firstBorrowUseMoney;
	}

	public void setFirstBorrowUseMoney(BigDecimal firstBorrowUseMoney) {
		this.firstBorrowUseMoney = firstBorrowUseMoney;
	}

	public String getTotalStr() {
		if (total != null) {
			return MoneyUtil.fmtMoneyByDot(total);
		}
		return totalStr;
	}

	public void setTotalStr(String totalStr) {
		this.totalStr = totalStr;
	}

	public String getUseMoneyStr() {
		if (useMoney != null) {
			return MoneyUtil.fmtMoneyByDot(useMoney);
		}
		return useMoneyStr;
	}

	public void setUseMoneyStr(String useMoneyStr) {
		this.useMoneyStr = useMoneyStr;
	}

	public String getNoUseMoneyStr() {
		if (noUseMoney != null) {
			return MoneyUtil.fmtMoneyByDot(noUseMoney);
		}
		return noUseMoneyStr;
	}

	public void setNoUseMoneyStr(String noUseMoneyStr) {
		this.noUseMoneyStr = noUseMoneyStr;
	}

	public String getCollectionStr() {
		if (collection != null) {
			return MoneyUtil.fmtMoneyByDot(collection);
		}
		return collectionStr;
	}

	public void setCollectionStr(String collectionStr) {
		this.collectionStr = collectionStr;
	}

	public String getDrawMoneyStr() {
		if (drawMoney != null) {
			return MoneyUtil.fmtMoneyByDot(drawMoney);
		}
		return drawMoneyStr;
	}

	public void setDrawMoneyStr(String drawMoneyStr) {
		this.drawMoneyStr = drawMoneyStr;
	}

	public String getNoDrawMoneyStr() {
		if (noDrawMoney != null) {
			return MoneyUtil.fmtMoneyByDot(noDrawMoney);
		}
		return noDrawMoneyStr;
	}

	public void setNoDrawMoneyStr(String noDrawMoneyStr) {
		this.noDrawMoneyStr = noDrawMoneyStr;
	}

	public String getFirstBorrowUseMoneyStr() {
		if (firstBorrowUseMoney != null) {
			return MoneyUtil.fmtMoneyByDot(firstBorrowUseMoney);
		}
		return firstBorrowUseMoneyStr;
	}

	public void setFirstBorrowUseMoneyStr(String firstBorrowUseMoneyStr) {
		this.firstBorrowUseMoneyStr = firstBorrowUseMoneyStr;
	}

	public String getLastlogintimeStr() {
		if (lastlogintime != null && !lastlogintime.equals("")) {
			return DateUtils.timeStampToDate(lastlogintime, DateUtils.YMD_HMS);
		}
		return lastlogintimeStr;
	}

	public void setLastlogintimeStr(String lastlogintimeStr) {
		this.lastlogintimeStr = lastlogintimeStr;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public String getSourceDesc() {
		return Dictionary.getValue(1101, String.valueOf(source));
	}

	public BigDecimal getTenderAccountTotal() {
		return tenderAccountTotal;
	}

	public void setTenderAccountTotal(BigDecimal tenderAccountTotal) {
		this.tenderAccountTotal = tenderAccountTotal;
	}

	public String getTenderAccountTotalStr() {
		if (tenderAccountTotal != null) {
			return MoneyUtil.fmtMoneyByDot(tenderAccountTotal);
		}
		return tenderAccountTotalStr;
	}

	public void setTenderAccountTotalStr(String tenderAccountTotalStr) {
		this.tenderAccountTotalStr = tenderAccountTotalStr;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public BigDecimal getCurTotal() {
		return curTotal;
	}

	public void setCurTotal(BigDecimal curTotal) {
		this.curTotal = curTotal;
	}

	public BigDecimal getFixTotal() {
		return fixTotal;
	}

	public void setFixTotal(BigDecimal fixTotal) {
		this.fixTotal = fixTotal;
	}

	public String getCurTotalStr() {
		if (curTotal != null) {
			return MoneyUtil.fmtMoneyByDot(curTotal);
		}
		return curTotalStr;
	}

	public void setCurTotalStr(String curTotalStr) {
		this.curTotalStr = curTotalStr;
	}

	public String getFixTotalStr() {
		if (fixTotal != null) {
			return MoneyUtil.fmtMoneyByDot(fixTotal);
		}
		return fixTotalStr;
	}

	public void setFixTotalStr(String fixTotalStr) {
		this.fixTotalStr = fixTotalStr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogintimesStr() {
		if(logintimes!=null&&logintimes>0){
			return logintimes.toString();
		}
		return logintimesStr;
	}

	public void setLogintimesStr(String logintimesStr) {
		this.logintimesStr = logintimesStr;
	}

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Integer getIsCustody() {
        return isCustody;
    }

    public void setIsCustody(Integer isCustody) {
        this.isCustody = isCustody;
    }

    public Integer geteType() {
        return eType;
    }

    public void seteType(Integer eType) {
        this.eType = eType;
    }



	public BigDecimal geteUseMoney() {
		return eUseMoney;
	}

	public void seteUseMoney(BigDecimal eUseMoney) {
		this.eUseMoney = eUseMoney;
	}

	public BigDecimal geteFreezeMoney() {
		return eFreezeMoney;
	}

	public void seteFreezeMoney(BigDecimal eFreezeMoney) {
		this.eFreezeMoney = eFreezeMoney;
	}

	public BigDecimal geteCollection() {
		return eCollection;
	}

	public void seteCollection(BigDecimal eCollection) {
		this.eCollection = eCollection;
	}



	public String geteUseMoneyStr() {
		if (eUseMoney != null) {
			return MoneyUtil.fmtMoneyByDot(eUseMoney);
		}
		return eUseMoneyStr;
	}

	public void seteUseMoneyStr(String eUseMoneyStr) {
		this.eUseMoneyStr = eUseMoneyStr;
	}

	public String geteFreezeMoneyStr() {
		if (eFreezeMoney != null) {
			return MoneyUtil.fmtMoneyByDot(eFreezeMoney);
		}
		return eFreezeMoneyStr;
	}

	public void seteFreezeMoneyStr(String eFreezeMoneyStr) {
		this.eFreezeMoneyStr = eFreezeMoneyStr;
	}

	public String geteCollectionStr() {
		if (eCollection != null) {
			return MoneyUtil.fmtMoneyByDot(eCollection);
		}
		return eCollectionStr;
	}

	public void seteCollectionStr(String eCollectionStr) {
		this.eCollectionStr = eCollectionStr;
	}


}