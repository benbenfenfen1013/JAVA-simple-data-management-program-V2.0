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
    Integer NextId = 8;

    private Mission findById(Integer id){
        if (id == null) return new Mission();
        for (Mission m:missions){
            if (id.equals(m.getId())){
                return m;
            }
        }
        return null;
    }
    

    @GetMapping ("/")
    public String redirect(){
        return "redirect:/search";
    }

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

    // Mission edition = null;
    @PostMapping("/save")
    public String SaveEdition(Mission edition) {
        if (edition.getId()==null){
            edition.setId(NextId);
            missions.add(edition);
            NextId += 1;
        }else{
            Integer id = edition.getId();
            missions.set(id-1,edition);
        }
        edition = null;
        return "redirect:/search";
    }

    @GetMapping("/save")
    public String Edition(){
        return "form";
    }

    @GetMapping("/{id}/edit")
    public String StartEdit(@PathVariable Integer id,Model model){
        model.addAttribute("id",id);
        Mission m = findById(id);
        model.addAttribute("mission",m);
        return "form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        int position = 0;
        for (Mission m : missions){
            if (m.getId().equals(id)){
                missions.remove(position);
                return "redirect:/search";
            }
            position += 1;
        }
        return "redirect:/search";
    }
    
}