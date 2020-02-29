package org.szh.beans;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Document(collection = "notes")
@Data
public class Note implements Serializable{
    
    private static final long serialVersionUID = 4140225914859914176L;
    
    @Id
    private String noteId;
    
    @ApiModelProperty(value = "标题")
    private String title;
    
    @ApiModelProperty(value = "作者")
    private String author;
    
    @ApiModelProperty(value = "内容")
    private String content ;
}
