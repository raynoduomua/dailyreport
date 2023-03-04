package com.example.dailyreport.application.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.form_validation.ClientForm;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.domain.service.admin.AdminClientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminClientController {

    private final AdminClientService adminClientService;

    /**
     * クライアント名登録画面表示
     * http://localhost:8080/admin/create-client
     * 
     * @param clientForm Formクラス
     * @return クライアント名登録画面
     */
    @GetMapping("/create-client")
    public String viewCreateClient(@ModelAttribute("clientForm") ClientForm clientForm) {

        return "admin/client/createclient";
    }

    /**
     * クライアント名登録処理
     * 
     * @param clientForm    Formクラス
     * @param bindingResult バリデーションチェック
     * @return クライアント名登録画面
     */
    @PostMapping("/save-client")
    public String saveClient(@Validated(GroupOrder.class) @ModelAttribute("clientForm") ClientForm clientForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return viewCreateClient(clientForm);
        }

        this.adminClientService.saveCourse(clientForm);

        return "redirect:/admin/create-client?save";
    }

    /**
     * クライアント名一覧画面
     * http://localhost:8080/admin/list-client
     * 
     * @param model Modelクラス
     * @return クライアント名一覧画面
     */
    @GetMapping("/list-client")
    public String viewClientList(Model model) {

        model.addAttribute("clients", this.adminClientService.viewClientList());

        return "admin/list/client";
    }
}
