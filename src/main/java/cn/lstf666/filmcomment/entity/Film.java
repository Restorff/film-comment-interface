package cn.lstf666.filmcomment.entity;

//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Re5t0rff
 * @since 2020-12-06
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Film对象", description="")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电影id")
//    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    @ApiModelProperty(value = "电影名")
    private String filmname;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "评分")
    private Float score;

    @ApiModelProperty(value = "电影有多少人评分")
    private Integer scorePeople;

    @ApiModelProperty(value = "演员表")
    private String actor;

    @ApiModelProperty(value = "封面图片url")
    private String img;

    @ApiModelProperty(value = "地区")
    private String regions;

    @ApiModelProperty(value = "电影简介")
    private String intro;

    @ApiModelProperty(value = "上映日期")
    private Date date1;

    @ApiModelProperty(value = "导演")
    private String director;


}
