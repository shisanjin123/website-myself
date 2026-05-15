package com.brix.blog.controller;

import com.brix.blog.entity.PersonalIntro;
import com.brix.blog.service.PersonalIntroService;
import com.brix.blog.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "个人介绍", description = "个人介绍管理")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalIntroController {

    private final PersonalIntroService personalIntroService;

    @Operation(summary = "获取个人介绍")
    @GetMapping("/personal")
    public Result<PersonalIntro> getPersonalIntro() {
        return Result.success(personalIntroService.getPersonalIntro());
    }

    @Operation(summary = "更新个人介绍")
    @PutMapping("/admin/personal")
    public Result<?> updatePersonalIntro(@RequestBody PersonalIntro personalIntro) {
        personalIntroService.updateById(personalIntro);
        return Result.success("更新成功");
    }
}
