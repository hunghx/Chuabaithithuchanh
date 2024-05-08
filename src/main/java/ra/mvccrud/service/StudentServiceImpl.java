package ra.mvccrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ra.mvccrud.dto.request.StudentRequest;
import ra.mvccrud.entity.Student;
import ra.mvccrud.repository.StudentRepository;

import org.modelmapper.ModelMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService{
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;
    @Override
    public List<Student> findALl() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findALlByName(String keyword) {
        return studentRepository.findAllByNameContainingOrAddressContaining(keyword,keyword);
    }

    @Override
    public StudentRequest findById(Integer id) {
        return mapper.map(studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found")),StudentRequest.class);
    }

    @Override
    public void save(StudentRequest request) {
        final String uploadPath = "C:\\Users\\AD\\Desktop\\mvc-crud\\src\\main\\webapp\\uploads";
        // chuyển đổi từ dto -> entity
        Student student = mapper.map(request, Student.class);
        // lấy ra ảnh cũ nếu đang edit
        if (request.getId()!=null){
            // cập nhật
            student.setAvatar(studentRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("not found")).getAvatar());
        }
        // upload ảnh
        MultipartFile file = request.getFile();
        if (file.getSize()!=0){
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(),new File(uploadPath+File.separator+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            student.setAvatar(fileName);
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}
