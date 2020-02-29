package org.szh.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Document(collection = "QQmails")//指定文档名称名称
@Data
public class MailMsg implements Serializable{
    private static final long serialVersionUID = 8523817739815358421L;

    @ApiModelProperty(value = "mailID", hidden = true)
    @Id
    private String mailId;
 
    @ApiModelProperty(value = "收件人")
    private String to;
    
    @ApiModelProperty(value = "主题")
    private String subject;
 
    @ApiModelProperty(value = "内容")
    private String content;
 
    @ApiModelProperty(value = "创建时间", hidden = true)
    @JsonFormat(pattern = "yyyy-mm-dd HH：mm",timezone = "GMT+8")
    private Date sendDt;
}
