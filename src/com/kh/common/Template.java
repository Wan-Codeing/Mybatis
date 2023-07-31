package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//마이바티스 탬플릿
public class Template {
	
	
	public static SqlSession getSqlSession() {
		//mybatis-config.xml파일을 읽어들여서 해당 DB와 접속된 SqlSession객체를 생성해서 반환
		SqlSession sqlSession = null;
		
		// SqlSession객체를생성하기 위해서 sqlSessionFactory객체가 필요
		// SqlSessionFactory를 생성하기 위해서는 SqllSessionFactoryBuilder가 필요
		
		String resource = "/mybatis-config.xml"; // /는 최상위 경로 폴더를 의미
	 	
		InputStream stream;
		try {
			stream = Resources.getResourceAsStream(resource);
			/*
			 * 1단계) new SqlSessionFactoryBuilder() : sqlSessionFactoryBuilder객체 생성
			 * 2단계) .build(stream) : 입력스트림으로부터 mybatis-config.xml파일을 읽어드리면서 sqlSessionFactory객체를 생성
			 * 3단계) .openSession(false) : sqlSession객체 생성 및 앞으로 트랜잭션 처리시 자동 커밋할것인지 안할건지 여부를 지정  => false == 자동커밋 사용하지 않겠다.
			 */
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession;
	}
}
