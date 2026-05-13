package com.example.club.mapper;

import com.example.club.entity.ClubApply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClubApplyMapper {

    @Insert("""
            <script>
            insert into club_apply
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="userId != null">user_id,</if>
                <if test="clubId != null">club_id,</if>
                <if test="recruitmentId != null">recruitment_id,</if>
                <if test="applyReason != null">apply_reason,</if>
                <if test="personalStrength != null">personal_strength,</if>
                <if test="status != null">status,</if>
                <if test="applyTime != null">apply_time,</if>
                <if test="reviewTime != null">review_time,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="userId != null">#{userId},</if>
                <if test="clubId != null">#{clubId},</if>
                <if test="recruitmentId != null">#{recruitmentId},</if>
                <if test="applyReason != null">#{applyReason},</if>
                <if test="personalStrength != null">#{personalStrength},</if>
                <if test="status != null">#{status},</if>
                <if test="applyTime != null">#{applyTime},</if>
                <if test="reviewTime != null">#{reviewTime},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ClubApply clubApply);

    @Delete("delete from club_apply where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("""
            <script>
            update club_apply
            <set>
                <if test="userId != null">user_id = #{userId},</if>
                <if test="clubId != null">club_id = #{clubId},</if>
                <if test="recruitmentId != null">recruitment_id = #{recruitmentId},</if>
                <if test="applyReason != null">apply_reason = #{applyReason},</if>
                <if test="personalStrength != null">personal_strength = #{personalStrength},</if>
                <if test="status != null">status = #{status},</if>
                <if test="applyTime != null">apply_time = #{applyTime},</if>
                <if test="reviewTime != null">review_time = #{reviewTime},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(ClubApply clubApply);

    @Update("update club_apply set status = #{status}, review_time = now() where id = #{id}")
    int review(@Param("id") Integer id, @Param("status") Integer status);

    @Select("select * from club_apply where id = #{id}")
    ClubApply selectById(@Param("id") Integer id);

    @Select("select * from club_apply where user_id = #{userId}")
    List<ClubApply> selectByUserId(@Param("userId") Integer userId);

    @Select("select * from club_apply where club_id = #{clubId}")
    List<ClubApply> selectByClubId(@Param("clubId") Integer clubId);

    @Select("select * from club_apply where club_id = #{clubId} and status = #{status}")
    List<ClubApply> selectByClubIdAndStatus(@Param("clubId") Integer clubId, @Param("status") Integer status);

    @Select("select * from club_apply where recruitment_id = #{recruitmentId}")
    List<ClubApply> selectByRecruitmentId(@Param("recruitmentId") Integer recruitmentId);

    @Select("select * from club_apply where status = #{status}")
    List<ClubApply> selectByStatus(@Param("status") Integer status);

    @Select("select * from club_apply where user_id = #{userId} and recruitment_id = #{recruitmentId}")
    ClubApply selectByUserIdAndRecruitmentId(@Param("userId") Integer userId, @Param("recruitmentId") Integer recruitmentId);

    @Select("select count(*) from club_apply where recruitment_id = #{recruitmentId} and status = 1")
    int countPassedByRecruitmentId(@Param("recruitmentId") Integer recruitmentId);

    @Select("select * from club_apply")
    List<ClubApply> selectAll();
}
