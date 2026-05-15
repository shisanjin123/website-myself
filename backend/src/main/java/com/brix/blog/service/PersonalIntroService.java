package com.brix.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brix.blog.entity.PersonalIntro;

public interface PersonalIntroService extends IService<PersonalIntro> {

    PersonalIntro getPersonalIntro();
}
