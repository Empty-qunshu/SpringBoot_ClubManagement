package com.example.club.mapper;

import com.example.club.entity.ClubMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClubMemberMapper {

    @Insert("""
            <script>
            insert into club_member
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="clubId != null">club_id,</if>
                <if test="userId != null">user_id,</if>
                <if test="memberRole != null">member_role,</if>
                <if test="joinTime != null">join_time,</if>
                <if test="status != null">status,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="clubId != null">#{clubId},</if>
                <if test="userId != null">#{userId},</if>
                <if test="memberRole != null">#{memberRole},</if>
                <if test="joinTime != null">#{joinTime},</if>
                <if test="status != null">#{status},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ClubMember clubMember);

    @Delete("delete from club_member where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Delete("delete from club_member where club_id = #{clubId} and user_id = #{userId}")
    int deleteByClubIdAndUserId(@Param("clubId") Integer clubId, @Param("userId") Integer userId);

    @Update("""
            <script>
            update club_member
            <set>
                <if test="clubId != null">club_id = #{clubId},</if>
                <if test="userId != null">user_id = #{userId},</if>
                <if test="memberRole != null">member_role = #{memberRole},</if>
                <if test="joinTime != null">join_time = #{joinTime},</if>
                <if test="status != null">status = #{status},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(ClubMember clubMember);

    @Update("update club_member set member_role = #{memberRole} where id = #{id}")
    int updateRole(@Param("id") Integer id, @Param("memberRole") Integer memberRole);

    @Update("update club_member set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Select("select * from club_member where id = #{id}")
    ClubMember selectById(@Param("id") Integer id);

    @Select("select * from club_member where club_id = #{clubId} and user_id = #{userId}")
    ClubMember selectByClubIdAndUserId(@Param("clubId") Integer clubId, @Param("userId") Integer userId);

    @Select("select * from club_member where club_id = #{clubId}")
    List<ClubMember> selectByClubId(@Param("clubId") Integer clubId);

    @Select("select * from club_member where user_id = #{userId}")
    List<ClubMember> selectByUserId(@Param("userId") Integer userId);

    @Select("select * from club_member where club_id = #{clubId} and status = #{status}")
    List<ClubMember> selectByClubIdAndStatus(@Param("clubId") Integer clubId, @Param("status") Integer status);

    @Select("select * from club_member where user_id = #{userId} and status = #{status}")
    List<ClubMember> selectByUserIdAndStatus(@Param("userId") Integer userId, @Param("status") Integer status);

    @Select("select * from club_member where club_id = #{clubId} and member_role = #{memberRole}")
    List<ClubMember> selectByClubIdAndRole(@Param("clubId") Integer clubId, @Param("memberRole") Integer memberRole);

    @Select("select count(*) from club_member where club_id = #{clubId} and status = 1")
    int countActiveByClubId(@Param("clubId") Integer clubId);

    @Select("select * from club_member")
    List<ClubMember> selectAll();
}
