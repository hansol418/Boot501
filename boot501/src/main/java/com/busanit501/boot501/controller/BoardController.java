package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.BoardDTO;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 화면, 데이터 같이 전달.
@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    //깃 테스트2
    // ex) /board/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("BoardController : /board/list  확인 중, pageRequestDTO : " + pageRequestDTO);

        PageResponseDTO<BoardListReplyCountDTO> responseDTO
                = boardService.listWithReplyCount(pageRequestDTO);
        // 서버로부터 응답확인.
        log.info("BoardController 확인 중, responseDTO : " + responseDTO);
        // 서버 -> 화면 데이터 전달.
        model.addAttribute("responseDTO", responseDTO);

    } //list 닫는 부분

    //글쓰기 폼
    @GetMapping("/register")
    public void registerForm() {
    }

    //글쓰기 처리
    @PostMapping("/register")
    public String register(@Valid BoardDTO boardDTO
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model) {
        // 입력중 유효성 체크에 해당 될 때
        if(bindingResult.hasErrors()) {
            log.info("register 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        log.info("화면에서 입력 받은 내용 확인 : " + boardDTO);

        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        //화면 <- 서버 <- 서비스 <- 레포지토리 <- 디비
        Long bno = boardService.register(boardDTO);

        // 글쓰기 후, 작성된 게시글 번호 -> 화면 , 임시로 전달.(1회용)
        redirectAttributes.addFlashAttribute("result",bno);
        redirectAttributes.addFlashAttribute("resultType","register");
        return "redirect:/board/list";

    }

    //하나 조회 = 상세화면, read
    // 준비물, 해당 게시글 번호로 조회한 데이터가 필요함.
    // 화면 -> 서버로 , bno 게시글 번호를 전달하기.
    @GetMapping({"/read","/update"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model) {

        log.info("BoardController : /board/read  확인 중, pageRequestDTO : " + pageRequestDTO);

        // 디비에서, bno 번호, 하나의 게시글 디비 정보 가져오기.
        BoardDTO boardDTO = boardService.read(bno);
        // 서버로부터 응답확인.
        log.info("BoardController 확인 중, boardDTO : " + boardDTO);
        // 서버 -> 화면 데이터 전달.
        model.addAttribute("boardDTO", boardDTO);

    } //read 닫는 부분

    //글수정 처리
    @PostMapping("/update")
    public String update(@Valid BoardDTO boardDTO
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model
            ,PageRequestDTO pageRequestDTO) {
        // 입력중 유효성 체크에 해당 될 때
        if(bindingResult.hasErrors()) {
            log.info("update 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            // 서버 -> 화면으로 쿼리 스트링으로전달.
            // 예시 : ?bno=게시글번호
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/update"+pageRequestDTO.getLink();
        }
        log.info("화면에서 입력 받은 내용 update 확인 : " + boardDTO);

        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        //화면 <- 서버 <- 서비스 <- 레포지토리 <- 디비
        boardService.update(boardDTO);

        // 글쓰기 후, 작성된 게시글 번호 -> 화면 , 임시로 전달.(1회용)
        redirectAttributes.addFlashAttribute("result",boardDTO.getBno());
        redirectAttributes.addFlashAttribute("resultType","update");

        return "redirect:/board/list?"+pageRequestDTO.getLink2();

    }

    //글삭제 처리
    @PostMapping("/delete")
    public String delete(PageRequestDTO pageRequestDTO, Long bno, RedirectAttributes redirectAttributes
    ) {


        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        //화면 <- 서버 <- 서비스 <- 레포지토리 <- 디비
        boardService.delete(bno);

        // 글쓰기 후, 작성된 게시글 번호 -> 화면 , 임시로 전달.(1회용)
        redirectAttributes.addFlashAttribute("result",bno);
        redirectAttributes.addFlashAttribute("resultType","delete");
        return "redirect:/board/list?"+pageRequestDTO.getLink2();

    }


} // BoardController 닫는 부분
