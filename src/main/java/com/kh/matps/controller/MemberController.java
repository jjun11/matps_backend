package com.kh.matps.controller;


import com.kh.matps.dao.MemberDAO;
import com.kh.matps.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; //



@CrossOrigin(origins = "http://localhost:3000") // CrossOrigin 어노테이션을 통해 특정 origin(여기서는 http://localhost:3000)에서의 요청을 허용한다.
@RestController // RestController 어노테이션은 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타낸다.
@RequestMapping("/users") // RequestMapping 어노테이션은 클래스에 대한 기본 경로를 지정한다.
public class MemberController {

    // GET : 회원 조회
    @GetMapping("/member") // @GetMapping 어노테이션은 GET 방식의 요청을 처리한다.
    public ResponseEntity<List<MemberVO>> memberList(@RequestParam String name) { // @RequestParam 어노테이션은 요청 파라미터의 값을 메소드의 파라미터로 전달한다.
        System.out.println("NAME : " + name); // 콘솔에 이름을 출력한다.
        MemberDAO dao = new MemberDAO(); // MemberDAO 객체를 생성하여 회원 조회를 수행한다.
        List<MemberVO> list = dao.memberSelect(name); // MemberDAO 객체의 memberSelect 메소드를 호출한다.
        return new ResponseEntity<>(list, HttpStatus.OK); // 조회된 회원 목록을 ResponseEntity에 담아 반환한다.
    }

    // POST : 로그인
    @PostMapping("/login") // @PostMapping 어노테이션은 POST 방식의 요청을 처리한다.
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) { // @RequestBody 어노테이션은 요청 본문에 담긴 데이터를 메소드의 파라미터로 전달한다.
        String user = loginData.get("id"); // 전송된 로그인 데이터에서 아이디와 비밀번호를 추출한다.
        String pwd = loginData.get("pwd"); // 각각 user, pwd 변수에 저장한다.
        System.out.println("ID : " + user);  // 콘솔에 아이디와 비밀번호를 출력한다.
        System.out.println("PWD : " + pwd); // 각각 "ID : " + user, "PWD : " + pwd 형식으로 출력한다.
        MemberDAO dao = new MemberDAO();  // MemberDAO 객체를 생성하여 로그인 확인을 수행한다.
        boolean result = dao.loginCheck(user, pwd); // MemberDAO 객체의 loginCheck 메소드를 호출한다.
        return new ResponseEntity<>(result, HttpStatus.OK); // 로그인 결과를 ResponseEntity에 담아 반환한다.

    }

    // GET : ID 중복 여부 확인
    @GetMapping("/checkId") // @GetMapping 어노테이션은 GET 방식의 요청을 처리한다.
    public ResponseEntity<Boolean> memberCheckId(@RequestParam String id) { // @RequestParam 어노테이션은 요청 파라미터의 값을 메소드의 파라미터로 전달한다.
        System.out.println("회원 가입 여부 확인 ID : " + id); // 콘솔에 가입 여부 확인을 위한 아이디를 출력한다.
        MemberDAO dao = new MemberDAO(); // MemberDAO 객체를 생성\
        boolean isTrue = dao.regMemberCheckId(id); // MemberDAO 객체의 regMemberCheck 메소드 내 id를 인자로 전달하여 회원 가입 여부를 확인한다.
        return new ResponseEntity<>(isTrue, HttpStatus.OK); // 확인된 결과를 ResponseEntity에 담아 반환한다.


      }

    // GET : 닉네임 중복 여부 확인
    @GetMapping("/checkNick") // @GetMapping 어노테이션은 GET 방식의 요청을 처리한다.
    public ResponseEntity<Boolean> memberCheckNick  (@RequestParam String nick) { // @RequestParam 어노테이션은 요청 파라미터의 값을 메소드의 파라미터로 전달한다.
        System.out.println("회원 가입 여부 확인 NICK : " + nick); // 콘솔에 가입 여부 확인을 위한 닉네임을 출력한다.
        MemberDAO dao = new MemberDAO(); // MemberDAO 객체를 생성\
        boolean isTrue = dao.regMemberCheckNick(nick); // MemberDAO 객체의 regMemberCheck 메소드 내 nick를 인자로 전달하여 회원 가입 여부를 확인한다.
        return new ResponseEntity<>(isTrue, HttpStatus.OK); // 확인된 결과를 ResponseEntity에 담아 반환한다.


    }

    // POST : 회원 가입
    @PostMapping("/sign") // @PostMapping 어노테이션은 POST 방식의 요청을 처리한다.
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) { // @RequestBody 어노테이션은 요청 본문에 담긴 데이터를 메소드의 파라미터로 전달한다.
        // 전송된 회원 가입 데이터에서 필요한 정보를 추출한다.
        String getId = regData.get("id"); // 회원 가입 데이터에서 id를 추출해서 getId 변수에 저장한다.
        String getPwd = regData.get("pwd"); // 회원 가입 데이터에서 pwd를 추출해서 getPwd 변수에 저장한다.
        String getMail = regData.get("mail"); // 회원 가입 데이터에서 mail을 추출해서 getMail 변수에 저장한다.
        String getName = regData.get("name");  // 회원 가입 데이터에서 name을 추출해서 getName 변수에 저장한다.
        String getNick = regData.get("nick"); // 회원 가입 데이터에서 nick을 추출해서 getNick 변수에 저장한다.
        String getGender = regData.get("gender"); // 회원 가입 데이터에서 gender을 추출해서 getGender 변수에 저장한다.
        // MemberDAO 객체를 생성하여 회원 가입을 수행한다.
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.memberRegister(getId, getPwd, getMail, getName, getNick, getGender); // MemberDAO 객체의 memberRegister 메소드의 getId, getPwd, getMail, getName, getNick, getGender를 인자로 전달하여 회원 가입을 수행한다.
        // 회원 가입 결과를 ResponseEntity에 담아 반환한다.
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 회원 탈퇴
    @PostMapping("/del") // @PostMapping 어노테이션은 POST 방식의 요청을 처리한다.
    public ResponseEntity<Boolean> memberDelete(@RequestBody Map<String, String> delData) { // @RequestBody 어노테이션은 요청 본문에 담긴 데이터를 메소드의 파라미터로 전달한다.
        // 전송된 회원 탈퇴 데이터에서 아이디를 추출한다.
        String getId = delData.get("id"); // 회원 탈퇴 데이터에서 id를 추출해서 getId 변수에 저장한다.
        // MemberDAO 객체를 생성하여 회원 탈퇴를 수행한다.
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.memberDelete(getId); // MemberDAO 객체의 memberDelete 메소드의 getId를 인자로 전달하여 회원 탈퇴를 수행한다.
        // 회원 탈퇴 결과를 ResponseEntity에 담아 반환한다.
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
}