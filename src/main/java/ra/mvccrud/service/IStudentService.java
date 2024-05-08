package ra.mvccrud.service;

import ra.mvccrud.dto.request.StudentRequest;
import ra.mvccrud.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findALl();
    List<Student> findALlByName(String keyword);
    StudentRequest findById(Integer id);
    void save(StudentRequest request);
    void  deleteById(Integer id);

}
