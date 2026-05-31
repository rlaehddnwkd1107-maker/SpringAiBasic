package kopo.poly;

import kopo.poly.dto.NlpDTO;
import kopo.poly.dto.OcrDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SpringAiBasicApplication implements CommandLineRunner {


    private final IOcrService ocrService;

    private final INlpService nlpService;

    public static void main(String[] args) {
        SpringApplication.run(SpringAiBasicApplication.class, args);

    }
//비공개로 커밋되서 다시 커밋할려고 추가하는 주석 ㅎㅎㅎㅎㅎ

    @Override
    public void run(String... args) throws Exception {

        log.info("자바 프로그래밍 시작!!");

        String filePath = "image";
        String fileName = "sample01.jpg";

        OcrDTO pDTO = new OcrDTO();

        pDTO.setFilePath(filePath);
        pDTO.setFileName(fileName);

        OcrDTO rDTO = ocrService.getReadforImageText(pDTO);

        String result = rDTO.getResult();

        log.info("인식된 문자열");
        log.info(result);



        log.info("자바 프로그래밍 종료!!");


        NlpDTO nlpDTO = nlpService.getNouns(result);


        List<String> nouns = nlpDTO.getNouns();

        Set<String> distinct = new HashSet<>(nouns);

        Map<String, Integer> rMap = new HashMap<>();

        for (String s : distinct) {
            int count = Collections.frequency(nouns, s);
            rMap.put(s, count);

            log.info(s, count);
        }



        NlpDTO plainDTO = nlpService.getPlainText(result);

        log.info("형태소별 품사 분석 결과 : " + plainDTO.getResult());

        rMap = new HashMap<>();


        log.info("중복 제거 수행 전 단어 수 : " + nouns.size());
        log.info("중복 제거 수행 후 단어 수 : " + distinct.size());


        for (String s : distinct) {
            int count = Collections.frequency(nouns, s);
            rMap.put(s, count);

            log.info(s + " : " + count);
        }
        List<Map.Entry<String, Integer>> sortResult = new LinkedList<>(rMap.entrySet());
        Collections.sort(sortResult, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        log.info("가장 많이 사용된 단어는? : " + sortResult);

        log.info("자바 프로그래밍 종료!!");


    }


}

