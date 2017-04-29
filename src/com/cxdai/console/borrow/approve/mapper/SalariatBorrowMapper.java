package com.cxdai.console.borrow.approve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.base.entity.Borrower;
import com.cxdai.console.base.entity.Mortgage;

public interface SalariatBorrowMapper {

	public Integer insertEntity(Borrow borrow);

	public Integer insertBorrowApproved(Integer borrowId);

	public List<Mortgage> getMortgageByProperty(@Param(value = "proName") String proName, @Param(value = "proValue") String proValue);

	public List<Borrower> getBorrowerByProperty(@Param(value = "proName") String proName, @Param(value = "proValue") String proValue);

	public Integer updateBorrowById(Borrow borrow);

	public Integer updateBorrowFullInfoById(Borrow borrow);

	public Integer updateBorrowerByBorrowId(Borrower borrower);

	public Integer updateMortgageByBorrowId(Mortgage mortgage);

	public List<Borrow> getBeforeBorrow(@Param(value = "userId") Integer userId, @Param(value = "borrowId") Integer borrowId);

	public Integer insertPicFromBorrowId(@Param(value = "addip") String addip, @Param(value = "borrowId") Integer borrowId, @Param(value = "oldBorrowId") Integer oldBorrowId);

}
