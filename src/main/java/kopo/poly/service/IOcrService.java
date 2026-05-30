package kopo.poly.service;

import kopo.poly.dto.OcrDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface  IOcrService {

    String modelFile = "C:/model/tessdata";

    OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception;
}
