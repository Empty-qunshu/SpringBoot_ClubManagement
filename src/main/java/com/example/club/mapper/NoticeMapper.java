package com.example.club.mapper;

import com.example.club.entity.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Insert("""
            <script>
            insert into notice
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <if test="title != null">title,</if>
                <if test="content != null">content,</if>
                <if test="publisherId != null">publisher_id,</if>
                <if test="createTime != null">create_time,</if>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides=",">
                <if test="title != null">#{title},</if>
                <if test="content != null">#{content},</if>
                <if test="publisherId != null">#{publisherId},</if>
                <if test="createTime != null">#{createTime},</if>
            </trim>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Notice notice);

    @Delete("delete from notice where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("""
            <script>
            update notice
            <set>
                <if test="title != null">title = #{title},</if>
                <if test="content != null">content = #{content},</if>
                <if test="publisherId != null">publisher_id = #{publisherId},</if>
                <if test="createTime != null">create_time = #{createTime},</if>
            </set>
            where id = #{id}
            </script>
            """)
    int update(Notice notice);

    @Select("select * from notice where id = #{id}")
    Notice selectById(@Param("id") Integer id);

    @Select("select * from notice where publisher_id = #{publisherId}")
    List<Notice> selectByPublisherId(@Param("publisherId") Integer publisherId);

    @Select("select * from notice order by create_time desc")
    List<Notice> selectAll();
}
