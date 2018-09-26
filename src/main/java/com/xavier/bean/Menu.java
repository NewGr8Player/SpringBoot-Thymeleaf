package com.xavier.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xavier.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 系统菜单
 *
 * @author NewGr8Player
 */
@TableName(value = "sys_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {

    /**
     * 父级Id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 菜单类型
     */
    @TableField("menu_type")
    private String menuType;

    /**
     * 菜单编码
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单路径
     */
    @TableField("menu_url")
    private String menuUrl;

    /**
     * 菜单排序
     */
    @TableField("menu_order")
    private String menuOrder;

}
