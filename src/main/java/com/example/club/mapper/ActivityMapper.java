package com.example.club.mapper;

import com.example.club.entity.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivityMapper {

    @Insert("""
            <script>
            insert into activity
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="clubId != null">club_id,</if>
                <if test="activityName != null">activity_name,</if>
                <if test="description != null">description,</if>
                <if test="location != null">location,</if>
                <if test="startTime != null">start_time,</if>
                <if test="endTime != null">end_time,</if>
                <if test="maxPeople != null">max_people,</if>
                <if test="currentPeople != null">current_people,</if>
                <if test="publisherId != null">publisher_id,</if>
                <if test="status != null">status,</if>
                <if test="createTime != null">create_time,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="clubId != null">#{clubId},</if>
                <if test="activityName != null">#{activityName},</if>
                <if test="description != null">#{description},</if>
                <if test="location != null">#{location},</if>
                <if test="startTime != null">#{startTime},</if>
                <if test="endTime != null">#{endTime},</if>
                <if test="maxPeople != null">#{maxPeople},</if>
                <if test="currentPeople != null">#{currentPeople},</if>
                <if test="publisherId != null">#{publisherId},</if>
                <if test="status != null">#{status},</if>
                <if test="createTime != null">#{createTime},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Activity activity);

    @Delete("delete from activity where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("""
            <script>
            update activity
            <set>
                <if test="clubId != null">club_id = #{clubId},</if>
                <if test="activityName != null">activity_name = #{activityName},</if>
                <if test="description != null">description = #{description},</if>
                <if test="location != null">location = #{location},</if>
                <if test="startTime != null">start_time = #{startTime},</if>
                <if test="endTime != null">end_time = #{endTime},</if>
                <if test="maxPeople != null">max_people = #{maxPeople},</if>
                <if test="currentPeople != null">current_people = #{currentPeople},</if>
                <if test="publisherId != null">publisher_id = #{publisherId},</if>
                <if test="status != null">status = #{status},</if>
                <if test="createTime != null">create_time = #{createTime},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(Activity activity);

    @Update("update activity set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Update("update activity set current_people = current_people + 1 where id = #{id}")
    int increaseCurrentPeople(@Param("id") Integer id);

    @Update("update activity set current_people = current_people - 1 where id = #{id} and current_people > 0")
    int decreaseCurrentPeople(@Param("id") Integer id);

    @Select("select * from activity where id = #{id}")
    Activity selectById(@Param("id") Integer id);

    @Select("select * from activity where club_id = #{clubId}")
    List<Activity> selectByClubId(@Param("clubId") Integer clubId);

    @Select("select * from activity where publisher_id = #{publisherId}")
    List<Activity> selectByPublisherId(@Param("publisherId") Integer publisherId);

    @Select("select * from activity where status = #{status}")
    List<Activity> selectByStatus(@Param("status") Integer status);

    @Select("select * from activity order by start_time desc")
    List<Activity> selectAll();
}
