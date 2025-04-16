package com.dev.loja_virtual;

import static org.junit.Assert.assertEquals;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dev.loja_virtual.controller.AcessController;
import com.dev.loja_virtual.exception.ExceptionMsg;
import com.dev.loja_virtual.model.Acess;
import com.dev.loja_virtual.repository.AcessRepository;
import com.dev.loja_virtual.service.AcessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

@SpringBootTest
class LojaVirtualApplicationTests {
	
	@Autowired
	private AcessService acessService;
	
	@Autowired
	private AcessController acessController;
	
	@Autowired
	private AcessRepository acessRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	

}
