package com.spring1.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring1.dto.Custom;
import com.spring1.service.CustomService;

@Controller
@RequestMapping("/email/")
public class EmailController {
	
	private static final Logger log = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private JavaMailSenderImpl mailSender; //네이버 메일
	
	@Autowired
	private JavaMailSenderImpl mailSender2; //지메일
	
	@Autowired
	private JavaMailSenderImpl mailSender3; //다음 메일

	@Autowired
	private String uploadPath;
	
	@Autowired
	private CustomService customService;
	
	@RequestMapping(value = "sendMail", method = RequestMethod.POST)
    public void sendMailTest(HttpServletRequest req, Model model) throws Exception{
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.            
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            mailSender.send(mail);
        } catch(Exception e) {
            e.printStackTrace();
        }        
    }

	
	
    @RequestMapping(value = "sendMail2", method = RequestMethod.GET)
    public void sendMail2(HttpServletRequest req, Model model) throws Exception{
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String from = req.getParameter("from");
        String to = req.getParameter("to");       
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception{
                final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");                
                mailHelper.setFrom(from);
                mailHelper.setTo(to);
                mailHelper.setSubject(subject);
                mailHelper.setText(content, true);
            }            
        };
        try {    
            mailSender.send(preparator);
        } catch(Exception e) {
            e.printStackTrace();
        }        
    }
    
    
    
    @RequestMapping(value = "sendMail3", method = RequestMethod.GET)
    public void sendMail3(MultipartHttpServletRequest multi, Model model) throws Exception{
    	
    	String root = multi.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/upload/";
    	
    	File dir = new File(path);
    	
    	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
    		dir.mkdir();
    	}
    	
    	Iterator<String> files = multi.getFileNames();
    	
    	String fileName_original = "";
    	String uploadFile = "";
    	MultipartFile mFile;
    	if(files.hasNext()) {
    		uploadFile = files.next();
    		mFile = multi.getFile(uploadFile);
    		fileName_original = mFile.getOriginalFilename();
    		try {
    			mFile.transferTo(new File(uploadPath+fileName_original));
    			log.info("전송된 파일 이름 : "+fileName_original);
    			log.info("전송된 파일 크기 : "+mFile.getSize());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
        String subject = multi.getParameter("subject");
        String content = multi.getParameter("content") + "<img src='"+fileName_original+"'>";
        String from = multi.getParameter("from");
        String to = multi.getParameter("to");
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            mailSender.send(mail);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "sendMail4", method = RequestMethod.GET)
    public void sendMail4(MultipartHttpServletRequest multi, Model model) throws Exception{
    	String root = multi.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/upload/";    	
    	File dir = new File(path);
    	if(!dir.isDirectory()) {	//해당 디렉토리가 없는 경우 디렉토리 생성
    		dir.mkdir();
    	}
    	Iterator<String> files = multi.getFileNames();
    	String fileName_original = "";
    	String uploadFile = "";
    	MultipartFile mFile;
    	
    	if(files.hasNext()) {
    		uploadFile = files.next();
    		mFile = multi.getFile(uploadFile);
    		fileName_original = mFile.getOriginalFilename();
    		try {
    			mFile.transferTo(new File(uploadPath+fileName_original));
    			log.info("전송된 파일 이름 : "+fileName_original);
    			log.info("전송된 파일 크기 : "+mFile.getSize());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
        String subject = multi.getParameter("subject");
        String content = multi.getParameter("content");
        String from = multi.getParameter("from");
        String to = multi.getParameter("to");
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(uploadPath+fileName_original)); 
            mailHelper.addAttachment(fileName_original, file);
            mailSender.send(mail);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //이메일 인증번호 보내기 
    @RequestMapping("findAuth")
    @ResponseBody
    public Map findAuth(@ModelAttribute("vo") Custom vo, Model model) {
        
        Map map = new HashMap();
        
        //사용자가 작성한 아이디를 기준으로 존재하는 사용자인지 확인한다.(id는 회원가입시 중복 체크를 했기 때문에 유니크하다.)
        Custom isUser = customService.getCustom(vo.getId());
        
        if(isUser != null) {//회원가입이 되어있는, 존재하는 사용자라면
            Random r = new Random();
            int num = r.nextInt(999999); //랜덤 난수 
            
            StringBuilder sb = new StringBuilder();
            
            // DB에 저장된 email            입력받은 email
            if(isUser.getEmail().equals(vo.getEmail())) {//이메일 정보 또한 동일하다면 
        
                String setFrom = "관리자이메일";//발신자 이메일
                String tomail = isUser.getEmail();//수신자 이메일
                String title = "비밀번호 변경 인증 이메일입니다.";
                sb.append(String.format("안녕하세요 %s님\n", isUser.getName()));
                sb.append(String.format("비밀번호 찾기(변경) 인증번호는 %d입니다.", num));
                String content = sb.toString();
                
                try {
                    MimeMessage msg = mailSender.createMimeMessage();
                    MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "utf-8");
                    msgHelper.setFrom(setFrom);
                    msgHelper.setTo(tomail);
                    msgHelper.setSubject(title);
                    msgHelper.setText(content);
                    mailSender.send(msg);                    //메일 전송                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                //성공적으로 메일을 보낸 경우
                map.put("status", true);
                map.put("num", num);
                map.put("m_idx", isUser.getId());                
            }
        }
		return map;
    }    
}