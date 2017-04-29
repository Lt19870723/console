package com.cxdai.console.customer.information.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.base.entity.AccountUploadDoc;
import com.cxdai.console.base.entity.Member;
import com.cxdai.console.base.entity.MobileAppro;
import com.cxdai.console.base.mapper.BaseEmailApproMapper;
import com.cxdai.console.base.mapper.BaseIntegralMapper;
import com.cxdai.console.base.mapper.BaseMemberMapper;
import com.cxdai.console.base.mapper.BaseMobileApproMapper;
import com.cxdai.console.base.mapper.BaseRealNameApproMapper;
import com.cxdai.console.borrow.approve.mapper.AccountUploadDocMapper;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.mapper.SalariatBorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.entity.ApproModifyLog;
import com.cxdai.console.customer.information.entity.Integral;
import com.cxdai.console.customer.information.entity.RealNameAppro;
import com.cxdai.console.customer.information.mapper.ApproModifyLogMapper;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.vo.EmailAppro;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberRepeatCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.customer.information.vo.MobileApproVo;
import com.cxdai.console.maintain.cms.util.FileLoadUtil;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MD5;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.exception.AppException;

/**
 * 
 * <p>
 * Description:会员业务实现类<br />
 * </p>
 * 
 * @title MemberServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
    @Autowired
    private BaseRealNameApproMapper baseRealNameApproMapper;
    @Autowired
    private BaseEmailApproMapper baseEmailApproMapper;
    @Autowired
    private MobileApproService mobileApproService;
    @Autowired
    private BaseMemberMapper baseMemberMapper;
    @Autowired
    private BaseAccountMapper baseAccountMapper;
    @Autowired
    private ApproModifyLogMapper approModifyLogMapper;
    @Autowired
    private BaseIntegralMapper baseIntegralMapper;
    @Autowired
    private BaseMobileApproMapper baseMobileApproMapper;
    @Autowired
    private AccountUploadDocMapper accountUploadDocMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private SalariatBorrowMapper salariatBorrowMapper;
    
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryMemberVoList(MemberCnd memberCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = memberMapper.queryMemberVoCount(memberCnd);
		page.setTotalCount(totalCount);
		List<MemberVo> list = memberMapper.queryMemberVoList(memberCnd, page);
		page.setResult(list);
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public MemberVo queryMemberById(int id) throws Exception {
		return memberMapper.queryMemberById(id);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryMemberVoListForPages(MemberCnd memberCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		if (curPage <= 1) {
			memberCnd.set_offset(0);
		} else {
			memberCnd.set_offset((curPage - 1) * pageSize);
		}
		memberCnd.set_limit(pageSize);
        if (memberCnd.getIsCustody() != null && memberCnd.getIsCustody() == -1) {
            memberCnd.setIsCustody(null);
        }
		int totalCount = memberMapper.queryMemberVoListForCount(memberCnd);
		page.setTotalCount(totalCount);
		List<MemberVo> list = memberMapper.queryMemberVoListForPages(memberCnd);
		page.setResult(list);
		 
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Integer getMemberIdByUserName(String userName) {
		return memberMapper.getMemberIdByUserName(userName);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<MemberVo> queryMemberVoListForExport(MemberCnd memberCnd) throws Exception {
        if (memberCnd.getIsCustody() != null && memberCnd.getIsCustody() == -1) {
            memberCnd.setIsCustody(null);
        }
		int totalCount = memberMapper.queryMemberVoListForCount(memberCnd);
		memberCnd.set_offset(0);
		memberCnd.set_limit(totalCount);
		return memberMapper.queryMemberVoListForPages(memberCnd);
	}

	public void updateBorrowMemberPassword(String password) throws Exception {
		memberMapper.updateBorrowMemberPassword(password);

	}

	public void updateLoginPwd(String username, String loginPassWord) {
		String logpwd = MD5.toMD5(loginPassWord);
		memberMapper.updateLoginPwd(username, logpwd);
	}

	public void updateDealPwd(String username, String dealPassWord) {
		String dealpwd = MD5.toMD5(dealPassWord);
		memberMapper.updateDealPwd(username, dealpwd);
	}
	public String saveMember(MemberVo memberVo) throws Exception {
		String result = "success";
		// 验证用户数据
		result = this.validateRegisterMember(memberVo);
		if (!"success".equals(result)) {
			return result;
		}
		// 初始化用户信息
		Member member = new Member();
		BeanUtils.copyProperties(memberVo, member);
		// 初始化用户基本信息并保存
		member = this.initMemberInfo(member);
		// 初始化资金账户
		this.insertAccount(member.getId());
		// 初始化积分等级
		this.insertIntegral(member.getId());
		// 初始化用户论坛角色
		memberMapper.insertBbsUserGroupForUncertified(member.getId());
		memberVo.setId(member.getId());
		// 自动邮箱认证通过
		//普惠借款用户没有邮箱
		if(memberVo.getPhplatform()==null){
		autoPassedEmailAppro(memberVo);
		}
		// 自动实名认证通过
		autoPassedRealNameAppro(memberVo);
		// 自动手机认证通过
		autoPassedMobileAppro(memberVo);
		// 手机与实名认证通过，发放奖励
		MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(memberVo.getId());
		if (null != mobileApproVo && null != mobileApproVo.getPassed() && mobileApproVo.getPassed() == 1) {
			mobileApproService.saveRealNameMobileAward(memberVo.getId(), null);
		}
		// 实名认证通过,更新用户的论坛权限
		memberMapper.updateBbsUserGroupForRegistered(memberVo.getId());
		memberMapper.insertBbsUserPermissionForRegistered(memberVo.getId());
		return result;
	}
	public String validateRegisterMember(MemberVo memberVo) throws Exception {
		String result = "success";
		if (memberVo.getUsername() == null || memberVo.getUsername().equals("")) {
			return "用户名不能为空！";
		}
		if (memberVo.getLogpassword() == null || memberVo.getLogpassword().equals("")) {
			return "登陆密码不能为空！";
		}
		if (memberVo.getRealName() == null || memberVo.getRealName().equals("")) {
			return "真实姓名不能为空！";
		}
		if (memberVo.getIdcard() == null || memberVo.getIdcard().equals("")) {
			return "身份证号码不能为空！";
		}
		if (memberVo.getMobileNum() == null || memberVo.getMobileNum().equals("")) {
			return "手机号不能为空！";
		}
		//普惠借款用户没有邮箱
		if(memberVo.getPhplatform()==null){
		if (memberVo.getEmail() == null || memberVo.getEmail().equals("")) {
			return "邮箱不能为空！";
		}
		}
		// 为空直接返回false
		if (!StringUtils.isEmpty(memberVo.getUsername())) {
			// 不包含特殊字符也返回true
			Pattern p = Pattern.compile("[^`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]{1,}");
			Matcher m = p.matcher(memberVo.getUsername());
			Boolean isContain = m.matches();
			if (!isContain) {
				return "用户名包含特殊字符串";
			}
		} else {
			return "用户名包含特殊字符串";
		}
		// 验证用户名
		MemberRepeatCnd usernameCnd = new MemberRepeatCnd();
		usernameCnd.setUsername(memberVo.getUsername());
		Integer usernameCount = memberMapper.queryRepeatMemberCount(usernameCnd);
		if (null != usernameCount && usernameCount > 0) {
			return "该用户名已经存在！";
		}
		return result;
	}

	private Member initMemberInfo(Member member) throws Exception {
		// 封装会员 用户基本信息
		member.setAddtime(DateUtils.getTime());
		member.setHeadimg("/images/main/head.png");
		member.setAddip("0.0.0.1");
		member.setStatus(Constants.MEMBER_STATUS_NORMAL);
		member.setType(0);// 正式身份
		// 注册不给积分
		member.setAccumulatepoints(Integer.valueOf(0));
		member.setGainaccumulatepoints(Integer.valueOf(0));
		String md5 = MD5.toMD5(member.getUsername() + member.getAddtime());
		member.setUseridmd5(md5);
		// 加密密码
		member.setLogpassword(MD5.toMD5(member.getLogpassword()));
		member.setAwardmoney(new BigDecimal("0"));
		member.setLastloginip("0.0.0.1");
		member.setLastlogintime(DateUtils.getTime());
		member.setLogintimes(1);
		member.setBorrowtimes(0);
		member.setInvesttimes(0);
		member.setLogintimes(0);
		member.setSource(0); // 网站注册
		member.setIsFinancialUser(Constants.MEMBER_BORROW_USER); // 借款用户
		member.setIsEmployeer(Constants.NO);
		member.setPlatform("1");
		// 新增会员
		Integer id = baseMemberMapper.insertEntity(member);
		if (member.getId() == null || id == null || id == 0) {
			throw new AppException("注册失败！");
		}
		return member;
	}
	/**
	 * 
	 * <p>
	 * Description:初始化资金账户<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月2日
	 * @param userId
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer insertAccount(Integer userId) throws Exception {
		Account account = new Account();
		BigDecimal zero = BigDecimal.ZERO;
		account.setUserId(userId);
		// 初始化帐号的金额为0
		account.setTotal(zero);
		account.setUseMoney(zero);
		account.setNoUseMoney(zero);
		account.setCollection(zero);
		account.setFirstBorrowUseMoney(zero);
		account.setNetvalue(zero);
		account.setVersion(1);
		baseAccountMapper.insertEntity(account);
		return account.getId();
	}

	/**
	 * 
	 * <p>
	 * Description:初始化积分等级<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月2日
	 * @param userId
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer insertIntegral(Integer userId) throws Exception {
		Integral integral = new Integral();
		integral.setCreditIntegral(1);
		integral.setInvestIntegral(1);
		integral.setCreditLevel("HR");
		integral.setInvestLevel("实习生");
		integral.setUserId(userId);
		integral.setIntegral(0);
		baseIntegralMapper.insertEntity(integral);
		return integral.getId();
	}

	/**
	 * 
	 * <p>
	 * Description:自动通过邮箱认证<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月5日
	 * @param memberVo
	 * @throws Exception
	 *             void
	 */
	public void autoPassedEmailAppro(MemberVo memberVo) throws Exception {
		EmailAppro emailAppro = new EmailAppro();
		emailAppro.setUserId(memberVo.getId());
		emailAppro.setChecked(1);
		emailAppro.setApprTime(DateUtils.getTime());
		emailAppro.setApprIp("0.0.0.1");
		emailAppro.setPlatform(1); // 官网
		emailAppro.setRandUUID(UUID.randomUUID().toString().replace("-", ""));
		emailAppro.setPlatform(1);
		baseEmailApproMapper.insertEntity(emailAppro);
		// 新增流水记录
		ApproModifyLog apprModifyLog = ApproModifyLog.createApproModifyVo(1, memberVo.getId(), "0.0.0.1", 1, "邮箱认证通过", memberVo.getEmail(),
				BusinessConstants.APPRO_MODIFY_LOG_TYPE_EMAIL);
		approModifyLogMapper.insertEntity(apprModifyLog);
	}

	/**
	 * 
	 * <p>
	 * Description:自动通过手机认证<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月5日
	 * @param memberVo
	 * @throws Exception
	 *             void
	 */
	public void autoPassedMobileAppro(MemberVo memberVo) throws Exception {
		MobileAppro mobileAppro = new MobileAppro();
		mobileAppro.setUserId(memberVo.getId());
		mobileAppro.setAddIp("0.0.0.1");
		mobileAppro.setAddTime(DateUtils.getTime());
		mobileAppro.setApproTime(DateUtils.getTime());
		mobileAppro.setMobileNum(memberVo.getMobileNum());
		mobileAppro.setPassed(1);
		mobileAppro.setRandCode("0000");
		mobileAppro.setPlatform(1);
		baseMobileApproMapper.insertEntity(mobileAppro);
		// 新增流水记录
		ApproModifyLog apprModifyLog = ApproModifyLog.createApproModifyVo(1, memberVo.getId(), "0.0.0.1", 1, "手机认证通过", memberVo.getMobileNum(),
				BusinessConstants.APPRO_MODIFY_LOG_TYPE_MOBILE);
		approModifyLogMapper.insertEntity(apprModifyLog);
	}

	/**
	 * 
	 * <p>
	 * Description:自动通过实名认证<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月5日
	 * @param memberVo
	 * @throws Exception
	 *             void
	 */
	public void autoPassedRealNameAppro(MemberVo memberVo) throws Exception {
		RealNameAppro realNameAppro = new RealNameAppro();
		realNameAppro.setUserId(memberVo.getId());
		realNameAppro.setIsPassed(1);
		realNameAppro.setRealName(memberVo.getRealName());
		realNameAppro.setIdCardNo(memberVo.getIdcard());
		realNameAppro.setApproveTime(DateUtils.getTime());
		realNameAppro.setAppTime(DateUtils.getTime());
		//普惠借款用户注册
		if(memberVo.getPhplatform()==null){
		realNameAppro.setApproveUser(ShiroUtils.currentUser().getUserName());
		}else{
			realNameAppro.setApproveUser("-1");
		}
		realNameAppro.setAppIp("0.0.0.1");
		realNameAppro.setPlatform(1);
		baseRealNameApproMapper.insertEntity(realNameAppro);
		// 新增流水记录
		ApproModifyLog apprModifyLog = ApproModifyLog.createApproModifyVo(1, memberVo.getId(), "0.0.0.1", 1, "新增实名认证通过", memberVo.getRealName(),
				BusinessConstants.APPRO_MODIFY_LOG_TYPE_REALNAME);
		approModifyLogMapper.insertEntity(apprModifyLog);
	}
	/**
	 * 
	 * <p>
	 * Description:上传图片<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月28日
	 * @param file
	 * @param style
	 * @param borrowId
	 * @param userId
	 * @param ip
	 * @return
	 * @throws Exception
	 * String
	 */
	public String saveUploadPic(MultipartFile file, HttpServletRequest request,Integer style, Integer borrowId, Integer userId, String ip) throws Exception {
		Map<String, String> resultMap = FileLoadUtil.borrowUploadPic(file, request,PropertiesUtil.getValue("cms_upload_path"), 512000 * 5);
		if (resultMap.get("result").equals("success")) {
			// 保存
			AccountUploadDoc doc = new AccountUploadDoc();
			doc.setBorrowId(borrowId);
			doc.setUploadIp(ip);
			doc.setUserId(userId);
			doc.setStyle(style);
			doc.setDocName(resultMap.get("fileName"));
			doc.setDocPath(resultMap.get("url"));
			doc.setUploadTime(new Date());
			if (accountUploadDocMapper.insertEntity(doc) > 0) {
				return "success";
			} else {
				// 删除上传的图片
				String attachementPath = resultMap.get("path");
				File attachmentFile = new File(attachementPath);
				if (attachmentFile.exists()) { // 判断该文件是否存在
					attachmentFile.delete();
				}
				return "图片上传失败！";
			}
		} else {
			return resultMap.get("result").toString();
		}
	}
	public String deleteDoc(Integer docId) throws Exception {
		if (docId == null || docId <= 0) {
			return "资料不存在，请刷新后重试！";
		}
		accountUploadDocMapper.deleteDoc(docId);
		return "success";
	}
	public String deleteAllDoc(Integer borrowId) throws Exception {
		if (borrowId == null || borrowId <= 0) {
			return "借款标不存在，请刷新后重试！";
		}
		accountUploadDocMapper.deleteDocByBorrowId(borrowId);
		return "success";
	}
	public String saveBorrowInfosFromOldBorrow(String addip, Integer borrowId, Integer oldBorrowId) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKey(oldBorrowId);
		if (borrowVo == null) {
			return "借款记录不存在";
		}
		borrowVo = borrowMapper.selectByPrimaryKey(borrowId);
		if (borrowVo == null) {
			return "借款记录不存在";
		}
		if (salariatBorrowMapper.insertPicFromBorrowId(addip, borrowId, oldBorrowId) > 0) {
			return "success";
		} else {
			return "上传历史标图片异常";
		}
	}
	
	/**
	 * 分页获取客户信息
	 * @author WangQianJin
	 * @title @param memberCnd
	 * @title @param pageSize
	 * @title @param curPage
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page getPageMemberVo(MemberCnd memberCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = memberMapper.getPageMemberVoCount(memberCnd);
		page.setTotalCount(totalCount);
		List<MemberVo> list = memberMapper.getPageMemberVoList(memberCnd, page);
		page.setResult(list);
		return page;
	}
}
