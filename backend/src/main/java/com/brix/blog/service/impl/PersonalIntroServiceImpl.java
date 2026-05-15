package com.brix.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brix.blog.entity.PersonalIntro;
import com.brix.blog.mapper.PersonalIntroMapper;
import com.brix.blog.service.PersonalIntroService;
import org.springframework.stereotype.Service;

@Service
public class PersonalIntroServiceImpl extends ServiceImpl<PersonalIntroMapper, PersonalIntro> implements PersonalIntroService {

    @Override
    public PersonalIntro getPersonalIntro() {
        return getOne(null);
    }
}
