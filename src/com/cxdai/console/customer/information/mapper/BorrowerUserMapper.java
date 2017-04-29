/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowerUserMapper.java
 * @package com.cxdai.console.customer.information.mapper 
 * @author tanghaitao
 * @version 0.1 2016年5月16日
 */
package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.customer.information.entity.BorrowerUser;

public interface BorrowerUserMapper {

	public List<BorrowerUser> selectBorrowUser(BorrowerUser borrowerUser);
	
}
