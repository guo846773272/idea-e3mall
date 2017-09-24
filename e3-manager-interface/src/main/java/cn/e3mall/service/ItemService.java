package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;

/**
 * Created by GMY on 2017/9/23.
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int page, int rows);

    E3Result addItem(TbItem item, String desc);
}
