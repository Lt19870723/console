package com.cxdai.console.borrow.manage.service;

import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordLogMapper;
import com.cxdai.console.borrow.manage.mapper.RepaymentBatchRecordMapper;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 批量还款
 */

@Service
@Transactional(rollbackFor = Throwable.class)
public class RepaymentBatchService {
    private Logger logger=Logger.getLogger(RepaymentBatchService.class);

    @Autowired
    private RepaymentBatchRecordMapper repaymentbatchRecordMapper;



    /**
     *
     * <p>
     * Description:分页查询还款中的借款标集合<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月28日
     * @param borrowCnd
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     * Page
     */
    public Page selectRepayingBorrow(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
        Page page = new Page(curPage, pageSize);


        String statusStr = borrowCnd.getStatusStr();
        if(statusStr != null && !statusStr.equals("")){
            borrowCnd.setStatus(statusStr);
            if(statusStr.equals("0")){
                borrowCnd.setRepaymentStatus("1");
            }
            if(statusStr.equals("1")){
                borrowCnd.setRepaymentStatus("2");
            }

        }
        int totalCount = repaymentbatchRecordMapper.selectRepayingBorrowCount(borrowCnd);
        page.setTotalCount(totalCount);
        List<BRepaymentRecordVo> list = repaymentbatchRecordMapper.selectRepayingBorrow(borrowCnd, page);
        page.setResult(list);
        return page;
    }

    public BigDecimal sumWaitRepayMoney(BorrowCnd borrowCnd) {
        return repaymentbatchRecordMapper.sumWaitRepayMoney(borrowCnd);
    }
}
