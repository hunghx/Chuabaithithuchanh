package ra.mvccrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(name = "student_name")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String avatar;
    private Boolean sex;
    private String address;
}
