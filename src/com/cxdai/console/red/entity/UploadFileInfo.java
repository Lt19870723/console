package com.cxdai.console.red.entity;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**   
 * <p>
 * Description:上传的文件信息对象
 * </p>
 * @title UploadFile.java
 * @package com.common.upload.entity 
 * @author  ZHUCHEN
 * @version 0.1 2014年8月14日
 */
@ManagedBean(name="fileInfo")
@SessionScoped
public class UploadFileInfo implements Serializable{
	private String id;//文件的唯一标识码（32位的UUID）
    private String Name;//文件的名称
    private String mime;//文件的类型
    private long bytes;//文件的大小
    private String path;//文件的全路径
    
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
        int extDot = name.lastIndexOf('.');
        if(extDot > 0){
            String extension = name.substring(extDot +1);
            if("bmp".equals(extension)){
                mime="image/bmp";
            } else if("jpg".equals(extension)){
                mime="image/jpeg";
            } else if("gif".equals(extension)){
                mime="image/gif";
            } else if("png".equals(extension)){
                mime="image/png";
            } else {
                mime = extension;
            }
        }
    }
   
    
    public String getMime(){
        return mime;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public long getBytes() {
		return bytes;
	}
	public void setBytes(long bytes) {
		this.bytes = bytes;
	}
	
	//其他系统自动赋值字段：
	private String memo;//文件来源
	private String createUserId;
	private Date createTimestamp;
	private String updateUserId;
	private Date updateTimestamp;
	private int removeTag;
 
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	public int getRemoveTag() {
		return removeTag;
	}
	public void setRemoveTag(int removeTag) {
		this.removeTag = removeTag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
}
