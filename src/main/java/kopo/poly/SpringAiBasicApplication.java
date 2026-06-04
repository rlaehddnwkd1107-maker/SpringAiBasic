package kopo.poly;

import kopo.poly.dto.NlpDTO;
import kopo.poly.dto.OcrDTO;
import kopo.poly.dto.StudentDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import kopo.poly.service.IStudentService;
import kopo.poly.service.impl.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
//@MapperScan("kopo.poly.mapper")
@SpringBootApplication
public class SpringAiBasicApplication implements CommandLineRunner {


//    private final IOcrService ocrService;
//
//    private final INlpService nlpService;

    private final IStudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(SpringAiBasicApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {

        log.info("자바 프로그래밍 시작!!");

        StudentDTO pDTO;
        List<StudentDTO> rList;

        pDTO = new StudentDTO();

        pDTO.setUserId("hglee67");
        pDTO.setUserName("이협건");
        pDTO.setEmail("hglee67@kopo.ac.kr");
        pDTO.setAddr("서울");


        rList = studentService.insertStudent(pDTO);

        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디: " + dto.getUserId());
            log.info("DB에 저장된 이름: " + dto.getUserName());
            log.info("DB에 저장된 이메일: " + dto.getEmail());
            log.info("DB에 저장된 주소: " + dto.getAddr());
        });
        //수정하기

        pDTO = new StudentDTO();

        pDTO.setUserId("hglee67");
        pDTO.setUserName("이협건_수정");
        pDTO.setEmail("hglee67@kopo.ac.kr_수정");
        pDTO.setAddr("서울_수정");

        rList = studentService.updateStudent(pDTO);

        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름: " + dto.getUserName());
            log.info("DB에 저장된 이메일: " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });

        pDTO = new StudentDTO();

        pDTO.setUserId("hglee67");

        rList = studentService.deleteStudent(pDTO);

        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름: " + dto.getUserName());
            log.info("DB에 저장된 이메일: " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });

        log.info("자바 프로그래밍 종료!!");

        }

//        String filePath = "image";
//        String fileName = "sample01.jpg";
//        OcrDTO pDTO = new OcrDTO();
//
//        pDTO.setFilePath(filePath);
//        pDTO.setFileName(fileName);
//
//        OcrDTO rDTO = ocrService.getReadforImageText(pDTO);
//
//        String result = rDTO.getResult();
//
//        log.info("인식된 문자열");
//        log.info(result);
//
//
//
//        log.info("자바 프로그래밍 종료!!");
//
//
//        NlpDTO nlpDTO = nlpService.getNouns(result);
//
//
//        List<String> nouns = nlpDTO.getNouns();
//
//        Set<String> distinct = new HashSet<>(nouns);
//
//        Map<String, Integer> rMap = new HashMap<>();
//
//        for (String s : distinct) {
//            int count = Collections.frequency(nouns, s);
//            rMap.put(s, count);
//
//            log.info(s, count);
//        }
//
//
//
//
//        NlpDTO plainDTO = nlpService.getPlainText(result);
//
//        log.info("형태소별 품사 분석 결과 : " + plainDTO.getResult());
//
//        rMap = new HashMap<>();
//
//
//        log.info("중복 제거 수행 전 단어 수 : " + nouns.size());
//        log.info("중복 제거 수행 후 단어 수 : " + distinct.size());
//
//
//        for (String s : distinct) {
//            int count = Collections.frequency(nouns, s);
//            rMap.put(s, count);
//
//            log.info(s + " : " + count);
//        }
//        List<Map.Entry<String, Integer>> sortResult = new LinkedList<>(rMap.entrySet());
//        Collections.sort(sortResult, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//
//        log.info("가장 많이 사용된 단어는? : " + sortResult);
//
//        log.info("자바 프로그래밍 종료!!");
//




}

