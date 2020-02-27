package com.roc.javaweb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roc.javaweb.domain.Msg;
import com.roc.javaweb.service.EduService;
import com.roc.javaweb.service.MsgService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JavaWebApplicationTests {

	@Autowired
	private EduService eduService;

	@Autowired
	private MsgService msgService;

	@Test
	void contextLoads() {
		IPage<Msg> page = msgService.page(new Page<Msg>(1, 3).setDesc("time"));
		List<Msg> records = page.getRecords();
		System.out.println(records);
	}

}
