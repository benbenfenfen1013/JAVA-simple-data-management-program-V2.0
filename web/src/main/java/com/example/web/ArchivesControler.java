package com.example.web;
import com.example.web.model.*;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArchivesControler {
    ArrayList<Mission>missions = new ArrayList<>();
    public ArchivesControler(){
        missions.add(new Mission(1, "斯普特尼克一号", "苏联", "1957", "成功"));
        missions.add(new Mission(2, "东方号", "苏联", "1961", "成功"));
        missions.add(new Mission(3, "阿波罗11号", "美国", "1969", "成功"));
        missions.add(new Mission(4, "东方红一号", "中国", "1970", "成功"));
        missions.add(new Mission(5, "水手8号", "美国", "1971", "失败"));
        missions.add(new Mission(6, "火星7号", "苏联", "1973", "失败"));
        missions.add(new Mission(7, "神舟五号", "中国", "2003", "成功")); 
    }
    @GetMapping ("/")
    public String redirect(){
        return "redirect:/search";
    }


    // @GetMapping ("/search")
    // public String home(Model model){
    //     model.addAttribute("result",missions);
    //     return "Website";
    // }
    @GetMapping ("/search")
    public String list(@RequestParam(required = false) String keyword,Model model){
        ArrayList<Mission>result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()){
            result = missions;
        }else{
            for (Mission m:missions){
                if (m.getName().contains(keyword) || m.getCountry().contains(keyword) || m.getDate().contains(keyword) || m.getResult().contains(keyword)){
                    result.add(m);
                }
            }
        }
        int total = result.size();
        double success = 0.00;
        double fail = 0.00;
        for (Mission i:result){
            if (i.getResult().contains("成功")){
                success += 1;
            }else{
                fail += 1;
            }
        }
        double rate01 = success/(success + fail);
        String rate02 = String.format("%.2f%%" , rate01*100);
        model.addAttribute("total",total);
        model.addAttribute("success",success);
        model.addAttribute("fail",fail);
        model.addAttribute("rate",rate02);
        model.addAttribute("result",result);
        model.addAttribute("keyword",keyword);
        return ("Website");
    }
}