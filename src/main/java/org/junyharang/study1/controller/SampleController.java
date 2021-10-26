package org.junyharang.study1.controller;

import lombok.extern.log4j.Log4j2;
import org.junyharang.study1.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller @RequestMapping("/sample") @Log4j2
public class SampleController {

    @GetMapping("/ex1") public void ex1() {

        log.info("ex1...............");

    } // ex1() 끝

    @GetMapping({"/ex2", "exLink"})
    public void exModel(Model model) {
        List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
            SampleDTO dto = SampleDTO.builder()
                    .sno(i)
                    .first("첫번째 " + i)
                    .last("마지막 " + i)
                    .regTime(LocalDateTime.now())
                    .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
    } //exModel() 끝

    @GetMapping({"exInline"})
    public String exInline(RedirectAttributes redirectAttributes) {

        log.info("exInline이 동작 중 입니다!");

        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("첫번째 값은 100이에요!")
                .last("마지막 값은 100이에요!")
                .regTime(LocalDateTime.now())
                .build();

        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/sample/ex3";
    } // exInline() 끝

    @GetMapping("/ex3") public void ex3() {

        log.info("ex3가 동작 중 입니다!");

    } // ex3() 끝

    @GetMapping({"/exLayout1", "/exLayout2" ,"exTemplate", "exSidebar"})
    public void exLayout1() {

        log.info("exLayout이 동작 중 입니다!");

    } // exLayout1() 끝
} // class 끝
