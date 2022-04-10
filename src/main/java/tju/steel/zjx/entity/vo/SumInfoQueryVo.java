package tju.steel.zjx.entity.vo;

import lombok.Data;

import java.util.Objects;

/**
 * 类功能：设置 SumInfo 的查询对象
 */
@Data
public class SumInfoQueryVo {

    private static final long serialVersionUID = 1L;

    /**
     * 根据型钢编号查询
     */
    private String number;

    /**
     * 根据型钢型号查询
     */
    private String type;

    /**
     * 根据型钢长度查询
     */
    private int length;

    /**
     * 根据型钢生产开始时间查询
     */
    private String joinDateBegin;

    /**
     * 根据型钢生产结束时间查询
     */
    private String joinDateEnd;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SumInfoQueryVo that = (SumInfoQueryVo) o;
        return length == that.length &&
                Objects.equals(number, that.number) &&
                Objects.equals(type, that.type) &&
                Objects.equals(joinDateBegin, that.joinDateBegin) &&
                Objects.equals(joinDateEnd, that.joinDateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type, length, joinDateBegin, joinDateEnd);
    }

    @Override
    public String toString() {
        return "SumInfoQueryVo{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", joinDateBegin='" + joinDateBegin + '\'' +
                ", joinDateEnd='" + joinDateEnd + '\'' +
                '}';
    }
}
