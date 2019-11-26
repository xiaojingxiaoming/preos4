package com.kgc.dao;

import com.kgc.domain.Permission;
import com.kgc.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id去中间表中查询角色id  在根据角色的id查询角色信息
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "desc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many=@Many(select = "com.kgc.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "desc",column = "roleDesc")
    })
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{desc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "desc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many=@Many(select = "com.kgc.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String id);

    @Delete("delete from role where id=#{id}")
    void deleteById(String id);
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId) throws Exception;

    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
