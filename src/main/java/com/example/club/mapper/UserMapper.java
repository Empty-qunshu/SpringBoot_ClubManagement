package com.example.club.mapper;

import com.example.club.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("""
            <script>
            insert into `user`
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="username != null">username,</if>
                <if test="password != null">password,</if>
                <if test="realName != null">real_name,</if>
                <if test="studentNo != null">student_no,</if>
                <if test="gender != null">gender,</if>
                <if test="phone != null">phone,</if>
                <if test="email != null">email,</if>
                <if test="role != null">role,</if>
                <if test="avatar != null">avatar,</if>
                <if test="status != null">status,</if>
                <if test="createTime != null">create_time,</if>
                <if test="updateTime != null">update_time,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="username != null">#{username},</if>
                <if test="password != null">#{password},</if>
                <if test="realName != null">#{realName},</if>
                <if test="studentNo != null">#{studentNo},</if>
                <if test="gender != null">#{gender},</if>
                <if test="phone != null">#{phone},</if>
                <if test="email != null">#{email},</if>
                <if test="role != null">#{role},</if>
                <if test="avatar != null">#{avatar},</if>
                <if test="status != null">#{status},</if>
                <if test="createTime != null">#{createTime},</if>
                <if test="updateTime != null">#{updateTime},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Delete("delete from `user` where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("""
            <script>
            update `user`
            <set>
                <if test="username != null">username = #{username},</if>
                <if test="password != null">password = #{password},</if>
                <if test="realName != null">real_name = #{realName},</if>
                <if test="studentNo != null">student_no = #{studentNo},</if>
                <if test="gender != null">gender = #{gender},</if>
                <if test="phone != null">phone = #{phone},</if>
                <if test="email != null">email = #{email},</if>
                <if test="role != null">role = #{role},</if>
                <if test="avatar != null">avatar = #{avatar},</if>
                <if test="status != null">status = #{status},</if>
                <if test="createTime != null">create_time = #{createTime},</if>
                <if test="updateTime != null">update_time = #{updateTime},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(User user);

    @Update("update `user` set password = #{password} where id = #{id}")
    int updatePassword(@Param("id") Integer id, @Param("password") String password);

    @Update("update `user` set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Select("select * from `user` where id = #{id}")
    User selectById(@Param("id") Integer id);

    @Select("select * from `user` where username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("select * from `user` where student_no = #{studentNo}")
    User findByStudentNo(@Param("studentNo") String studentNo);

    @Select("select * from `user`")
    List<User> findAll();

    @Select("select * from `user`")
    List<User> selectAll();

    @Select("select * from `user` where role = #{role}")
    List<User> selectByRole(@Param("role") Integer role);

    @Select("select * from `user` where status = #{status}")
    List<User> selectByStatus(@Param("status") Integer status);

    @Select("select * from `user` where username = #{username} and password = #{password}")
    User selectByNumberAndPassword(User user);

    @Select("select * from `user` where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
