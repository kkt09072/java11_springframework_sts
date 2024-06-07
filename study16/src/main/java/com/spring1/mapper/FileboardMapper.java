package com.spring1.mapper;

import com.spring1.domain.Fileboard;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileboardMapper {

    @Select("SELECT * FROM fileboard ORDER BY no DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<Fileboard> getList(@Param("pageSize") int pageSize, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM fileboard")
    int getTotalCount();

    @Select("SELECT * FROM fileboard WHERE no = #{no}")
    Fileboard getDetail(int no);

    @Insert("INSERT INTO fileboard (title, content, filename) VALUES (#{title}, #{content}, #{filename})")
    void insertFileboard(Fileboard fileboard);

    @Update("UPDATE fileboard SET title = #{title}, content = #{content}, filename = #{filename} WHERE no = #{no}")
    void updateFileboard(Fileboard fileboard);

    @Delete("DELETE FROM fileboard WHERE no = #{no}")
    void deleteFileboard(int no);
}
