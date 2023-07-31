package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 인터페이스 구현
public class MyFileRenamePolicy implements FileRenamePolicy{
	
	// 미완성된 rename함수 오버라이딩하기
	// 기존의 파일을 매개변수로 전달받아서 수정작업을한  후 해당 파일을 반환해주는 메소드
	
	@Override
	public File rename(File originFile){
		// 원본파일 ("aaa.jpg")
		String originName = originFile.getName();
		
		// 수정 파일명 : 파일업로드시간(년월일시분초) + 5자리 랜덤값
		// 확장자 	: 원본파일 확장자 그대로
		
		// 원본명 	=> 수정명
		// aaa.jpg 	=> 2023070312381112345.jpg
		
		// 1. 파일 업로드된 시간 => String currentTime;
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 2. 5자리 랜덤값 = int ran;
		int ran = (int)(Math.random() * 90000) + 100000;
		
		// 3. 원본파일에 확장자(String ext)
		// 원본파일에서 갖아 마지막위치의 .의 인덱스를 기준으로 확장자 구하기(파일명 중간에 .이 들ㅇ거ㅏ는 케이스도 있기 떄문)
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = currentTime + ran + ext;
		
		// 원본 파일을 수정된 파일명으로 적용시켜서 파일객체 반환
		return new File(originFile.getParent(), changeName);
	}
}
