package cn.e3mall.content.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    @Override
    public E3Result addContent(TbContent content) {
        //将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入到数据库
        tbContentMapper.insert(content);
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        return E3Result.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {

        try {

            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if (StringUtils.isNotBlank(json)) {
                System.out.println("从缓存获取");
                List list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("查数据库");
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

        try {
            System.out.println("写入缓存");
            jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
