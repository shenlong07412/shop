package net.shopxx.entity;

import java.io.Serializable;

public class ZtreeBean implements Serializable{
    private static final long serialVersionUID = 4542667966180441411L;
    /********************树的基本属性***********************/
    //节点id
    private Integer treeId;
    public Integer getTreeId() {
		return treeId;
	}
	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	private Integer parentId;
    //名称
    private String name;
    //是否是父节点
    private Boolean isParent;
    
    //是否可点击
    private Boolean isClick;
	public Boolean getIsClick() {
		return isClick;
	}
	public void setIsClick(Boolean isClick) {
		this.isClick = isClick;
	}
	
	private Boolean checked  = false;
    
    public Boolean getChecked() {
        return checked;
    }
    
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
	
    
}
