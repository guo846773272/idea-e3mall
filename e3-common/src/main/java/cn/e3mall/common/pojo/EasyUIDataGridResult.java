package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GMY on 2017/9/23.
 */
public class EasyUIDataGridResult implements Serializable {

    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public List getRows() {
        return rows;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
