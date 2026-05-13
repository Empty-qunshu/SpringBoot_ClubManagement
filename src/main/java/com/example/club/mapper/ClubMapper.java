package com.example.club.mapper;

import com.example.club.entity.Club;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClubMapper {

    @Insert("""
            <script>
            insert into club
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="clubName != null">club_name,</if>
                <if test="clubDescription != null">club_description,</if>
                <if test="clubType != null">club_type,</if>
                <if test="leaderId != null">leader_id,</if>
                <if test="contactPhone != null">contact_phone,</if>
                <if test="location != null">location,</if>
                <if test="memberCount != null">member_count,</if>
                <if test="status != null">status,</if>
                <if test="createTime != null">create_time,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="clubName != null">#{clubName},</if>
                <if test="clubDescription != null">#{clubDescription},</if>
                <if test="clubType != null">#{clubType},</if>
                <if test="leaderId != null">#{leaderId},</if>
                <if test="contactPhone != null">#{contactPhone},</if>
                <if test="location != null">#{location},</if>
                <if test="memberCount != null">#{memberCount},</if>
                <if test="status != null">#{status},</if>
                <if test="createTime != null">#{createTime},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Club club);

    @Delete("delete from club where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("""
            <script>
            update club
            <set>
                <if test="clubName != null">club_name = #{clubName},</if>
                <if test="clubDescription != null">club_description = #{clubDescription},</if>
                <if test="clubType != null">club_type = #{clubType},</if>
                <if test="leaderId != null">leader_id = #{leaderId},</if>
                <if test="contactPhone != null">contact_phone = #{contactPhone},</if>
                <if test="location != null">location = #{location},</if>
                <if test="memberCount != null">member_count = #{memberCount},</if>
                <if test="status != null">status = #{status},</if>
                <if test="createTime != null">create_time = #{createTime},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(Club club);

    @Update("update club set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Update("update club set member_count = member_count + 1 where id = #{id}")
    int increaseMemberCount(@Param("id") Integer id);

    @Update("update club set member_count = member_count - 1 where id = #{id} and member_count > 0")
    int decreaseMemberCount(@Param("id") Integer id);

    @Select("select * from club where id = #{id}")
    Club selectById(@Param("id") Integer id);

    @Select("select * from club where leader_id = #{leaderId}")
    List<Club> selectByLeaderId(@Param("leaderId") Integer leaderId);

    @Select("select * from club where club_type = #{clubType}")
    List<Club> selectByClubType(@Param("clubType") String clubType);

    @Select("select * from club where status = #{status}")
    List<Club> selectByStatus(@Param("status") Integer status);

    @Select("select * from club")
    List<Club> selectAll();
}
