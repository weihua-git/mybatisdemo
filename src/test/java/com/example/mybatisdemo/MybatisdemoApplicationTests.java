package com.example.mybatisdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisdemo.entity.Grade;
import com.example.mybatisdemo.entity.Student;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.entity.vo.StudentVo;
import com.example.mybatisdemo.mapper.StudentMapper;
import com.example.mybatisdemo.mapper.UserMapper;
import com.example.mybatisdemo.service.IGradeService;
import com.example.mybatisdemo.service.IStudentService;
import com.example.mybatisdemo.service.IUserService;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import es6.console;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MybatisdemoApplicationTests {


    @Resource
    private UserMapper userMapper;

    @Resource
    private IUserService userService;

    @Resource
    private IGradeService gradeService;

    @Resource
    private IStudentService studentService;

    @Test
    void addGrade() {
//        console.log("======");
//        ArrayList<Grade> objects = new ArrayList<Grade>();
//        objects.add(new Grade(0, "年级1", "asdsa",0));
//        objects.add(new Grade(0, "年级2", "asdsa",0));
//        objects.add(new Grade(0, "年级3", "asdsa",0));
//        objects.forEach(System.out::print);
//        boolean b = gradeService.saveBatch(objects);
//        console.log(b);

    }

    @Test
    void addStudent() {

        console.log("======");
        ArrayList<Student> objects = new ArrayList<Student>();
        for (int i = 5; i <= 100; i++) {
            objects.add(new Student(0, "用户" + i, i % 2 == 0 ? 1 : 2, 18, 0));
        }
        boolean b = studentService.saveBatch(objects);
        console.log(b);

    }


    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        String name = "";
        Integer age = 1;
        // 进行具体条件构造
        userService.list(
                new LambdaQueryWrapper<User>()
                        .select(User::getName)
                        .eq(StringUtils.isNotBlank(name), User::getName, name)
                        .gt(age != null && age >= 0, User::getAge, 18)
        );


    }

    @Resource
    private StudentMapper studentMapper;


    // 简单的连表查询
    @Test
    void contextLoads2() {


        List<StudentVo> studentVos = studentMapper.selectJoinList(StudentVo.class,
                new MPJQueryWrapper<Student>()
                        .select("gr.gradeName", "t.stuName")
                        .leftJoin("grade gr on t.gradeId = gr.Id")
        );


    }

    @Test
    void testJoin() {


        studentMapper.selectJoinPage(new Page<>(1, 2), StudentVo.class,
                new MPJQueryWrapper<Student>()
                        .select("gr.gradeName", "t.stuName")
                        .leftJoin("grade gr on t.gradeId = gr.Id"));

    }

    // lambda
    @Test
    void lambdacontextLoads2() {

        String name = "test";
        studentMapper.selectJoinList(StudentVo.class,
                new MPJLambdaWrapper<>()
                        .selectAll(Student.class)
                        .selectAs(Grade::getGradeName, StudentVo::getFakeyouName)
                        .leftJoin(Grade.class, Grade::getId, Student::getGradeId)
                        .eq(Grade::getId, 1)
                        .like(StringUtils.isNotBlank(name), Student::getStuName, "1")

        );

    }

    @Test
    void lambdacontextLoads2Page() {

        String name = "test";
        studentMapper.selectJoinPage(new Page<>(1,10),StudentVo.class,
                new MPJLambdaWrapper<>()
                        .selectAll(Student.class)
                        .selectAs(Grade::getGradeName, StudentVo::getFakeyouName)
                        .leftJoin(Grade.class, Grade::getId, Student::getGradeId)
                        .eq(Grade::getId, 1)
                        .like(StringUtils.isNotBlank(name), Student::getStuName, "1")

        );

    }

}
