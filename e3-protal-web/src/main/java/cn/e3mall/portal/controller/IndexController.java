package cn.e3mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by GMY on 2017/9/24.
 */

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex(Model model) {

        return "index";
    }
}
