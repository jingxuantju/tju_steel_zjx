package tju.steel.zjx.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import tju.steel.zjx.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ImgInfo对象", description="")
public class ImgInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ExcelProperty("型钢编号")
    @ApiModelProperty(value = "图像所属型钢编号")
    @TableField("number")
    private String number;

    @ExcelProperty("缺陷所属面")
    @ApiModelProperty(value = "缺陷图像所属面")
    private String surface;

    @ExcelProperty("缺陷类型")
    @ApiModelProperty(value = "缺陷类型")
    private String type;


    @ExcelProperty("缺陷位置(m)")
    @ApiModelProperty(value = "缺陷位置(m)")
    private Double length;

    @ExcelIgnore
    @ApiModelProperty(value = "缺陷图像存储位置")
    private String link;

    @ExcelIgnore
    @ApiModelProperty(value = "逻辑删除，1(true)")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImgInfo imgInfo = (ImgInfo) o;
        return super.getId() == imgInfo.getId() &&
                Double.compare(imgInfo.length, length) == 0 &&
                Objects.equals(number, imgInfo.number) &&
                Objects.equals(surface, imgInfo.surface) &&
                Objects.equals(type, imgInfo.type) &&
                Objects.equals(link, imgInfo.link) &&
                Objects.equals(isDeleted, imgInfo.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), number, surface, type, length, link, isDeleted);
    }

    @Override
    public String toString() {
        return "ImgInfo{" +
                "id=" + super.getId() +
                ", number='" + number + '\'' +
                ", surface='" + surface + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", link='" + link + '\'' +
                ", deleted=" + isDeleted +
                '}';
    }
}
