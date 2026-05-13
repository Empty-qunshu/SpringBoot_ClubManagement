package com.example.club.mapper;

import com.example.club.entity.Recruitment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RecruitmentMapper {

    @Insert("""
            <script>
            insert into recruitment
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="clubId != null">club_id,</if>
                <if test="title != null">title,</if>
                <if test="content != null">content,</if>
                <if test="requirement != null">requirement,</if>
                <if test="startTime != null">start_time,</if>
                <if test="endTime != null">end_time,</if>
                <if test="limitCount != null">limit_count,</if>
                <if test="currentCount != null">current_count,</if>
                <if test="status != null">status,</if>
                <if test="publisherId != null">publisher_id,</if>
                <if test="createTime != null">create_time,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="clubId != null">#{clubId},</if>
                <if test="title != null">#{title},</if>
                <if test="content != null">#{content},</if>
                <if test="requirement != null">#{requirement},</if>
                <if test="startTime != null">#{startTime},</if>
                <if test="endTime != null">#{endTime},</if>
                <if test="limitCount != null">#{limitCount},</if>
                <if test="currentCount != null">#{currentCount},</if>
                <if test="status != null">#{status},</if>
                <if test="publisherId != null">#{publisherId},</if>
                <if test="createTime != null">#{createTime},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Recruitment recruitment);

    @Delete("delete from recruitment where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("""
            <script>
            update recruitment
            <set>
                <if test="clubId != null">club_id = #{clubId},</if>
                <if test="title != null">title = #{title},</if>
                <if test="content != null">content = #{content},</if>
                <if test="requirement != null">requirement = #{requirement},</if>
                <if test="startTime != null">start_time = #{startTime},</if>
                <if test="endTime != null">end_time = #{endTime},</if>
                <if test="limitCount != null">limit_count = #{limitCount},</if>
                <if test="currentCount != null">current_count = #{currentCount},</if>
                <if test="status != null">status = #{status},</if>
                <if test="publisherId != null">publisher_id = #{publisherId},</if>
                <if test="createTime != null">create_time = #{createTime},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(Recruitment recruitment);

    @Update("update recruitment set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Update("update recruitment set current_count = current_count + 1 where id = #{id}")
    int increaseCurrentCount(@Param("id") Integer id);

    @Update("update recruitment set current_count = current_count - 1 where id = #{id} and current_count > 0")
    int decreaseCurrentCount(@Param("id") Integer id);

    @Select("select * from recruitment where id = #{id}")
    Recruitment selectById(@Param("id") Integer id);

    @Select("select * from recruitment where club_id = #{clubId}")
    List<Recruitment> selectByClubId(@Param("clubId") Integer clubId);

    @Select("select * from recruitment where publisher_id = #{publisherId}")
    List<Recruitment> selectByPublisherId(@Param("publisherId") Integer publisherId);

    @Select("select * from recruitment where status = #{status}")
    List<Recruitment> selectByStatus(@Param("status") Integer status);

    @Select("select * from recruitment order by create_time desc")
    List<Recruitment> selectAll();
}
