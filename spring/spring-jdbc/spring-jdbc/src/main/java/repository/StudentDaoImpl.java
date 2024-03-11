package repository;

import entities.Student;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDaoImpl implements StudentDao{

    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertStudent (Student student) {
        String insertQuery = "insert into student(id,name,city) values (?,?,?)";
        int result;
        result = this.jdbcTemplate.update(insertQuery, student.getId(), student.getName(), student.getCity());
        return result;
    }

    @Override
    public int updateStudent (Student student) {
        String updateQuery = "update student set name=?,city=? where id=?";
        int result;
        result = this.jdbcTemplate.update(updateQuery, student.getName(), student.getCity(), student.getId());
        return result;
    }

    @Override
    public int deleteStudent (int studentId) {
        String deleteQuery = "delete from student where id=?";
        int result;
        result = this.jdbcTemplate.update(deleteQuery, studentId);
        return result;
    }

    public JdbcTemplate getJdbcTemplate () {
        return jdbcTemplate;
    }

    public void setJdbcTemplate (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
