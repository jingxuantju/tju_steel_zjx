package tju.steel.zjx.entity.vo;

import lombok.Data;

import java.util.Objects;

/**
 * 类功能：设置 ImgInfo 查询对象
 */
@Data
public class ImgInfoQueryVo {

    private static final long serialVersionUID = 1L;

    /**
     * 根据缺陷所属型钢编号查询
     */
    private String number;

    /**
     * 根据缺陷所属型钢面查询
     */
    private String surface;

    /**
     * 根据缺陷类型查询
     */
    private String type;

    /**
     * 根据缺陷起始位置查询
     */
    private double startLength;

    /**
     * 根据缺陷终止位置查询
     */
    private double endLength;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImgInfoQueryVo that = (ImgInfoQueryVo) o;
        return Double.compare(that.startLength, startLength) == 0 &&
                Double.compare(that.endLength, endLength) == 0 &&
                Objects.equals(number, that.number) &&
                Objects.equals(surface, that.surface) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, surface, type, startLength, endLength);
    }

    @Override
    public String toString() {
        return "ImgInfoQueryVo{" +
                "number='" + number + '\'' +
                ", surface='" + surface + '\'' +
                ", type='" + type + '\'' +
                ", startLength=" + startLength +
                ", endLength=" + endLength +
                '}';
    }
}
