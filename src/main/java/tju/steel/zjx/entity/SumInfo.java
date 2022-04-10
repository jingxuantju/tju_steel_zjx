package tju.steel.zjx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import tju.steel.zjx.model.BaseEntity;
import java.util.Date;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="SumInfo对象", description="")
public class SumInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "型钢编号")
    private String number;

    @ApiModelProperty(value = "型钢型号")
    private String type;

    @ApiModelProperty(value = "型钢开口度")
    private String opening;

    @ApiModelProperty(value = "型钢通条 B 值")
    private String bValue;

    @ApiModelProperty(value = "型钢表面缺陷个数,默认为0个")
    private Integer defects;

    @ApiModelProperty(value = "型钢长度，默认为 0m")
    private Integer length;

    @ApiModelProperty(value = "型钢生产时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date joinDate;

    @ApiModelProperty(value = "该条型钢是否已经查看过，0 表示未查看，1 表示已查看")
    private Integer viewed;

    @ApiModelProperty(value = "逻辑删除，1表示已删除")
    @TableLogic
    private Integer isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SumInfo sumInfo = (SumInfo) o;
        return super.getId() == sumInfo.getId() &&
                defects == sumInfo.defects &&
                length == sumInfo.length &&
                viewed == sumInfo.viewed &&
                Objects.equals(number, sumInfo.number) &&
                Objects.equals(type, sumInfo.type) &&
                Objects.equals(opening, sumInfo.opening) &&
                Objects.equals(bValue, sumInfo.bValue) &&
                Objects.equals(joinDate, sumInfo.joinDate) &&
                Objects.equals(isDeleted, sumInfo.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), number, type, opening, bValue, defects, length, joinDate,isDeleted, viewed);
    }

    @Override
    public String toString() {
        return "SumInfo{" +
                "id=" + super.getId() +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", opening='" + opening + '\'' +
                ", bValue='" + bValue + '\'' +
                ", defects=" + defects +
                ", length=" + length +
                ", joinDate=" + joinDate +
                ", deleted=" + isDeleted +
                ", viewed=" + viewed +
                '}';
    }
}
