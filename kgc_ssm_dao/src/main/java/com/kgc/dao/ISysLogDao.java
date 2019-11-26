package com.kgc.dao;

import com.kgc.domain.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("<script>" +
            "select * from sysLog " +
            "<where>"+
            "<if test=\"condition != null and condition != ''\">\n"+
            "id like ('%'||#{condition}||'%') or " +
            "visitTime like ('%'||#{condition}||'%') or " +
            "username like ('%'||#{condition}||'%') or "+
            "ip like ('%'||#{condition}||'%') or " +
            "url like ('%'||#{condition}||'%') or " +
            "executionTime like ('%'||#{condition}||'%') or " +
            "method like ('%'||#{condition}||'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<SysLog> findAll(@Param("condition") String condition);
    @Delete("<script>"+
            "delete from syslog"+
            "<where>"+
            "<foreach collection='ids' open='and id in(' close=')' item='id' separator=','>"+
            "#{id}"+
            "</foreach>"+
            "</where>"+
            "</script>")
    void deleteById(@Param("ids")String[] ids);
}
