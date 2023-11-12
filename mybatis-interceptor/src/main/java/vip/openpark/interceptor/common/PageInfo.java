package vip.openpark.interceptor.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/12 19:52
 */
@Data
public class PageInfo<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4987390851912052717L;

    private Long total;
    private Long pages;
    private Integer pageSize;
    private Integer pageNum;
    private List<T> dataList;

    private Integer startIndex; // 索引的起始位置
    private Integer totalSelect; // 检索的总数目


    public void count() {
        this.pages = (this.total - 1) / this.pageSize + 1; // 总页数

        if (this.pages < this.pageNum) {
            this.pageNum = this.pages.intValue();
        }
        if (this.pageNum < 1) {
            this.pageNum = 1;
        }

        this.startIndex = (this.pageNum - 1) * this.pageSize;
        this.totalSelect = this.pageSize;
    }
}