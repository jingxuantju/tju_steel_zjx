package tju.steel.zjx.entity;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tju.steel.zjx.model.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ImgInfo对象", description="")
public class Member extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ExcelProperty("用户名")
    @ApiModelProperty(value = "用户名")
    @TableField("nickname")
    private String nickname;


    @ExcelProperty("密码")
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;


    @ExcelIgnore
    @ApiModelProperty(value = "逻辑删除，1(true)")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;
}
