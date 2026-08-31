package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

    /**
     * TODO一覧を取得し、一覧画面を表示する。
     *
     * @param model ビューへ渡すデータを保持するModel
     * @return TODO一覧画面のビュー名
     */
    @GetMapping
    public String index(Model model) {
        return "index";
    }

    /**
     * TODO登録画面を表示する。
     *
     * @return TODO登録画面のビュー名
     */
    @GetMapping("/add")
    public String add() {
        return "add";
    }

    /**
     * TODO更新画面を表示する。
     *
     * @return TODO更新画面のビュー名
     */
    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    /**
     * 特定タスクの変更履歴一覧画面を表示する
     *
     * @param id タスクID
     * @param model 画面へ渡すデータを保持するオブジェクト
     * @return 履歴一覧画面のHTMLパス (history.html)
     */
    @GetMapping("/history/{id}")
    public String showHistory(@PathVariable("id") Long id, Model model) {
        return "history";
    }
}
