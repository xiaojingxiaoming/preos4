package com.kgc.dao;

import com.kgc.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {
    //根据id查询产品信息
    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;
    //查询产品信息
    @Select("<script>" +
            "select * from product " +
            "<where>"+
            "<if test=\"condition != null and condition != ''\">\n"+
            "productNum like ('%'||#{condition}||'%') or " +
            "productName like ('%'||#{condition}||'%') or "+
            "cityName like ('%'||#{condition}||'%') or " +
            "productPrice like ('%'||#{condition}||'%') or " +
            "productDesc like ('%'||#{condition}||'%') or " +
            "id like ('%'||#{condition}||'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Product> findAll(@Param("condition") String condition) throws  Exception;
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    //根据提交的ids修改的产品的状态
    @Select("<script>" +
            "update  product set productStatus=1"+
            "<where>"+
            "<foreach collection='ids' open='and id in(' close=')' item='id' separator=','>"+
            "#{id}"+
            "</foreach>"+
            "</where>"+
            "</script>")
    void updateStatus( @Param("ids") String[] ids);

    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void updateProduct(Product product);

    @Delete("<script>" +
            "delete from product"+
            "<where>"+
            "<foreach collection='ids' open='and id in(' close=')' item='id' separator=','>"+
            "#{id}"+
            "</foreach>"+
            "</where>"+
            "</script>")
    void deleteByIds(@Param("ids") String[] ids);
}
