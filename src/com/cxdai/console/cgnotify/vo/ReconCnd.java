package com.cxdai.console.cgnotify.vo;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description: <br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/6/6
 * @title cxdai_interface
 * @package com.cxdai.console.cgnotify.vo
 */
public class ReconCnd extends BaseCnd {

    /****
     * 1:按天查询
     */
    public static final int SEARCHTYPE_DAY = 1;

    /****
     * 2:按月查询
     */
    public static final int SEARCHTYPE_MONTH = 2;
    /****
     * 3:按年查询
     */
    public static final int SEARCHTYPE_YEAR = 3;

    private String operationDate;// 操作日期

    private int searchType;// 时间查询类别

    private String targetType; // 业务类型

    private String targetName;

    private Integer status; // 操作结果

    private String filename; // 文件名称

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
