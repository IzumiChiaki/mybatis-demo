import com.chiaki.entity.Student;
import com.chiaki.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis快速案例
 * @author chenliang258
 * @date 2021/3/3 13:59
 */
public class StudentTest {

    private InputStream in;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // 读取MyBatis的配置文件
        in = Resources.getResourceAsStream("mybatis-config.xml");
        // 创建SqlSessionFactory的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 使用builder创建SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        // 使用factory创建sqlSession对象并设置自动提交事务
        sqlSession = factory.openSession(true);
    }

    @Test
    public void test() {
        // 使用sqlSession创建StudentMapper接口的代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        // 使用代理对象执行相关方法
        System.out.println(studentMapper.getStudentById(2));
        studentMapper.updateStudentName("托尼·李四", 2);
        System.out.println(studentMapper.getStudentById(2));
        System.out.println(studentMapper.findAll());
    }

    @Test
    public void testSqlExecute() {
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.getStudentById(2);
        System.out.println(student);
    }

    @Test
    public void testInsertStudent() {
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        studentMapper.insertStudent(new Student("赵六", "男"));
    }

    @After
    public void close() throws IOException {
        // 关闭资源
        sqlSession.close();
        in.close();
    }
}
