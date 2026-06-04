package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {

    private final IStudentMapper studentMapper;

    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception{

        String className = this.getClass().getName();
        log.info("{}.insertStudent start!", className);

        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );
        if (res.isEmpty()) {
            studentMapper.insertStudent(pDTO);
            log.info("학생 등록 완료 - ID: {}", pDTO.getUserId());
        } else {
            log.warn("학생 등록 실패 - 이미 존재하는 ID: {}", pDTO.getUserId());
        }

        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info("{}.insertStudent End!", className);

        return rList;

        }
    public List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception{

        String className = this.getClass().getName();
        log.info("{}.updateStudent start!", className);

        Optional<StudentDTO> res = Optional.ofNullable(studentMapper.getStudent(pDTO));

        if (res.isPresent()) {

            studentMapper.updateStudent(pDTO);

            log.info("{}님이 수정되었습니다.", pDTO.getUserId());
        } else{
            log.warn("수정 실패 - 존재하지 않는 회원입니다 : {}", pDTO.getUserId());
        }

        List<StudentDTO> rList =  Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info("{}.updateStudent End!", className);

        return rList;
        }

    @Override
    public List<StudentDTO> deleteStudent(StudentDTO pDTO) throws Exception{

        String className = this.getClass().getName();
        log.info("{}.deleteStudent start!", className);

        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO));

        if (res.isPresent()) {
            studentMapper.deleteStudent(pDTO);

            log.info("{}님이 삭제되었습니다.", pDTO.getUserId());
        } else {
            log.warn("삭제 실패 - 존재하지 않는 회원입니다: {}", pDTO.getUserId());
        }

        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info("{}.deleteStudent End!", className);

        return rList;
        }

    }


