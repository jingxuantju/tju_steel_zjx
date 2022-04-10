package tju.steel.zjx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import tju.steel.zjx.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="ParamInfo对象", description="")
public class ParamInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "型钢规格")
    private String specification;

    @ApiModelProperty(value = "型钢长度")
    private String length;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamInfo paramInfo = (ParamInfo) o;
        return Objects.equals(specification, paramInfo.specification) &&
                Objects.equals(length, paramInfo.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specification, length);
    }

    @Override
    public String toString() {
        return "ParamInfo{" +
                "specification='" + specification + '\'' +
                ", length='" + length + '\'' +
                '}';
    }
}
