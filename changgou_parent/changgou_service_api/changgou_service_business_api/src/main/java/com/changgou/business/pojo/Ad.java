package com.changgou.business.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:shenkunlin
 * @Description:Ad构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Ad",value = "Ad")
@Table(name="tb_ad")
public class Ad implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//ID

	@ApiModelProperty(value = "广告名称",required = false)
    @Column(name = "name")
	private String name;//广告名称

	@ApiModelProperty(value = "广告位置",required = false)
    @Column(name = "position")
	private String position;//广告位置

	@ApiModelProperty(value = "开始时间",required = false)
    @Column(name = "start_time")
	private Date startTime;//开始时间

	@ApiModelProperty(value = "到期时间",required = false)
    @Column(name = "end_time")
	private Date endTime;//到期时间

	@ApiModelProperty(value = "状态",required = false)
    @Column(name = "status")
	private String status;//状态

	@ApiModelProperty(value = "图片地址",required = false)
    @Column(name = "image")
	private String image;//图片地址

	@ApiModelProperty(value = "URL",required = false)
    @Column(name = "url")
	private String url;//URL

	@ApiModelProperty(value = "备注",required = false)
    @Column(name = "remarks")
	private String remarks;//备注



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public String getPosition() {
		return position;
	}

	//set方法
	public void setPosition(String position) {
		this.position = position;
	}
	//get方法
	public Date getStartTime() {
		return startTime;
	}

	//set方法
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	//get方法
	public Date getEndTime() {
		return endTime;
	}

	//set方法
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}
	//get方法
	public String getImage() {
		return image;
	}

	//set方法
	public void setImage(String image) {
		this.image = image;
	}
	//get方法
	public String getUrl() {
		return url;
	}

	//set方法
	public void setUrl(String url) {
		this.url = url;
	}
	//get方法
	public String getRemarks() {
		return remarks;
	}

	//set方法
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
