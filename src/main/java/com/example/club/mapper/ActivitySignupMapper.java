package com.example.club.mapper;

import com.example.club.entity.ActivitySignup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivitySignupMapper {

    @Insert("""
            <script>
            insert into activity_signup
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="activityId != null">activity_id,</if>
                <if test="userId != null">user_id,</if>
                <if test="signupTime != null">signup_time,</if>
                <if test="signStatus != null">sign_status,</if>
                <if test="status != null">status,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="activityId != null">#{activityId},</if>
                <if test="userId != null">#{userId},</if>
                <if test="signupTime != null">#{signupTime},</if>
                <if test="signStatus != null">#{signStatus},</if>
                <if test="status != null">#{status},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ActivitySignup activitySignup);

    @Delete("delete from activity_signup where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Delete("delete from activity_signup where activity_id = #{activityId} and user_id = #{userId}")
    int deleteByActivityIdAndUserId(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

    @Update("""
            <script>
            update activity_signup
            <set>
                <if test="activityId != null">activity_id = #{activityId},</if>
                <if test="userId != null">user_id = #{userId},</if>
                <if test="signupTime != null">signup_time = #{signupTime},</if>
                <if test="signStatus != null">sign_status = #{signStatus},</if>
                <if test="status != null">status = #{status},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(ActivitySignup activitySignup);

    @Update("update activity_signup set sign_status = #{signStatus} where id = #{id}")
    int updateSignStatus(@Param("id") Integer id, @Param("signStatus") Integer signStatus);

    @Update("update activity_signup set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Select("select * from activity_signup where id = #{id}")
    ActivitySignup selectById(@Param("id") Integer id);

    @Select("select * from activity_signup where activity_id = #{activityId} and user_id = #{userId}")
    ActivitySignup selectByActivityIdAndUserId(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

    @Select("select * from activity_signup where activity_id = #{activityId}")
    List<ActivitySignup> selectByActivityId(@Param("activityId") Integer activityId);

    @Select("select * from activity_signup where user_id = #{userId}")
    List<ActivitySignup> selectByUserId(@Param("userId") Integer userId);

    @Select("select * from activity_signup where activity_id = #{activityId} and status = #{status}")
    List<ActivitySignup> selectByActivityIdAndStatus(@Param("activityId") Integer activityId, @Param("status") Integer status);

    @Select("select count(*) from activity_signup where activity_id = #{activityId} and status = 1")
    int countActiveByActivityId(@Param("activityId") Integer activityId);

    @Select("select * from activity_signup")
    List<ActivitySignup> selectAll();
}
