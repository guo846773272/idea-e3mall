package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by GMY on 2017/9/23.
 */
public interface ItemCatService {

    List<EasyUITreeNode> getItemCatlist(long parentId);
}
