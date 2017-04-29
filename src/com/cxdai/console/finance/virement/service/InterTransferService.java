package com.cxdai.console.finance.virement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.AttachMent;
import com.cxdai.console.finance.virement.entity.AuditCnd;
import com.cxdai.console.finance.virement.entity.MoneyOperation;
import com.cxdai.console.finance.virement.entity.MoneyOperationApprove;
import com.cxdai.console.finance.virement.mapper.AttachMentMapper;
import com.cxdai.console.finance.virement.mapper.MoneyOperationApproveMapper;
import com.cxdai.console.finance.virement.mapper.MoneyOperationMapper;
import com.cxdai.console.finance.virement.vo.InterTransferCnd;
import com.cxdai.console.finance.virement.vo.MoneyOperationVo;
import com.cxdai.console.util.SerialNumber;

/***
 * 内部转账Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class InterTransferService {

	@Autowired
	private MoneyOperationMapper moneyOperationMapper;
	@Autowired
	private MoneyOperationApproveMapper moneyOperationApproveMapper;
	@Autowired
	private AttachMentMapper attachMentMapper;

	/***
	 * 内部转账列表
	 * 
	 * @param interTransferCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Page queryListForPage(InterTransferCnd interTransferCnd, Integer curPage, Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		Integer totalCount = moneyOperationMapper.queryListCountByCnd(interTransferCnd);
		page.setTotalCount(totalCount);
		List<MoneyOperationVo> list = moneyOperationMapper.queryListByCnd(interTransferCnd, page);
		page.setResult(list);
		return page;
	}

	/****
	 * 内部转账查询
	 * 
	 * @param interTransferCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Page searchListForPage(InterTransferCnd interTransferCnd, Integer curPage, Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		Integer totalCount = moneyOperationMapper.searchListCountByCnd(interTransferCnd);
		page.setTotalCount(totalCount);
		List<MoneyOperationVo> list = moneyOperationMapper.searchListByCnd(interTransferCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:内部转账申请<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param moneyOperation
	 * @param type
	 *            void
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertMoneyOperation(MoneyOperation moneyOperation, Integer type) throws Exception {

		// 保存
		if (type == 1) {
			moneyOperation.setStatus(1);// 草稿中
			moneyOperationMapper.insert(moneyOperation);
			// 提交
		} else if (type == 2) {
			moneyOperation.setStatus(2);// 待审核
			moneyOperationMapper.insert(moneyOperation);
			MoneyOperationApprove record = new MoneyOperationApprove();
			record.setTargetId(moneyOperation.getId());
			record.setTargetName("cw_money_operation");
			record.setTargetType(1);// 内部转账申请
			record.setStatus(1);// 提交审核
			moneyOperationApproveMapper.insert(record);
		}

	}

	/**
	 * 
	 * <p>
	 * Description:转账申请审核<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param auditCnd
	 *            void
	 */
	@Transactional
	public void moneyOperationAudit(AuditCnd auditCnd, Integer type) throws Exception {

		MoneyOperationApprove record = new MoneyOperationApprove();
		MoneyOperation moneyOperation = moneyOperationMapper.selectByPrimaryKeyForUpdate(auditCnd.getId());
		// 修改申请记录状态
		if (type == 1) {
			record.setStatus(2);// 审核通过
			auditCnd.setStatus(3);
			if (moneyOperation.getOperationCode() == null || "".equals(moneyOperation.getOperationCode())) {
				auditCnd.setOperationCode(SerialNumber.ContractSerialNumber(auditCnd.getId()));
			}

		} else if (type == 2) {
			auditCnd.setStatus(1);
			record.setStatus(3);// 审核不通过
		}
		// 修改审核记录状态
		moneyOperationMapper.updateMoneyOperation(auditCnd);

		// 添加审核记录
		record.setTargetId(moneyOperation.getId());
		record.setTargetName("cw_money_operation");
		record.setTargetType(1);// 内部转账申请
		record.setBusinessTime(auditCnd.getBusinessTime());
		record.setRemark(auditCnd.getRemark());
		record.setAddip(auditCnd.getIp());
		record.setAdduser(auditCnd.getUserId());
		moneyOperationApproveMapper.insert(record);

		// 添加证件信息记录
		if (auditCnd.getAttachmentUrl() != null && !"".equals(auditCnd.getAttachmentUrl())) {
			AttachMent attachMent = new AttachMent();
			attachMent.setName(auditCnd.getAttachmentName());
			attachMent.setUrl(auditCnd.getAttachmentUrl());
			attachMent.setType(1);// 内部转账凭证
			attachMent.setBusinessId(moneyOperation.getId());
			attachMent.setBusinessType(1);// 内部转账
			attachMent.setStatus(0);// 0-正常
			attachMent.setAddip(auditCnd.getIp());
			attachMent.setAdduser(auditCnd.getUserId());
			attachMent.setUpdateip(auditCnd.getIp());
			attachMent.setUpdateuser(auditCnd.getUserId());
			attachMent.setCardShow(auditCnd.getAttachmentType());
			attachMentMapper.insert(attachMent);
		}

	}

	/**
	 * 
	 * <p>
	 * Description:更加ID查询转账申请记录ID<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param id
	 * @return MoneyOperation
	 */
	public MoneyOperationVo findMoneyOperation(Integer id) {
		return moneyOperationMapper.findMoneyOperation(id);

	}

	/***
	 * 删除转账信息
	 * 
	 * @param id
	 */
	public void deleteByPrimaryKey(Integer id) {
		moneyOperationMapper.deleteByPrimaryKey(id);
	}

	public MoneyOperation selectByPrimaryKey(Integer id) {
		return moneyOperationMapper.selectByPrimaryKey(id);
	}

	public MoneyOperationVo findMoneyOperationDetail(Integer id) {
		return moneyOperationMapper.findMoneyOperationDetail(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateMoneyOperation(MoneyOperation moneyOperation, Integer type) {
		// 保存
		if (type == 1) {
			moneyOperation.setStatus(1);// 草稿中
			moneyOperationMapper.updateByPrimaryKey(moneyOperation);
			// 提交
		} else if (type == 2) {
			moneyOperation.setStatus(2);// 待审核
			moneyOperationMapper.updateByPrimaryKey(moneyOperation);
			MoneyOperationApprove record = new MoneyOperationApprove();
			record.setTargetId(moneyOperation.getId());
			record.setTargetName("cw_money_operation");
			record.setTargetType(1);// 内部转账申请
			record.setStatus(1);// 提交审核
			moneyOperationApproveMapper.insert(record);
		}
	}

	public Map<String, Object> findMoneyOperationMap(Integer id) {
		return moneyOperationMapper.findMoneyOperationMap(id);
	}

}
