package ra.mvccrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.mvccrud.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
        List<Student> findAllByNameContainingOrAddressContaining(String name, String address);
}
